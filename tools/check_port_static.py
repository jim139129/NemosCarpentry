"""Offline checks only: never invokes Gradle, Java, a launcher, or the network."""
from collections import Counter
from hashlib import sha256
import json
from pathlib import Path
import re
import subprocess
import sys

ROOT = Path(__file__).resolve().parents[1]
BASELINE = json.loads((ROOT / "docs/port-26.3/baseline.json").read_text(encoding="utf-8"))
PACKAGE = "com/nemonotfound/nemos/carpentry"
MAIN = ROOT / "src/main/java" / PACKAGE
CLIENT = ROOT / "src/client/java" / PACKAGE
RESOURCE_ROOTS = [ROOT / "src/main/resources", ROOT / "src/main/generated"]
errors = []
warnings = []


def check(condition, message):
    if not condition:
        errors.append(message)


def source(path):
    text = path.read_text(encoding="utf-8")
    return re.sub(r"/\*.*?\*/|//[^\n]*", "", text, flags=re.S)


def resource_digest(root, excluded=()):
    files = sorted(p for p in root.rglob("*") if p.is_file() and ".cache" not in p.parts and p.name not in excluded)
    def baseline_bytes(path):
        raw = path.read_bytes()
        if path.suffix == ".json" and "advancement" in path.parts:
            # The sole allowed data migration is the 26.3 recipe_unlocked field
            # rename. Reverse only that criterion to retain the ORIGINAL manifest
            # as proof that IDs, rewards, requirements and other assets are unchanged.
            for criterion in json.loads(raw).get("criteria", {}).values():
                if criterion.get("trigger") == "minecraft:recipe_unlocked":
                    recipes = criterion.get("conditions", {}).get("recipes")
                    if isinstance(recipes, str):
                        new = ('"recipes": ' + json.dumps(recipes)).encode()
                        old = ('"recipe": ' + json.dumps(recipes)).encode()
                        raw = raw.replace(new, old)
        return raw
    manifest = "\n".join(p.relative_to(ROOT).as_posix() + ":" + sha256(baseline_bytes(p)).hexdigest() for p in files)
    return sha256(manifest.encode()).hexdigest()


def pairs_unique(pairs):
    result = {}
    for key, value in pairs:
        if key in result:
            raise ValueError(f"duplicate JSON key: {key}")
        result[key] = value
    return result


blocks_text = (MAIN / "block/CarpentryBlocks.java").read_text(encoding="utf-8")
blocks = dict(re.findall(r'public static final Block (\w+) = register\("([^"]+)"', blocks_text))
items_text = (MAIN / "item/CarpentryItems.java").read_text(encoding="utf-8")
items = {blocks[name] for name in re.findall(r'\bregisterBlock\(CarpentryBlocks\.(\w+)', items_text)}
items.update(re.findall(r'= register\("([^"]+)"', items_text))
check(len(blocks) == len(set(blocks.values())), "Duplicate block registration ID")

# Compare the baseline literal inventories, not just the current resource references.
for name, literals in BASELINE["registration_literals"].items():
    path = MAIN / (name + ".java")
    current = re.findall(r'"([^"\n]+)"', path.read_text(encoding="utf-8"))
    check(Counter(current) == Counter(literals), f"Registration literal inventory changed: {name}")

check(resource_digest(RESOURCE_ROOTS[0], ("fabric.mod.json", "nemos-carpentry.mixins.json", "nemos-carpentry.accesswidener"))
      == BASELINE["resource_manifest_sha256"], "Existing resource content or paths changed")
check(resource_digest(RESOURCE_ROOTS[1]) == BASELINE["generated"]["manifest_sha256"], "Existing generated content or paths changed")

