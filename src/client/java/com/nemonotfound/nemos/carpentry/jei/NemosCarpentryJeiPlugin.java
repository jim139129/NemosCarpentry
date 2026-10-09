package com.nemonotfound.nemos.carpentry.jei;

import com.nemonotfound.nemos.carpentry.client.recipebook.ClientCarpentryRecipeManager;
import com.nemonotfound.nemos.carpentry.item.CarpentryItems;
import com.nemonotfound.nemos.carpentry.recipe.display.CarpentryRecipeDisplay;
import com.nemonotfound.nemos.carpentry.screen.CarpentryMenu;
import com.nemonotfound.nemos.carpentry.screen.CarpentryMenuTypes;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.ingredients.ITypedIngredient;
import mezz.jei.api.recipe.advanced.ISimpleRecipeManagerPlugin;
import mezz.jei.api.registration.*;
import mezz.jei.api.runtime.IJeiRuntime;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

import java.util.List;

import static com.nemonotfound.nemos.carpentry.NemosCarpentry.MOD_ID;

@JeiPlugin
@Environment(EnvType.CLIENT)
public class NemosCarpentryJeiPlugin implements IModPlugin {
    private static final Identifier UID = Identifier.fromNamespaceAndPath(MOD_ID, "jei_plugin");
    private IJeiRuntime runtime;

    @Override
    public Identifier getPluginUid() {
        return UID;
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new CarpentryRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerAdvanced(IAdvancedRegistration registration) {
        // Query the current immutable snapshot. JEI's addRecipes is append-only; using a
        // manager plugin avoids retaining deleted recipes or stale entries with the same ID.
        registration.addSimpleRecipeManagerPlugin(NemosCarpentryJeiRecipeTypes.CARPENTRY, new ISimpleRecipeManagerPlugin<CarpentryRecipeDisplay.GroupEntry>() {
            @Override
            public boolean isHandledInput(ITypedIngredient<?> input) {
                return !getRecipesForInput(input).isEmpty();
            }

            @Override
            public boolean isHandledOutput(ITypedIngredient<?> output) {
                return !getRecipesForOutput(output).isEmpty();
            }

            @Override
            public List<CarpentryRecipeDisplay.GroupEntry> getRecipesForInput(ITypedIngredient<?> input) {
                return input.getItemStack().map(stack -> getAllRecipes().stream()
                        .filter(recipe -> recipe.ingredients().stream().anyMatch(ingredient -> ingredient.test(stack)))
                        .toList()).orElseGet(List::of);
            }

            @Override
            public List<CarpentryRecipeDisplay.GroupEntry> getRecipesForOutput(ITypedIngredient<?> output) {
                return output.getItemStack().map(stack -> getAllRecipes().stream()
                        .filter(recipe -> ItemStack.isSameItemSameComponents(recipe.result().create(), stack))
                        .toList()).orElseGet(List::of);
            }

            @Override
            public List<CarpentryRecipeDisplay.GroupEntry> getAllRecipes() {
                return ClientCarpentryRecipeManager.recipes().entries();
            }
        });
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

    // The workbench has no separate recipe-arrow button. A JEI click area over
    // the result slot intercepts normal crafting clicks, so leave slot input to the screen.

    @Override
    public void onRuntimeAvailable(IJeiRuntime runtime) {
        this.runtime = runtime;
        ClientCarpentryRecipeManager.setListener(this::onRecipesReplaced);
        onRecipesReplaced(ClientCarpentryRecipeManager.recipes());
    }

    @Override
    public void onRuntimeUnavailable() {
        runtime = null;
        ClientCarpentryRecipeManager.setListener(grouping -> {});
    }

    private void onRecipesReplaced(CarpentryRecipeDisplay.Grouping recipes) {
        if (runtime == null) {
            return;
        }
        // These public APIs also invalidate JEI's cached category lookups.
        if (recipes.isEmpty()) {
            runtime.getRecipeManager().hideRecipeCategory(NemosCarpentryJeiRecipeTypes.CARPENTRY);
        } else {
            runtime.getRecipeManager().unhideRecipeCategory(NemosCarpentryJeiRecipeTypes.CARPENTRY);
        }
        // An open recipe screen owns layouts from its last query. Close it through
        // Screen's public lifecycle so its history is cleared before the next query.
        var screen = Minecraft.getInstance().gui.screen();
        if (screen != null && screen == runtime.getRecipesGui()) {
            screen.onClose();
        }
    }
}
