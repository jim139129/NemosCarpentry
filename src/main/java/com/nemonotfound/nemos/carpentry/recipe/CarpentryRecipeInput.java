package com.nemonotfound.nemos.carpentry.recipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

public record CarpentryRecipeInput(ItemStack first, ItemStack second) implements RecipeInput {
    @Override
    public ItemStack getItem(int slot) {
        return switch (slot) {
            case 0 -> first;
            case 1 -> second;
            default -> throw new IllegalArgumentException("Invalid carpentry input slot: " + slot);
        };
    }

    @Override
    public int size() {
        return 2;
    }
}