json_count = 0
recipe_counts = Counter()
ingredient_counts = Counter()
recipe_ids = set()
assets = set()
documents = []
duplicates = []
for root in RESOURCE_ROOTS:
    for path in sorted(root.rglob("*")):
        if not path.is_file() or ".cache" in path.parts:
            continue
        relative = path.relative_to(root).as_posix()
        if relative in assets:
            duplicates.append(relative)
        assets.add(relative)
        if path.suffix not in (".json", ".mcmeta"):
            continue
        try:
            data = json.loads(path.read_text(encoding="utf-8"), object_pairs_hook=pairs_unique)
        except (ValueError, UnicodeError) as exc:
            errors.append(f"{relative}: {exc}")
            continue
        json_count += 1
        documents.append((relative, data))
        if "/recipe/" not in relative:
            continue
        recipe_id = relative.split("/")[1] + ":" + relative.split("/recipe/", 1)[1][:-5]
        check(recipe_id not in recipe_ids, f"Duplicate recipe ID: {recipe_id}")
        recipe_ids.add(recipe_id)
        recipe_counts[data.get("type")] += 1
        if data.get("type") != "nemos-carpentry:carpentry":
            continue
        ingredients = data.get("ingredients", [])
        counts = data.get("inputCounts", [])
        ingredient_counts[len(ingredients)] += 1
        check(1 <= len(ingredients) <= 2 and len(ingredients) == len(counts), f"Material/count mismatch: {relative}")
        check(all(type(count) is int and count > 0 for count in counts), f"Invalid material count: {relative}")
        result = data.get("result", {})
        check(type(result.get("count", 1)) is int and result.get("count", 1) > 0, f"Invalid result count: {relative}")
        for value in [*ingredients, result.get("id", "")]:
            for item in (value if isinstance(value, list) else [value]):
                if isinstance(item, str) and item.startswith("nemos-carpentry:"):
                    check(item.split(":", 1)[1] in items, f"Unknown registered item {item}: {relative}")


def check_asset(identifier, category, suffix, owner):
    if not isinstance(identifier, str) or not identifier.startswith("nemos-carpentry:"):
        return
    namespace, path = identifier.split(":", 1)
    target = f"assets/{namespace}/{category}/{path}{suffix}"
    if target not in assets and "/blockstates/" in owner and Path(owner).stem not in blocks.values():
        # Kept legacy files are part of the baseline hash above. They do not belong
        # to a registered block; do not remove old assets to make this check pass.
        warnings.append(f"Baseline orphan blockstate references missing model {identifier}: {owner}")
        return
    check(target in assets, f"Missing {category} reference {identifier}: {owner}")


def walk_assets(value, owner):
    if isinstance(value, dict):
        for key, child in value.items():
            if key in ("parent", "model"):
                check_asset(child, "models", ".json", owner)
            if key == "textures" and isinstance(child, dict):
                for texture in child.values():
                    check_asset(texture, "textures", ".png", owner)
            walk_assets(child, owner)
    elif isinstance(value, list):
        for child in value:
            walk_assets(child, owner)


for relative, data in documents:
    if relative.startswith("assets/"):
        walk_assets(data, relative)

advancement_count = 0
for relative, data in documents:
    if "/advancement/" not in relative:
        continue
    advancement_count += 1
    criteria = data.get("criteria", {})
    requirements = data.get("requirements", [])
    check(bool(criteria) and bool(requirements) and all(requirements), f"Empty advancement criteria/requirements: {relative}")
    required_names = {name for group in requirements for name in group}
    check(required_names == set(criteria), f"Advancement criteria/requirements mismatch: {relative}")
    unlocked_recipes = set()
    for criterion in criteria.values():
        if criterion.get("trigger") != "minecraft:recipe_unlocked":
            continue
        conditions = criterion.get("conditions", {})
        check("recipe" not in conditions and "recipes" in conditions, f"Pre-26.3 recipe_unlocked schema: {relative}")
        values = conditions.get("recipes", [])
        values = [values] if isinstance(values, str) else values
        check(isinstance(values, list) and bool(values), f"Empty/invalid recipe holder set: {relative}")
        if isinstance(values, list):
            for value in values:
                check(isinstance(value, str) and value in recipe_ids, f"Unknown unlocked recipe {value}: {relative}")
                if isinstance(value, str):
                    unlocked_recipes.add(value)
    rewards = data.get("rewards", {}).get("recipes", [])
    check(set(rewards) == unlocked_recipes, f"Recipe unlock/reward mismatch: {relative}")
    for recipe_id in rewards:
        check(recipe_id in recipe_ids, f"Unknown rewarded recipe {recipe_id}: {relative}")

