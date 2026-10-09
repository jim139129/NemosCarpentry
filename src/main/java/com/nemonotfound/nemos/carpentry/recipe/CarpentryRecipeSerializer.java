package com.nemonotfound.nemos.carpentry.recipe;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;

import static com.nemonotfound.nemos.carpentry.NemosCarpentry.MOD_ID;
import static com.nemonotfound.nemos.carpentry.NemosCarpentry.log;

public class CarpentryRecipeSerializer {

    public static final RecipeSerializer<CarpentryRecipe> CARPENTRY = register("carpentry",
            new RecipeSerializer<>(CarpentryRecipe.CODEC, CarpentryRecipe.STREAM_CODEC));

    public static void registerRecipeSerializer() {
        log.info("Register recipe serializer");
    }

    private static <S extends RecipeSerializer<T>, T extends Recipe<?>> S register(String path, S serializer) {
        return Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, Identifier.fromNamespaceAndPath(MOD_ID, path), serializer);
    }
}
