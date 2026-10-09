package com.nemonotfound.nemos.carpentry.recipe;

import com.nemonotfound.nemos.carpentry.network.CarpentryRecipesPayload;
import com.nemonotfound.nemos.carpentry.network.SelectCarpentryRecipePayload;
import com.nemonotfound.nemos.carpentry.recipe.display.CarpentryRecipeDisplay;
import com.nemonotfound.nemos.carpentry.screen.CarpentryMenu;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.recipe.v1.FabricRecipeManager;
import net.minecraft.resources.Identifier;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.function.Supplier;

import static com.nemonotfound.nemos.carpentry.NemosCarpentry.log;

public final class CarpentryRecipeService {
    // Accessed on the server thread only. Client state is supplied by the client entrypoint.
    private static final Map<MinecraftServer, Snapshot> SERVERS = new WeakHashMap<>();
    private static Supplier<CarpentryRecipeDisplay.Grouping> clientRecipes = CarpentryRecipeDisplay.Grouping::empty;

    private record Snapshot(CarpentryRecipeDisplay.Grouping display, Map<Identifier, RecipeHolder<CarpentryRecipe>> recipes) {}

    private CarpentryRecipeService() {}

    public static void register() {
        PayloadTypeRegistry.clientboundPlay().registerLarge(CarpentryRecipesPayload.TYPE, CarpentryRecipesPayload.CODEC, 8 * 1024 * 1024);
        PayloadTypeRegistry.serverboundPlay().register(SelectCarpentryRecipePayload.TYPE, SelectCarpentryRecipePayload.CODEC);
        ServerPlayNetworking.registerGlobalReceiver(SelectCarpentryRecipePayload.TYPE, (payload, context) -> {
            var player = context.player();
            if (player.containerMenu instanceof CarpentryMenu menu && menu.containerId == payload.containerId()
                    && menu.stillValid(player)) {
                menu.selectRecipe(payload.revision(), payload.recipeId());
            }
        });
        ServerLifecycleEvents.SERVER_STARTED.register(server -> replace(server, false));
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            if (!SERVERS.containsKey(server)) {
                replace(server, false);
            }
            sender.sendPacket(new CarpentryRecipesPayload(SERVERS.get(server).display()));
        });
        ServerLifecycleEvents.END_DATA_PACK_RELOAD.register((server, resources, success) -> {
            if (success) {
                replace(server, true);
            }
        });
        ServerLifecycleEvents.SERVER_STOPPED.register(SERVERS::remove);
    }

    private static void replace(MinecraftServer server, boolean broadcast) {
        var previous = SERVERS.get(server);
        long revision = previous == null ? 1 : previous.display().revision() + 1;
        // Build before publishing so a failed reload never exposes a partial snapshot.
        var holders = ((FabricRecipeManager) server.getRecipeManager()).getAllOfType(CarpentryRecipeTypes.CARPENTRY)
                .stream().sorted(Comparator.comparing(holder -> holder.id().identifier().toString())).toList();
        var recipes = new LinkedHashMap<Identifier, RecipeHolder<CarpentryRecipe>>();
        var entries = holders.stream().map(holder -> {
            var recipe = holder.value();
            var id = holder.id().identifier();
            recipes.put(id, holder);
            return new CarpentryRecipeDisplay.GroupEntry(id, recipe.ingredients(), recipe.inputCounts(), recipe.result());
        }).toList();
        var snapshot = new Snapshot(new CarpentryRecipeDisplay.Grouping(revision, entries), Map.copyOf(recipes));
        SERVERS.put(server, snapshot);
        if (broadcast) {
            for (var player : server.getPlayerList().getPlayers()) {
                ServerPlayNetworking.send(player, new CarpentryRecipesPayload(snapshot.display()));
                if (player.containerMenu instanceof CarpentryMenu menu) {
                    menu.refreshRecipes();
                }
            }
        }
        log.info("Loaded {} carpentry recipes (revision {})", entries.size(), revision);
    }

    public static void setClientRecipeSource(Supplier<CarpentryRecipeDisplay.Grouping> source) {
        clientRecipes = source;
    }

    public static CarpentryRecipeDisplay.Grouping getRecipes(Level level) {
        if (level instanceof ServerLevel serverLevel) {
            var snapshot = SERVERS.get(serverLevel.getServer());
            return snapshot == null ? CarpentryRecipeDisplay.Grouping.empty() : snapshot.display();
        }
        return clientRecipes.get();
    }

    public static RecipeHolder<CarpentryRecipe> find(Level level, long revision, Identifier id) {
        if (!(level instanceof ServerLevel serverLevel)) {
            return null;
        }
        var snapshot = SERVERS.get(serverLevel.getServer());
        return snapshot == null || snapshot.display().revision() != revision ? null : snapshot.recipes().get(id);
    }
}