for block_id in blocks.values():
    check(f"assets/nemos-carpentry/blockstates/{block_id}.json" in assets, f"Missing blockstate: {block_id}")
for item_id in items:
    check(f"assets/nemos-carpentry/items/{item_id}.json" in assets, f"Missing item definition: {item_id}")

mod = json.loads((RESOURCE_ROOTS[0] / "fabric.mod.json").read_text(encoding="utf-8"))
check(mod["depends"]["minecraft"] == "~26.3", "Minecraft version constraint is not 26.3-only")
check(mod["depends"]["java"] == ">=25", "Java constraint is not 25")
check(mod["depends"]["fabricloader"] == ">=0.19.5", "Fabric Loader metadata does not match baseline")
check(mod["depends"]["fabric-api"] == ">=0.162.0+26.3", "Fabric API metadata does not match baseline")
check(mod.get("suggests", {}).get("jei") == ">=31.9.0.61", "Optional JEI metadata does not match baseline")
check(mod["depends"]["fabricloader"] == ">=0.19.5", "Fabric Loader metadata does not match baseline")
check(mod["depends"]["fabric-api"] == ">=0.162.0+26.3", "Fabric API metadata does not match baseline")
check(mod.get("suggests", {}).get("jei") == ">=31.9.0.61", "Optional JEI metadata does not match baseline")
check("mixins" not in mod and "accessWidener" not in mod, "Obsolete mixin/access widener configuration remains")
check("jei" not in mod["depends"], "JEI became a required dependency")
check(f"assets/nemos-carpentry/icon.png" in assets, "Missing mod icon")

sources = list((ROOT / "src/main/java").rglob("*.java")) + list((ROOT / "src/client/java").rglob("*.java"))
classes = {}
for path in sources:
    text = source(path)
    match = re.search(r"package\s+([\w.]+);", text)
    if match:
        classes[match[1] + "." + path.stem] = path

for entrypoints in mod["entrypoints"].values():
    for entrypoint in entrypoints:
        check(entrypoint in classes, f"Missing entrypoint class: {entrypoint}")

for path in sources:
    text = source(path)
    for imported in re.findall(r"import\s+(?:static\s+)?([\w.]+);", text):
        if imported.startswith("com.nemonotfound."):
            owner = imported
            while owner not in classes and "." in owner:
                owner = owner.rsplit(".", 1)[0]
            check(owner in classes, f"Unresolved project import {imported}: {path.name}")
            if owner in classes and "/src/main/" in path.as_posix():
                check("/src/client/" not in classes[owner].as_posix(), f"Common source references client class: {path.name} -> {owner}")
    if "/src/main/" in path.as_posix():
        check(not re.search(r"import\s+(net\.minecraft\.client|mezz\.jei|net\.fabricmc\.fabric\.api\.client)\.", text),
              f"Client dependency in common source: {path.name}")
    obsolete = r"CarpentryRecipeManagerGetter|CarpentryRecipeGetter|MinecraftGetter|nemo_sCarpentry\$|GameProtocols|SingleRecipeInput|GuiGraphics\b|BlockRenderLayerMap|FabricDataOutput|FabricTagProvider\b|FabricBlockLootTableProvider|valueLookupBuilder|setInvulnerable\("
    check(not re.search(obsolete, text), f"Obsolete API/bridge remains: {path.name}")
    check(not re.search(r"PushReaction\.(?:NORMAL|DESTROY|BLOCK|IGNORE|PUSH_ONLY)\b", text),
          f"Pre-26.3 piston reaction remains: {path.name}")
    check(not re.search(r"\bItems\.register(?:Block|Item)\(", text),
          f"Private vanilla item registration helper remains: {path.name}")
    check(not re.search(r"net\.minecraft\.advancements\.(?:Criterion;|criterion\.)", text),
          f"Pre-26.2 advancement package remains: {path.name}")
    if "/block/" in path.as_posix():
        check("MapCodec" not in text and "codec()" not in text, f"Removed block Codec override: {path.name}")
    # Lexical delimiter check, not Java type checking or compilation.
    scrubbed = re.sub(r'"(?:\\.|[^"\\])*"|\'(?:\\.|[^\'\\])*\'', "", text)
    stack = []
    for char in scrubbed:
        if char in "({[":
            stack.append(char)
        elif char in ")}]":
            if not stack or stack.pop() != dict(zip(")}]", "({["))[char]:
                errors.append(f"Unbalanced Java delimiters: {path.name}")
                break
    check(not stack, f"Unclosed Java delimiter: {path.name}")

