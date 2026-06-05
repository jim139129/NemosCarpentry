package com.nemonotfound.nemos.carpentry.jei;

import com.nemonotfound.nemos.carpentry.interfaces.CarpentryRecipeManagerGetter;
import com.nemonotfound.nemos.carpentry.item.CarpentryItems;
import com.nemonotfound.nemos.carpentry.recipe.display.CarpentryRecipeDisplay;
import com.nemonotfound.nemos.carpentry.screen.CarpentryMenu;
import com.nemonotfound.nemos.carpentry.screen.CarpentryMenuTypes;
import com.nemonotfound.nemos.carpentry.screen.CarpentryScreen;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.nemonotfound.nemos.carpentry.NemosCarpentry.MOD_ID;

@JeiPlugin
public class NemosCarpentryJeiPlugin implements IModPlugin {

    private static final Identifier UID = Identifier.fromNamespaceAndPath(MOD_ID, "jei_plugin");
    private static final Set<String> REGISTERED_RECIPE_KEYS = new HashSet<>();
    private static @Nullable IJeiRuntime runtime;

    @Override
    public @NotNull Identifier getPluginUid() {
        return UID;
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IGuiHelper guiHelper = registration.getJeiHelpers().getGuiHelper();
        registration.addRecipeCategories(new CarpentryRecipeCategory(guiHelper));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        List<CarpentryRecipeDisplay.GroupEntry> recipes = getCarpentryRecipes();
        if (!recipes.isEmpty()) {
            registration.addRecipes(NemosCarpentryJeiRecipeTypes.CARPENTRY, recipes);
            recipes.stream().map(NemosCarpentryJeiPlugin::recipeKey).forEach(REGISTERED_RECIPE_KEYS::add);
        }
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addCraftingStation(NemosCarpentryJeiRecipeTypes.CARPENTRY, CarpentryItems.CARPENTERS_WORKBENCH);
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        registration.addRecipeTransferHandler(CarpentryMenu.class, CarpentryMenuTypes.CARPENTRY_SCREEN_HANDLER,
                NemosCarpentryJeiRecipeTypes.CARPENTRY, 0, 2, 3, 36);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(CarpentryScreen.class, 134, 28, 24, 24, NemosCarpentryJeiRecipeTypes.CARPENTRY);
    }

    @Override
    public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
        runtime = jeiRuntime;
        addRecipesAtRuntime(getCarpentryRecipes());
    }

    @Override
    public void onRuntimeUnavailable() {
        runtime = null;
        REGISTERED_RECIPE_KEYS.clear();
    }

    public static void onCarpentryRecipesSynced(CarpentryRecipeDisplay.Grouping grouping) {
        addRecipesAtRuntime(grouping.entries());
    }

    private static List<CarpentryRecipeDisplay.GroupEntry> getCarpentryRecipes() {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.level == null) {
            return List.of();
        }

        CarpentryRecipeDisplay.Grouping grouping = ((CarpentryRecipeManagerGetter) minecraft.level)
                .nemo_sCarpentry$getModRecipeManager()
                .carpentryRecipes();

        return grouping.entries();
    }

    private static void addRecipesAtRuntime(List<CarpentryRecipeDisplay.GroupEntry> recipes) {
        IJeiRuntime currentRuntime = runtime;
        if (currentRuntime == null || recipes.isEmpty()) {
            return;
        }

        List<CarpentryRecipeDisplay.GroupEntry> newRecipes = recipes.stream()
                .filter(recipe -> !REGISTERED_RECIPE_KEYS.contains(recipeKey(recipe)))
                .toList();

        if (!newRecipes.isEmpty()) {
            currentRuntime.getRecipeManager().addRecipes(NemosCarpentryJeiRecipeTypes.CARPENTRY, newRecipes);
            newRecipes.stream().map(NemosCarpentryJeiPlugin::recipeKey).forEach(REGISTERED_RECIPE_KEYS::add);
        }
    }

    private static String recipeKey(CarpentryRecipeDisplay.GroupEntry recipe) {
        return recipe.recipe().recipe()
                .map(recipeHolder -> recipeHolder.id().identifier().toString())
                .orElseGet(() -> recipe.ingredients().stream()
                        .map(ingredient -> ingredient.items()
                                .map(holder -> BuiltInRegistries.ITEM.getKey(holder.value()).toString())
                                .sorted()
                                .collect(Collectors.joining("|")))
                        .collect(Collectors.joining(",")) + ";" + recipe.inputCounts() + "->" + recipe.recipe().optionDisplay());
    }
}
