package com.nemonotfound.nemos.carpentry.jei;

import com.nemonotfound.nemos.carpentry.item.CarpentryItems;
import com.nemonotfound.nemos.carpentry.recipe.display.CarpentryRecipeDisplay;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.widgets.IRecipeExtrasBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.category.AbstractRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;

public class CarpentryRecipeCategory extends AbstractRecipeCategory<CarpentryRecipeDisplay.GroupEntry> {

    public static final int WIDTH = 82;
    public static final int HEIGHT = 52;

    public CarpentryRecipeCategory(IGuiHelper guiHelper) {
        super(
                NemosCarpentryJeiRecipeTypes.CARPENTRY,
                Component.translatable("jei.nemos-carpentry.category.carpentry"),
                guiHelper.createDrawableItemLike(CarpentryItems.CARPENTERS_WORKBENCH),
                WIDTH,
                HEIGHT
        );
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, CarpentryRecipeDisplay.GroupEntry recipe, IFocusGroup focuses) {
        List<Ingredient> ingredients = recipe.ingredients();
        List<Integer> inputCounts = recipe.inputCounts();

        addInputSlot(builder, ingredients, inputCounts, 0, 1, ingredients.size() > 1 ? 1 : 9);
        if (ingredients.size() > 1) {
            addInputSlot(builder, ingredients, inputCounts, 1, 1, 27);
        }

        builder.addOutputSlot(61, 18)
                .setOutputSlotBackground()
                .add(recipe.recipe().optionDisplay());
    }

    @Override
    public void createRecipeExtras(IRecipeExtrasBuilder builder, CarpentryRecipeDisplay.GroupEntry recipe, IFocusGroup focuses) {
        if (recipe.ingredients().size() > 1) {
            builder.addRecipePlusSign().setPosition(22, 19);
        }
        builder.addRecipeArrow().setPosition(34, 18);
    }

    @Override
    public Identifier getIdentifier(CarpentryRecipeDisplay.GroupEntry recipe) {
        return recipe.recipe().recipe()
                .map(recipeHolder -> recipeHolder.id().identifier())
                .orElse(null);
    }

    private static void addInputSlot(IRecipeLayoutBuilder builder, List<Ingredient> ingredients, List<Integer> inputCounts, int index, int x, int y) {
        builder.addInputSlot(x, y)
                .setStandardSlotBackground()
                .addItemStacks(copyWithCount(ingredients.get(index), inputCounts.get(index)));
    }

    private static List<ItemStack> copyWithCount(Ingredient ingredient, int count) {
        return ingredient.items()
                .map(holder -> new ItemStack(holder.value(), count))
                .toList();
    }
}