build = (ROOT / "build.gradle").read_text(encoding="utf-8")
check("net.fabricmc.fabric-loom'" in build and "splitEnvironmentSourceSets()" in build, "Unobfuscated Loom/client split missing")
check(not re.search(r"\bmappings\b|\bremapJar\b|\bmodImplementation\b|\bmodCompileOnly\b|\bmodRuntimeOnly\b", build), "Obsolete build configuration")
check("Changelog.md" in build and "changelog.md" not in build, "Wrong changelog filename case")
for setting in ("it.options.release = 25", "toolchain.languageVersion = JavaLanguageVersion.of(25)",
                "sourceCompatibility = JavaVersion.VERSION_25", "targetCompatibility = JavaVersion.VERSION_25"):
    check(setting in build, f"Missing Java 25 build setting: {setting}")
check('clientCompileOnly "mezz.jei:jei-${project.minecraft_version}-fabric-api:${project.jei_version}"' in build,
      "JEI Fabric API is not isolated to client compilation")
check('clientRuntimeOnly "mezz.jei:jei-${project.minecraft_version}-fabric:${project.jei_version}"' in build,
      "JEI Fabric runtime is not isolated to the client")
for setting in ("it.options.release = 25", "toolchain.languageVersion = JavaLanguageVersion.of(25)",
                "sourceCompatibility = JavaVersion.VERSION_25", "targetCompatibility = JavaVersion.VERSION_25"):
    check(setting in build, f"Missing Java 25 build setting: {setting}")
check('clientCompileOnly "mezz.jei:jei-${project.minecraft_version}-fabric-api:${project.jei_version}"' in build,
      "JEI Fabric API is not isolated to client compilation")
check('clientRuntimeOnly "mezz.jei:jei-${project.minecraft_version}-fabric:${project.jei_version}"' in build,
      "JEI Fabric runtime is not isolated to the client")
properties = dict(line.split("=", 1) for line in (ROOT / "gradle.properties").read_text().splitlines() if "=" in line and not line.startswith("#"))
for name, expected in dict(minecraft_version="26.3", loader_version="0.19.5", loom_version="1.17.21", fabric_version="0.162.0+26.3", jei_version="31.9.0.61").items():
    check(properties.get(name) == expected, f"Wrong dependency baseline: {name}")
check("gradle-9.6.1-bin.zip" in (ROOT / "gradle/wrapper/gradle-wrapper.properties").read_text(), "Wrong Gradle wrapper target (user-updated 9.6.1)")

branch = subprocess.run(["git", "branch", "--show-current"], cwd=ROOT, capture_output=True, text=True, check=True).stdout.strip()
check(branch == BASELINE["branch"], f"Unexpected branch: {branch}")
diff = subprocess.run(["git", "diff", "--check"], cwd=ROOT, capture_output=True, text=True)
check(diff.returncode == 0, "git diff --check failed: " + diff.stdout + diff.stderr)
if duplicates:
    warnings.append(f"{len(duplicates)} pre-existing paths occur in both resource roots; content retained")

print(json.dumps({"scope": "offline static checks only; no compilation, datagen or game execution",
                  "branch": branch, "json_and_mcmeta": json_count, "blocks": len(blocks), "items": len(items),
                  "recipes": dict(recipe_counts), "carpentry_ingredient_counts": dict(ingredient_counts),
                  "advancements": advancement_count,
                  "java_sources": len(classes), "warnings": sorted(set(warnings)), "errors": errors}, indent=2, ensure_ascii=False))
sys.exit(bool(errors))
