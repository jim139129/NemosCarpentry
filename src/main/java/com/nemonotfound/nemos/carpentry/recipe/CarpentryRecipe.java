package com.nemonotfound.nemos.carpentry.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.nemonotfound.nemos.carpentry.item.CarpentryItems;
import com.nemonotfound.nemos.carpentry.recipe.book.CarpentryRecipeBookCategory;
import com.nemonotfound.nemos.carpentry.recipe.display.CarpentersWorkbenchRecipeDisplay;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import net.minecraft.world.level.Level;

import java.util.List;

public record CarpentryRecipe(List<Ingredient> ingredients, List<Integer> inputCounts,
                             ItemStackTemplate result) implements Recipe<CarpentryRecipeInput> {
    public static final MapCodec<CarpentryRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Ingredient.CODEC.listOf(1, 2).fieldOf("ingredients").forGetter(CarpentryRecipe::ingredients),
            Codec.intRange(1, Integer.MAX_VALUE).listOf(1, 2).fieldOf("inputCounts").forGetter(CarpentryRecipe::inputCounts),
            ItemStackTemplate.CODEC.fieldOf("result").forGetter(CarpentryRecipe::result)
    ).apply(instance, CarpentryRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, CarpentryRecipe> STREAM_CODEC = StreamCodec.composite(
            Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.list(2)), CarpentryRecipe::ingredients,
            ByteBufCodecs.VAR_INT.apply(ByteBufCodecs.list(2)), CarpentryRecipe::inputCounts,
            ItemStackTemplate.STREAM_CODEC, CarpentryRecipe::result,
            CarpentryRecipe::new
    );

    public CarpentryRecipe {
        ingredients = List.copyOf(ingredients);
        inputCounts = List.copyOf(inputCounts);
        validateInputs(ingredients, inputCounts);
        java.util.Objects.requireNonNull(result, "result");
    }

    public static void validateInputs(List<Ingredient> ingredients, List<Integer> counts) {
        if (ingredients.isEmpty() || ingredients.size() > 2 || ingredients.size() != counts.size()
                || counts.stream().anyMatch(count -> count < 1)) {
            throw new IllegalArgumentException("Carpentry requires one or two ingredients with matching positive counts");
        }
    }

    @Override
    public RecipeSerializer<CarpentryRecipe> getSerializer() {
        return CarpentryRecipeSerializer.CARPENTRY;
    }

    @Override
    public RecipeType<CarpentryRecipe> getType() {
        return CarpentryRecipeTypes.CARPENTRY;
    }

    @Override
    public List<RecipeDisplay> display() {
        return List.of(new CarpentersWorkbenchRecipeDisplay(ingredients.stream().map(Ingredient::display).toList(),
                createResultDisplay(), new SlotDisplay.ItemSlotDisplay(CarpentryItems.CARPENTERS_WORKBENCH)));
    }

    public SlotDisplay createResultDisplay() {
        return new SlotDisplay.ItemStackSlotDisplay(result);
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return CarpentryRecipeBookCategory.CARPENTERS_WORKBENCH;
    }

    @Override
    public boolean matches(CarpentryRecipeInput input, Level level) {
        for (int slot = 0; slot < ingredients.size(); slot++) {
            if (!ingredients.get(slot).test(input.getItem(slot))
                    || input.getItem(slot).getCount() < inputCounts.get(slot)) {
                return false;
            }
        }
        // A single-material recipe leaves anything in the second slot untouched.
        return true;
    }

    @Override
    public ItemStack assemble(CarpentryRecipeInput input) {
        return result.create();
    }

    @Override
    public PlacementInfo placementInfo() {
        return PlacementInfo.create(ingredients);
    }

    @Override
    public String group() {
        return "";
    }

    @Override
    public boolean showNotification() {
        return true;
    }
}
