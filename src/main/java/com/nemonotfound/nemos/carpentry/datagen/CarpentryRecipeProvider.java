package com.nemonotfound.nemos.carpentry.datagen;

import com.nemonotfound.nemos.carpentry.block.CarpentryBlocks;
import com.nemonotfound.nemos.carpentry.item.CarpentryItems;
import com.nemonotfound.nemos.carpentry.recipe.CarpentryRecipeBuilder;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.advancements.Advancement;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.tags.BlockItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CarpentryRecipeProvider extends FabricRecipeProvider {

    public CarpentryRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected @NotNull RecipeProvider createRecipeProvider(HolderLookup.@NotNull Provider provider,
            BootstrapContext<Recipe<?>> recipes, BootstrapContext<Advancement> advancements) {
        return new RecipeProvider(recipes, advancements) {

            @Override
            public void buildRecipes() {
                this.shaped(RecipeCategory.DECORATIONS, CarpentryBlocks.CARPENTERS_WORKBENCH)
                        .define('#', BlockItemTags.PLANKS.item())
                        .define('@', Items.CRAFTING_TABLE)
                        .pattern("@@")
                        .pattern("##")
                        .pattern("##")
                        .unlockedBy("has_crafting_table", has(Items.CRAFTING_TABLE))
                        .save(output);
                createBoundBambooLadderReceipe(output);

                createCarpentryRecipe(List.of(Blocks.ACACIA_PLANKS), List.of(1), CarpentryBlocks.ACACIA_LADDER, 2);
                createCarpentryRecipe(List.of(Blocks.BAMBOO_PLANKS), List.of(1), CarpentryBlocks.BAMBOO_LADDER, 2);
                createCarpentryRecipe(List.of(Blocks.BIRCH_PLANKS), List.of(1), CarpentryBlocks.BIRCH_LADDER, 2);
                createCarpentryRecipe(List.of(Blocks.CHERRY_PLANKS), List.of(1), CarpentryBlocks.CHERRY_LADDER, 2);
                createCarpentryRecipe(List.of(Blocks.CRIMSON_PLANKS), List.of(1), CarpentryBlocks.CRIMSON_LADDER, 2);
                createCarpentryRecipe(List.of(Blocks.DARK_OAK_PLANKS), List.of(1), CarpentryBlocks.DARK_OAK_LADDER, 2);
                createCarpentryRecipe(List.of(Blocks.PALE_OAK_PLANKS), List.of(1), CarpentryBlocks.PALE_OAK_LADDER, 2);
                createCarpentryRecipe(List.of(Blocks.JUNGLE_PLANKS), List.of(1), CarpentryBlocks.JUNGLE_LADDER, 2);
                createCarpentryRecipe(List.of(Blocks.MANGROVE_PLANKS), List.of(1), CarpentryBlocks.MANGROVE_LADDER, 2);
                createCarpentryRecipe(List.of(Blocks.OAK_PLANKS), List.of(1), Blocks.LADDER, 2);
                createCarpentryRecipe(List.of(Blocks.SPRUCE_PLANKS), List.of(1), CarpentryBlocks.SPRUCE_LADDER, 2);
                createCarpentryRecipe(List.of(Blocks.WARPED_PLANKS), List.of(1), CarpentryBlocks.WARPED_LADDER, 2);

                createCarpentryRecipe(List.of(Blocks.ACACIA_PLANKS), List.of(1), CarpentryBlocks.ACACIA_CHAIR, 1);
                createCarpentryRecipe(List.of(Blocks.BAMBOO_PLANKS), List.of(1), CarpentryBlocks.BAMBOO_CHAIR, 1);
                createCarpentryRecipe(List.of(Blocks.BIRCH_PLANKS), List.of(1), CarpentryBlocks.BIRCH_CHAIR, 1);
                createCarpentryRecipe(List.of(Blocks.CHERRY_PLANKS), List.of(1), CarpentryBlocks.CHERRY_CHAIR, 1);
                createCarpentryRecipe(List.of(Blocks.CRIMSON_PLANKS), List.of(1), CarpentryBlocks.CRIMSON_CHAIR, 1);
                createCarpentryRecipe(List.of(Blocks.DARK_OAK_PLANKS), List.of(1), CarpentryBlocks.DARK_OAK_CHAIR, 1);
                createCarpentryRecipe(List.of(Blocks.PALE_OAK_PLANKS), List.of(1), CarpentryBlocks.PALE_OAK_CHAIR, 1);
                createCarpentryRecipe(List.of(Blocks.JUNGLE_PLANKS), List.of(1), CarpentryBlocks.JUNGLE_CHAIR, 1);
                createCarpentryRecipe(List.of(Blocks.MANGROVE_PLANKS), List.of(1), CarpentryBlocks.MANGROVE_CHAIR, 1);
                createCarpentryRecipe(List.of(Blocks.OAK_PLANKS), List.of(1), CarpentryBlocks.OAK_CHAIR, 1);
                createCarpentryRecipe(List.of(Blocks.SPRUCE_PLANKS), List.of(1), CarpentryBlocks.SPRUCE_CHAIR, 1);
                createCarpentryRecipe(List.of(Blocks.WARPED_PLANKS), List.of(1), CarpentryBlocks.WARPED_CHAIR, 1);

                createCarpentryRecipe(List.of(Blocks.ACACIA_PLANKS), List.of(1), CarpentryBlocks.ACACIA_CHAIR_FELIX, 1);
                createCarpentryRecipe(List.of(Blocks.BAMBOO_PLANKS), List.of(1), CarpentryBlocks.BAMBOO_CHAIR_FELIX, 1);
                createCarpentryRecipe(List.of(Blocks.BIRCH_PLANKS), List.of(1), CarpentryBlocks.BIRCH_CHAIR_FELIX, 1);
                createCarpentryRecipe(List.of(Blocks.CHERRY_PLANKS), List.of(1), CarpentryBlocks.CHERRY_CHAIR_FELIX, 1);
                createCarpentryRecipe(List.of(Blocks.CRIMSON_PLANKS), List.of(1), CarpentryBlocks.CRIMSON_CHAIR_FELIX, 1);
                createCarpentryRecipe(List.of(Blocks.DARK_OAK_PLANKS), List.of(1), CarpentryBlocks.DARK_OAK_CHAIR_FELIX, 1);
                createCarpentryRecipe(List.of(Blocks.PALE_OAK_PLANKS), List.of(1), CarpentryBlocks.PALE_OAK_CHAIR_FELIX, 1);
                createCarpentryRecipe(List.of(Blocks.JUNGLE_PLANKS), List.of(1), CarpentryBlocks.JUNGLE_CHAIR_FELIX, 1);
                createCarpentryRecipe(List.of(Blocks.MANGROVE_PLANKS), List.of(1), CarpentryBlocks.MANGROVE_CHAIR_FELIX, 1);
                createCarpentryRecipe(List.of(Blocks.OAK_PLANKS), List.of(1), CarpentryBlocks.OAK_CHAIR_FELIX, 1);
                createCarpentryRecipe(List.of(Blocks.SPRUCE_PLANKS), List.of(1), CarpentryBlocks.SPRUCE_CHAIR_FELIX, 1);
                createCarpentryRecipe(List.of(Blocks.WARPED_PLANKS), List.of(1), CarpentryBlocks.WARPED_CHAIR_FELIX, 1);

                createCarpentryRecipe(List.of(Blocks.ACACIA_LOG), List.of(1), CarpentryBlocks.ACACIA_LOG_SEAT, 2);
                createCarpentryRecipe(List.of(Blocks.BIRCH_LOG), List.of(1), CarpentryBlocks.BIRCH_LOG_SEAT, 2);
                createCarpentryRecipe(List.of(Blocks.CHERRY_LOG), List.of(1), CarpentryBlocks.CHERRY_LOG_SEAT, 2);
                createCarpentryRecipe(List.of(Blocks.CRIMSON_STEM), List.of(1), CarpentryBlocks.CRIMSON_STEM_SEAT, 2);
                createCarpentryRecipe(List.of(Blocks.DARK_OAK_LOG), List.of(1), CarpentryBlocks.DARK_OAK_LOG_SEAT, 2);
                createCarpentryRecipe(List.of(Blocks.PALE_OAK_LOG), List.of(1), CarpentryBlocks.PALE_OAK_LOG_SEAT, 2);
                createCarpentryRecipe(List.of(Blocks.JUNGLE_LOG), List.of(1), CarpentryBlocks.JUNGLE_LOG_SEAT, 2);
                createCarpentryRecipe(List.of(Blocks.MANGROVE_LOG), List.of(1), CarpentryBlocks.MANGROVE_LOG_SEAT, 2);
                createCarpentryRecipe(List.of(Blocks.OAK_LOG), List.of(1), CarpentryBlocks.OAK_LOG_SEAT, 2);
                createCarpentryRecipe(List.of(Blocks.SPRUCE_LOG), List.of(1), CarpentryBlocks.SPRUCE_LOG_SEAT, 2);
                createCarpentryRecipe(List.of(Blocks.WARPED_STEM), List.of(1), CarpentryBlocks.WARPED_STEM_SEAT, 2);

                createCarpentryRecipe(List.of(Blocks.ACACIA_PLANKS), List.of(2), CarpentryBlocks.ACACIA_PARK_BENCH, 1);
                createCarpentryRecipe(List.of(Blocks.BAMBOO_PLANKS), List.of(2), CarpentryBlocks.BAMBOO_PARK_BENCH, 1);
                createCarpentryRecipe(List.of(Blocks.BIRCH_PLANKS), List.of(2), CarpentryBlocks.BIRCH_PARK_BENCH, 1);
                createCarpentryRecipe(List.of(Blocks.CHERRY_PLANKS), List.of(2), CarpentryBlocks.CHERRY_PARK_BENCH, 1);
                createCarpentryRecipe(List.of(Blocks.CRIMSON_PLANKS), List.of(2), CarpentryBlocks.CRIMSON_PARK_BENCH, 1);
                createCarpentryRecipe(List.of(Blocks.DARK_OAK_PLANKS), List.of(2), CarpentryBlocks.DARK_OAK_PARK_BENCH, 1);
                createCarpentryRecipe(List.of(Blocks.PALE_OAK_PLANKS), List.of(2), CarpentryBlocks.PALE_OAK_PARK_BENCH, 1);
                createCarpentryRecipe(List.of(Blocks.JUNGLE_PLANKS), List.of(2), CarpentryBlocks.JUNGLE_PARK_BENCH, 1);
                createCarpentryRecipe(List.of(Blocks.MANGROVE_PLANKS), List.of(2), CarpentryBlocks.MANGROVE_PARK_BENCH, 1);
                createCarpentryRecipe(List.of(Blocks.OAK_PLANKS), List.of(2), CarpentryBlocks.OAK_PARK_BENCH, 1);
                createCarpentryRecipe(List.of(Blocks.SPRUCE_PLANKS), List.of(2), CarpentryBlocks.SPRUCE_PARK_BENCH, 1);
                createCarpentryRecipe(List.of(Blocks.WARPED_PLANKS), List.of(2), CarpentryBlocks.WARPED_PARK_BENCH, 1);

                createCarpentryRecipe(List.of(Blocks.ACACIA_PLANKS), List.of(2), CarpentryItems.ACACIA_CHAIR_LUKAS, 1);
                createCarpentryRecipe(List.of(Blocks.BAMBOO_PLANKS), List.of(2), CarpentryItems.BAMBOO_CHAIR_LUKAS, 1);
                createCarpentryRecipe(List.of(Blocks.BIRCH_PLANKS), List.of(2), CarpentryItems.BIRCH_CHAIR_LUKAS, 1);
                createCarpentryRecipe(List.of(Blocks.CHERRY_PLANKS), List.of(2), CarpentryItems.CHERRY_CHAIR_LUKAS, 1);
                createCarpentryRecipe(List.of(Blocks.CRIMSON_PLANKS), List.of(2), CarpentryItems.CRIMSON_CHAIR_LUKAS, 1);
                createCarpentryRecipe(List.of(Blocks.DARK_OAK_PLANKS), List.of(2), CarpentryItems.DARK_OAK_CHAIR_LUKAS, 1);
                createCarpentryRecipe(List.of(Blocks.PALE_OAK_PLANKS), List.of(2), CarpentryItems.PALE_OAK_CHAIR_LUKAS, 1);
                createCarpentryRecipe(List.of(Blocks.JUNGLE_PLANKS), List.of(2), CarpentryItems.JUNGLE_CHAIR_LUKAS, 1);
                createCarpentryRecipe(List.of(Blocks.MANGROVE_PLANKS), List.of(2), CarpentryItems.MANGROVE_CHAIR_LUKAS, 1);
                createCarpentryRecipe(List.of(Blocks.OAK_PLANKS), List.of(2), CarpentryItems.OAK_CHAIR_LUKAS, 1);
                createCarpentryRecipe(List.of(Blocks.SPRUCE_PLANKS), List.of(2), CarpentryItems.SPRUCE_CHAIR_LUKAS, 1);
                createCarpentryRecipe(List.of(Blocks.WARPED_PLANKS), List.of(2), CarpentryItems.WARPED_CHAIR_LUKAS, 1);

                createCarpentryRecipe(List.of(Blocks.CHERRY_PLANKS), List.of(2), CarpentryItems.CHERRY_GLASS_DOOR_FRAME, 2);

                createTableTheClassicRecipes();
                createStandingTableRecipes();
                createChairLukasNaturalRecipes();
                createCoffeeTableRecipes();
                createBarrelSeatRecipes();
                createChairTommyRecipes();
                createChairTommyNaturalRecipes();
                createChairGregoryRecipes();
                createBarberChairRecipes();
                createBarChairRecipes();
                createTablePascalRecipes();
                createPascalChairRecipes();
            }

            private void createTableTheClassicRecipes() {
                createCarpentryRecipe(List.of(Blocks.ACACIA_PLANKS), List.of(1), CarpentryBlocks.ACACIA_TABLE_THE_CLASSIC, 1);
                createCarpentryRecipe(List.of(Blocks.BAMBOO_PLANKS), List.of(1), CarpentryBlocks.BAMBOO_TABLE_THE_CLASSIC, 1);
                createCarpentryRecipe(List.of(Blocks.BIRCH_PLANKS), List.of(1), CarpentryBlocks.BIRCH_TABLE_THE_CLASSIC, 1);
                createCarpentryRecipe(List.of(Blocks.CHERRY_PLANKS), List.of(1), CarpentryBlocks.CHERRY_TABLE_THE_CLASSIC, 1);
                createCarpentryRecipe(List.of(Blocks.CRIMSON_PLANKS), List.of(1), CarpentryBlocks.CRIMSON_TABLE_THE_CLASSIC, 1);
                createCarpentryRecipe(List.of(Blocks.DARK_OAK_PLANKS), List.of(1), CarpentryBlocks.DARK_OAK_TABLE_THE_CLASSIC, 1);
                createCarpentryRecipe(List.of(Blocks.PALE_OAK_PLANKS), List.of(1), CarpentryBlocks.PALE_OAK_TABLE_THE_CLASSIC, 1);
                createCarpentryRecipe(List.of(Blocks.JUNGLE_PLANKS), List.of(1), CarpentryBlocks.JUNGLE_TABLE_THE_CLASSIC, 1);
                createCarpentryRecipe(List.of(Blocks.MANGROVE_PLANKS), List.of(1), CarpentryBlocks.MANGROVE_TABLE_THE_CLASSIC, 1);
                createCarpentryRecipe(List.of(Blocks.OAK_PLANKS), List.of(1), CarpentryBlocks.OAK_TABLE_THE_CLASSIC, 1);
                createCarpentryRecipe(List.of(Blocks.SPRUCE_PLANKS), List.of(1), CarpentryBlocks.SPRUCE_TABLE_THE_CLASSIC, 1);
                createCarpentryRecipe(List.of(Blocks.WARPED_PLANKS), List.of(1), CarpentryBlocks.WARPED_TABLE_THE_CLASSIC, 1);
            }

            private void createTablePascalRecipes() {
                createCarpentryRecipe(List.of(Blocks.ACACIA_PLANKS, Blocks.IRON_BLOCK), List.of(1, 1), CarpentryBlocks.ACACIA_COFFEE_TABLE_PASCAL, 4);
                createCarpentryRecipe(List.of(Blocks.BAMBOO_PLANKS, Blocks.IRON_BLOCK), List.of(1, 1), CarpentryBlocks.BAMBOO_COFFEE_TABLE_PASCAL, 4);
                createCarpentryRecipe(List.of(Blocks.BIRCH_PLANKS, Blocks.IRON_BLOCK), List.of(1, 1), CarpentryBlocks.BIRCH_COFFEE_TABLE_PASCAL, 4);
                createCarpentryRecipe(List.of(Blocks.CHERRY_PLANKS, Blocks.IRON_BLOCK), List.of(1, 1), CarpentryBlocks.CHERRY_COFFEE_TABLE_PASCAL, 4);
                createCarpentryRecipe(List.of(Blocks.CRIMSON_PLANKS, Blocks.IRON_BLOCK), List.of(1, 1), CarpentryBlocks.CRIMSON_COFFEE_TABLE_PASCAL, 4);
                createCarpentryRecipe(List.of(Blocks.DARK_OAK_PLANKS, Blocks.IRON_BLOCK), List.of(1, 1), CarpentryBlocks.DARK_OAK_COFFEE_TABLE_PASCAL, 4);
                createCarpentryRecipe(List.of(Blocks.PALE_OAK_PLANKS, Blocks.IRON_BLOCK), List.of(1, 1), CarpentryBlocks.PALE_OAK_COFFEE_TABLE_PASCAL, 4);
                createCarpentryRecipe(List.of(Blocks.JUNGLE_PLANKS, Blocks.IRON_BLOCK), List.of(1, 1), CarpentryBlocks.JUNGLE_COFFEE_TABLE_PASCAL, 4);
                createCarpentryRecipe(List.of(Blocks.MANGROVE_PLANKS, Blocks.IRON_BLOCK), List.of(1, 1), CarpentryBlocks.MANGROVE_COFFEE_TABLE_PASCAL, 4);
                createCarpentryRecipe(List.of(Blocks.OAK_PLANKS, Blocks.IRON_BLOCK), List.of(1, 1), CarpentryBlocks.OAK_COFFEE_TABLE_PASCAL, 4);
                createCarpentryRecipe(List.of(Blocks.SPRUCE_PLANKS, Blocks.IRON_BLOCK), List.of(1, 1), CarpentryBlocks.SPRUCE_COFFEE_TABLE_PASCAL, 4);
                createCarpentryRecipe(List.of(Blocks.WARPED_PLANKS, Blocks.IRON_BLOCK), List.of(1, 1), CarpentryBlocks.WARPED_COFFEE_TABLE_PASCAL, 4);
            }

            private void createStandingTableRecipes() {
                createCarpentryRecipe(List.of(Blocks.ACACIA_LOG), List.of(1), CarpentryBlocks.ACACIA_STANDING_TABLE, 1);
                createCarpentryRecipe(List.of(Blocks.BAMBOO_BLOCK), List.of(1), CarpentryBlocks.BAMBOO_STANDING_TABLE, 1);
                createCarpentryRecipe(List.of(Blocks.BIRCH_LOG), List.of(1), CarpentryBlocks.BIRCH_STANDING_TABLE, 1);
                createCarpentryRecipe(List.of(Blocks.CHERRY_LOG), List.of(1), CarpentryBlocks.CHERRY_STANDING_TABLE, 1);
                createCarpentryRecipe(List.of(Blocks.CRIMSON_STEM), List.of(1), CarpentryBlocks.CRIMSON_STANDING_TABLE, 1);
                createCarpentryRecipe(List.of(Blocks.DARK_OAK_LOG), List.of(1), CarpentryBlocks.DARK_OAK_STANDING_TABLE, 1);
                createCarpentryRecipe(List.of(Blocks.PALE_OAK_LOG), List.of(1), CarpentryBlocks.PALE_OAK_STANDING_TABLE, 1);
                createCarpentryRecipe(List.of(Blocks.JUNGLE_LOG), List.of(1), CarpentryBlocks.JUNGLE_STANDING_TABLE, 1);
                createCarpentryRecipe(List.of(Blocks.MANGROVE_LOG), List.of(1), CarpentryBlocks.MANGROVE_STANDING_TABLE, 1);
                createCarpentryRecipe(List.of(Blocks.OAK_LOG), List.of(1), CarpentryBlocks.OAK_STANDING_TABLE, 1);
                createCarpentryRecipe(List.of(Blocks.SPRUCE_LOG), List.of(1), CarpentryBlocks.SPRUCE_STANDING_TABLE, 1);
                createCarpentryRecipe(List.of(Blocks.WARPED_STEM), List.of(1), CarpentryBlocks.WARPED_STANDING_TABLE, 1);
            }

            private void createChairLukasNaturalRecipes() {
                createCarpentryRecipe(Blocks.ACACIA_PLANKS, BlockItemTags.ACACIA_LOGS.item(), List.of(1, 1), CarpentryItems.ACACIA_CHAIR_LUKAS_NATURAL, 1);
                createCarpentryRecipe(Blocks.BAMBOO_PLANKS, BlockItemTags.BAMBOO_BLOCKS.item(), List.of(1, 1), CarpentryItems.BAMBOO_CHAIR_LUKAS_NATURAL, 1);
                createCarpentryRecipe(Blocks.BIRCH_PLANKS, BlockItemTags.BIRCH_LOGS.item(), List.of(1, 1), CarpentryItems.BIRCH_CHAIR_LUKAS_NATURAL, 1);
                createCarpentryRecipe(Blocks.CHERRY_PLANKS, BlockItemTags.CHERRY_LOGS.item(), List.of(1, 1), CarpentryItems.CHERRY_CHAIR_LUKAS_NATURAL, 1);
                createCarpentryRecipe(Blocks.CRIMSON_PLANKS, BlockItemTags.CRIMSON_STEMS.item(), List.of(1, 1), CarpentryItems.CRIMSON_CHAIR_LUKAS_NATURAL, 1);
                createCarpentryRecipe(Blocks.DARK_OAK_PLANKS, BlockItemTags.DARK_OAK_LOGS.item(), List.of(1, 1), CarpentryItems.DARK_OAK_CHAIR_LUKAS_NATURAL, 1);
                createCarpentryRecipe(Blocks.PALE_OAK_PLANKS, BlockItemTags.PALE_OAK_LOGS.item(), List.of(1, 1), CarpentryItems.PALE_OAK_CHAIR_LUKAS_NATURAL, 1);
                createCarpentryRecipe(Blocks.JUNGLE_PLANKS, BlockItemTags.JUNGLE_LOGS.item(), List.of(1, 1), CarpentryItems.JUNGLE_CHAIR_LUKAS_NATURAL, 1);
                createCarpentryRecipe(Blocks.MANGROVE_PLANKS, BlockItemTags.MANGROVE_LOGS.item(), List.of(1, 1), CarpentryItems.MANGROVE_CHAIR_LUKAS_NATURAL, 1);
                createCarpentryRecipe(Blocks.OAK_PLANKS, BlockItemTags.OAK_LOGS.item(), List.of(1, 1), CarpentryItems.OAK_CHAIR_LUKAS_NATURAL, 1);
                createCarpentryRecipe(Blocks.SPRUCE_PLANKS, BlockItemTags.SPRUCE_LOGS.item(), List.of(1, 1), CarpentryItems.SPRUCE_CHAIR_LUKAS_NATURAL, 1);
                createCarpentryRecipe(Blocks.WARPED_PLANKS, BlockItemTags.WARPED_STEMS.item(), List.of(1, 1), CarpentryItems.WARPED_CHAIR_LUKAS_NATURAL, 1);
            }

            private void createChairTommyRecipes() {
                createCarpentryRecipe(List.of(Blocks.ACACIA_PLANKS), List.of(2), CarpentryItems.ACACIA_CHAIR_TOMMY, 1);
                createCarpentryRecipe(List.of(Blocks.BAMBOO_PLANKS), List.of(2), CarpentryItems.BAMBOO_CHAIR_TOMMY, 1);
                createCarpentryRecipe(List.of(Blocks.BIRCH_PLANKS), List.of(2), CarpentryItems.BIRCH_CHAIR_TOMMY, 1);
                createCarpentryRecipe(List.of(Blocks.CHERRY_PLANKS), List.of(2), CarpentryItems.CHERRY_CHAIR_TOMMY, 1);
                createCarpentryRecipe(List.of(Blocks.CRIMSON_PLANKS), List.of(2), CarpentryItems.CRIMSON_CHAIR_TOMMY, 1);
                createCarpentryRecipe(List.of(Blocks.DARK_OAK_PLANKS), List.of(2), CarpentryItems.DARK_OAK_CHAIR_TOMMY, 1);
                createCarpentryRecipe(List.of(Blocks.PALE_OAK_PLANKS), List.of(2), CarpentryItems.PALE_OAK_CHAIR_TOMMY, 1);
                createCarpentryRecipe(List.of(Blocks.JUNGLE_PLANKS), List.of(2), CarpentryItems.JUNGLE_CHAIR_TOMMY, 1);
                createCarpentryRecipe(List.of(Blocks.MANGROVE_PLANKS), List.of(2), CarpentryItems.MANGROVE_CHAIR_TOMMY, 1);
                createCarpentryRecipe(List.of(Blocks.OAK_PLANKS), List.of(2), CarpentryItems.OAK_CHAIR_TOMMY, 1);
                createCarpentryRecipe(List.of(Blocks.SPRUCE_PLANKS), List.of(2), CarpentryItems.SPRUCE_CHAIR_TOMMY, 1);
                createCarpentryRecipe(List.of(Blocks.WARPED_PLANKS), List.of(2), CarpentryItems.WARPED_CHAIR_TOMMY, 1);
            }

            private void createChairTommyNaturalRecipes() {
                createCarpentryRecipe(Blocks.ACACIA_PLANKS, BlockItemTags.ACACIA_LOGS.item(), List.of(1, 1), CarpentryItems.ACACIA_CHAIR_TOMMY_NATURAL, 1);
                createCarpentryRecipe(Blocks.BAMBOO_PLANKS, BlockItemTags.BAMBOO_BLOCKS.item(), List.of(1, 1), CarpentryItems.BAMBOO_CHAIR_TOMMY_NATURAL, 1);
                createCarpentryRecipe(Blocks.BIRCH_PLANKS, BlockItemTags.BIRCH_LOGS.item(), List.of(1, 1), CarpentryItems.BIRCH_CHAIR_TOMMY_NATURAL, 1);
                createCarpentryRecipe(Blocks.CHERRY_PLANKS, BlockItemTags.CHERRY_LOGS.item(), List.of(1, 1), CarpentryItems.CHERRY_CHAIR_TOMMY_NATURAL, 1);
                createCarpentryRecipe(Blocks.CRIMSON_PLANKS, BlockItemTags.CRIMSON_STEMS.item(), List.of(1, 1), CarpentryItems.CRIMSON_CHAIR_TOMMY_NATURAL, 1);
                createCarpentryRecipe(Blocks.DARK_OAK_PLANKS, BlockItemTags.DARK_OAK_LOGS.item(), List.of(1, 1), CarpentryItems.DARK_OAK_CHAIR_TOMMY_NATURAL, 1);
                createCarpentryRecipe(Blocks.PALE_OAK_PLANKS, BlockItemTags.PALE_OAK_LOGS.item(), List.of(1, 1), CarpentryItems.PALE_OAK_CHAIR_TOMMY_NATURAL, 1);
                createCarpentryRecipe(Blocks.JUNGLE_PLANKS, BlockItemTags.JUNGLE_LOGS.item(), List.of(1, 1), CarpentryItems.JUNGLE_CHAIR_TOMMY_NATURAL, 1);
                createCarpentryRecipe(Blocks.MANGROVE_PLANKS, BlockItemTags.MANGROVE_LOGS.item(), List.of(1, 1), CarpentryItems.MANGROVE_CHAIR_TOMMY_NATURAL, 1);
                createCarpentryRecipe(Blocks.OAK_PLANKS, BlockItemTags.OAK_LOGS.item(), List.of(1, 1), CarpentryItems.OAK_CHAIR_TOMMY_NATURAL, 1);
                createCarpentryRecipe(Blocks.SPRUCE_PLANKS, BlockItemTags.SPRUCE_LOGS.item(), List.of(1, 1), CarpentryItems.SPRUCE_CHAIR_TOMMY_NATURAL, 1);
                createCarpentryRecipe(Blocks.WARPED_PLANKS, BlockItemTags.WARPED_STEMS.item(), List.of(1, 1), CarpentryItems.WARPED_CHAIR_TOMMY_NATURAL, 1);
            }

            private void createCoffeeTableRecipes() {
                createCarpentryRecipe(Blocks.ACACIA_PLANKS, BlockItemTags.ACACIA_LOGS.item(), List.of(1, 1), CarpentryItems.ACACIA_COFFEE_TABLE, 2);
                createCarpentryRecipe(Blocks.BAMBOO_PLANKS, BlockItemTags.BAMBOO_BLOCKS.item(), List.of(1, 1), CarpentryItems.BAMBOO_COFFEE_TABLE, 2);
                createCarpentryRecipe(Blocks.BIRCH_PLANKS, BlockItemTags.BIRCH_LOGS.item(), List.of(1, 1), CarpentryItems.BIRCH_COFFEE_TABLE, 2);
                createCarpentryRecipe(Blocks.CHERRY_PLANKS, BlockItemTags.CHERRY_LOGS.item(), List.of(1, 1), CarpentryItems.CHERRY_COFFEE_TABLE, 2);
                createCarpentryRecipe(Blocks.CRIMSON_PLANKS, BlockItemTags.CRIMSON_STEMS.item(), List.of(1, 1), CarpentryItems.CRIMSON_COFFEE_TABLE, 2);
                createCarpentryRecipe(Blocks.DARK_OAK_PLANKS, BlockItemTags.DARK_OAK_LOGS.item(), List.of(1, 1), CarpentryItems.DARK_OAK_COFFEE_TABLE, 2);
                createCarpentryRecipe(Blocks.PALE_OAK_PLANKS, BlockItemTags.PALE_OAK_LOGS.item(), List.of(1, 1), CarpentryItems.PALE_OAK_COFFEE_TABLE, 2);
                createCarpentryRecipe(Blocks.JUNGLE_PLANKS, BlockItemTags.JUNGLE_LOGS.item(), List.of(1, 1), CarpentryItems.JUNGLE_COFFEE_TABLE, 2);
                createCarpentryRecipe(Blocks.MANGROVE_PLANKS, BlockItemTags.MANGROVE_LOGS.item(), List.of(1, 1), CarpentryItems.MANGROVE_COFFEE_TABLE, 2);
                createCarpentryRecipe(Blocks.OAK_PLANKS, BlockItemTags.OAK_LOGS.item(), List.of(1, 1), CarpentryItems.OAK_COFFEE_TABLE, 2);
                createCarpentryRecipe(Blocks.SPRUCE_PLANKS, BlockItemTags.SPRUCE_LOGS.item(), List.of(1, 1), CarpentryItems.SPRUCE_COFFEE_TABLE, 2);
                createCarpentryRecipe(Blocks.WARPED_PLANKS, BlockItemTags.WARPED_STEMS.item(), List.of(1, 1), CarpentryItems.WARPED_COFFEE_TABLE, 2);
            }

            private void createBarrelSeatRecipes() {
                createCarpentryRecipe(List.of(Blocks.ACACIA_PLANKS, Items.IRON_INGOT), List.of(1, 1), CarpentryItems.ACACIA_BARREL_SEAT, 1);
                createCarpentryRecipe(List.of(Blocks.BAMBOO_PLANKS, Items.IRON_INGOT), List.of(1, 1), CarpentryItems.BAMBOO_BARREL_SEAT, 1);
                createCarpentryRecipe(List.of(Blocks.BIRCH_PLANKS, Items.IRON_INGOT), List.of(1, 1), CarpentryItems.BIRCH_BARREL_SEAT, 1);
                createCarpentryRecipe(List.of(Blocks.CHERRY_PLANKS, Items.IRON_INGOT), List.of(1, 1), CarpentryItems.CHERRY_BARREL_SEAT, 1);
                createCarpentryRecipe(List.of(Blocks.CRIMSON_PLANKS, Items.IRON_INGOT), List.of(1, 1), CarpentryItems.CRIMSON_BARREL_SEAT, 1);
                createCarpentryRecipe(List.of(Blocks.DARK_OAK_PLANKS, Items.IRON_INGOT), List.of(1, 1), CarpentryItems.DARK_OAK_BARREL_SEAT, 1);
                createCarpentryRecipe(List.of(Blocks.PALE_OAK_PLANKS, Items.IRON_INGOT), List.of(1, 1), CarpentryItems.PALE_OAK_BARREL_SEAT, 1);
                createCarpentryRecipe(List.of(Blocks.JUNGLE_PLANKS, Items.IRON_INGOT), List.of(1, 1), CarpentryItems.JUNGLE_BARREL_SEAT, 1);
                createCarpentryRecipe(List.of(Blocks.MANGROVE_PLANKS, Items.IRON_INGOT), List.of(1, 1), CarpentryItems.MANGROVE_BARREL_SEAT, 1);
                createCarpentryRecipe(List.of(Blocks.OAK_PLANKS, Items.IRON_INGOT), List.of(1, 1), CarpentryItems.OAK_BARREL_SEAT, 1);
                createCarpentryRecipe(List.of(Blocks.SPRUCE_PLANKS, Items.IRON_INGOT), List.of(1, 1), CarpentryItems.SPRUCE_BARREL_SEAT, 1);
                createCarpentryRecipe(List.of(Blocks.WARPED_PLANKS, Items.IRON_INGOT), List.of(1, 1), CarpentryItems.WARPED_BARREL_SEAT, 1);
            }

            private void createChairGregoryRecipes() {
                createCarpentryRecipe(Blocks.ACACIA_PLANKS, BlockItemTags.ACACIA_LOGS.item(), List.of(1, 1), CarpentryItems.ACACIA_CHAIR_GREGORY, 1);
                createCarpentryRecipe(Blocks.BAMBOO_PLANKS, BlockItemTags.BAMBOO_BLOCKS.item(), List.of(1, 1), CarpentryItems.BAMBOO_CHAIR_GREGORY, 1);
                createCarpentryRecipe(Blocks.BIRCH_PLANKS, BlockItemTags.BIRCH_LOGS.item(), List.of(1, 1), CarpentryItems.BIRCH_CHAIR_GREGORY, 1);
                createCarpentryRecipe(Blocks.CHERRY_PLANKS, BlockItemTags.CHERRY_LOGS.item(), List.of(1, 1), CarpentryItems.CHERRY_CHAIR_GREGORY, 1);
                createCarpentryRecipe(Blocks.CRIMSON_PLANKS, BlockItemTags.CRIMSON_STEMS.item(), List.of(1, 1), CarpentryItems.CRIMSON_CHAIR_GREGORY, 1);
                createCarpentryRecipe(Blocks.DARK_OAK_PLANKS, BlockItemTags.DARK_OAK_LOGS.item(), List.of(1, 1), CarpentryItems.DARK_OAK_CHAIR_GREGORY, 1);
                createCarpentryRecipe(Blocks.PALE_OAK_PLANKS, BlockItemTags.PALE_OAK_LOGS.item(), List.of(1, 1), CarpentryItems.PALE_OAK_CHAIR_GREGORY, 1);
                createCarpentryRecipe(Blocks.JUNGLE_PLANKS, BlockItemTags.JUNGLE_LOGS.item(), List.of(1, 1), CarpentryItems.JUNGLE_CHAIR_GREGORY, 1);
                createCarpentryRecipe(Blocks.MANGROVE_PLANKS, BlockItemTags.MANGROVE_LOGS.item(), List.of(1, 1), CarpentryItems.MANGROVE_CHAIR_GREGORY, 1);
                createCarpentryRecipe(Blocks.OAK_PLANKS, BlockItemTags.OAK_LOGS.item(), List.of(1, 1), CarpentryItems.OAK_CHAIR_GREGORY, 1);
                createCarpentryRecipe(Blocks.SPRUCE_PLANKS, BlockItemTags.SPRUCE_LOGS.item(), List.of(1, 1), CarpentryItems.SPRUCE_CHAIR_GREGORY, 1);
                createCarpentryRecipe(Blocks.WARPED_PLANKS, BlockItemTags.WARPED_STEMS.item(), List.of(1, 1), CarpentryItems.WARPED_CHAIR_GREGORY, 1);
            }

            private void createBarberChairRecipes() {
                createCarpentryRecipe(List.of(Blocks.WOOL.white(), Blocks.GOLD_BLOCK), List.of(1, 1), CarpentryItems.WHITE_BARBER_CHAIR, 1);
                createCarpentryRecipe(List.of(Blocks.WOOL.lightGray(), Blocks.GOLD_BLOCK), List.of(1, 1), CarpentryItems.LIGHT_GRAY_BARBER_CHAIR, 1);
                createCarpentryRecipe(List.of(Blocks.WOOL.gray(), Blocks.GOLD_BLOCK), List.of(1, 1), CarpentryItems.GRAY_BARBER_CHAIR, 1);
                createCarpentryRecipe(List.of(Blocks.WOOL.black(), Blocks.GOLD_BLOCK), List.of(1, 1), CarpentryItems.BLACK_BARBER_CHAIR, 1);
                createCarpentryRecipe(List.of(Blocks.WOOL.brown(), Blocks.GOLD_BLOCK), List.of(1, 1), CarpentryItems.BROWN_BARBER_CHAIR, 1);
                createCarpentryRecipe(List.of(Blocks.WOOL.red(), Blocks.GOLD_BLOCK), List.of(1, 1), CarpentryItems.RED_BARBER_CHAIR, 1);
                createCarpentryRecipe(List.of(Blocks.WOOL.orange(), Blocks.GOLD_BLOCK), List.of(1, 1), CarpentryItems.ORANGE_BARBER_CHAIR, 1);
                createCarpentryRecipe(List.of(Blocks.WOOL.yellow(), Blocks.GOLD_BLOCK), List.of(1, 1), CarpentryItems.YELLOW_BARBER_CHAIR, 1);
                createCarpentryRecipe(List.of(Blocks.WOOL.lime(), Blocks.GOLD_BLOCK), List.of(1, 1), CarpentryItems.LIME_BARBER_CHAIR, 1);
                createCarpentryRecipe(List.of(Blocks.WOOL.green(), Blocks.GOLD_BLOCK), List.of(1, 1), CarpentryItems.GREEN_BARBER_CHAIR, 1);
                createCarpentryRecipe(List.of(Blocks.WOOL.cyan(), Blocks.GOLD_BLOCK), List.of(1, 1), CarpentryItems.CYAN_BARBER_CHAIR, 1);
                createCarpentryRecipe(List.of(Blocks.WOOL.lightBlue(), Blocks.GOLD_BLOCK), List.of(1, 1), CarpentryItems.LIGHT_BLUE_BARBER_CHAIR, 1);
                createCarpentryRecipe(List.of(Blocks.WOOL.blue(), Blocks.GOLD_BLOCK), List.of(1, 1), CarpentryItems.BLUE_BARBER_CHAIR, 1);
                createCarpentryRecipe(List.of(Blocks.WOOL.purple(), Blocks.GOLD_BLOCK), List.of(1, 1), CarpentryItems.PURPLE_BARBER_CHAIR, 1);
                createCarpentryRecipe(List.of(Blocks.WOOL.magenta(), Blocks.GOLD_BLOCK), List.of(1, 1), CarpentryItems.MAGENTA_BARBER_CHAIR, 1);
                createCarpentryRecipe(List.of(Blocks.WOOL.pink(), Blocks.GOLD_BLOCK), List.of(1, 1), CarpentryItems.PINK_BARBER_CHAIR, 1);
            }

            private void createBarChairRecipes() {
                createCarpentryRecipe(List.of(Blocks.WOOL.white(), Blocks.IRON_BLOCK), List.of(1, 1), CarpentryItems.WHITE_BAR_CHAIR, 1);
                createCarpentryRecipe(List.of(Blocks.WOOL.lightGray(), Blocks.IRON_BLOCK), List.of(1, 1), CarpentryItems.LIGHT_GRAY_BAR_CHAIR, 1);
                createCarpentryRecipe(List.of(Blocks.WOOL.gray(), Blocks.IRON_BLOCK), List.of(1, 1), CarpentryItems.GRAY_BAR_CHAIR, 1);
                createCarpentryRecipe(List.of(Blocks.WOOL.black(), Blocks.IRON_BLOCK), List.of(1, 1), CarpentryItems.BLACK_BAR_CHAIR, 1);
                createCarpentryRecipe(List.of(Blocks.WOOL.brown(), Blocks.IRON_BLOCK), List.of(1, 1), CarpentryItems.BROWN_BAR_CHAIR, 1);
                createCarpentryRecipe(List.of(Blocks.WOOL.red(), Blocks.IRON_BLOCK), List.of(1, 1), CarpentryItems.RED_BAR_CHAIR, 1);
                createCarpentryRecipe(List.of(Blocks.WOOL.orange(), Blocks.IRON_BLOCK), List.of(1, 1), CarpentryItems.ORANGE_BAR_CHAIR, 1);
                createCarpentryRecipe(List.of(Blocks.WOOL.yellow(), Blocks.IRON_BLOCK), List.of(1, 1), CarpentryItems.YELLOW_BAR_CHAIR, 1);
                createCarpentryRecipe(List.of(Blocks.WOOL.lime(), Blocks.IRON_BLOCK), List.of(1, 1), CarpentryItems.LIME_BAR_CHAIR, 1);
                createCarpentryRecipe(List.of(Blocks.WOOL.green(), Blocks.IRON_BLOCK), List.of(1, 1), CarpentryItems.GREEN_BAR_CHAIR, 1);
                createCarpentryRecipe(List.of(Blocks.WOOL.cyan(), Blocks.IRON_BLOCK), List.of(1, 1), CarpentryItems.CYAN_BAR_CHAIR, 1);
                createCarpentryRecipe(List.of(Blocks.WOOL.lightBlue(), Blocks.IRON_BLOCK), List.of(1, 1), CarpentryItems.LIGHT_BLUE_BAR_CHAIR, 1);
                createCarpentryRecipe(List.of(Blocks.WOOL.blue(), Blocks.IRON_BLOCK), List.of(1, 1), CarpentryItems.BLUE_BAR_CHAIR, 1);
                createCarpentryRecipe(List.of(Blocks.WOOL.purple(), Blocks.IRON_BLOCK), List.of(1, 1), CarpentryItems.PURPLE_BAR_CHAIR, 1);
                createCarpentryRecipe(List.of(Blocks.WOOL.magenta(), Blocks.IRON_BLOCK), List.of(1, 1), CarpentryItems.MAGENTA_BAR_CHAIR, 1);
                createCarpentryRecipe(List.of(Blocks.WOOL.pink(), Blocks.IRON_BLOCK), List.of(1, 1), CarpentryItems.PINK_BAR_CHAIR, 1);
            }

            private void createPascalChairRecipes() {
                createCarpentryRecipe(List.of(Blocks.ACACIA_PLANKS, Blocks.IRON_BLOCK), List.of(1, 1), CarpentryItems.ACACIA_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.ACACIA_CHAIR_PASCAL, Blocks.WOOL.white()), List.of(1, 1), CarpentryItems.ACACIA_WHITE_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.ACACIA_CHAIR_PASCAL, Blocks.WOOL.lightGray()), List.of(1, 1), CarpentryItems.ACACIA_LIGHT_GRAY_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.ACACIA_CHAIR_PASCAL, Blocks.WOOL.gray()), List.of(1, 1), CarpentryItems.ACACIA_GRAY_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.ACACIA_CHAIR_PASCAL, Blocks.WOOL.black()), List.of(1, 1), CarpentryItems.ACACIA_BLACK_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.ACACIA_CHAIR_PASCAL, Blocks.WOOL.brown()), List.of(1, 1), CarpentryItems.ACACIA_BROWN_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.ACACIA_CHAIR_PASCAL, Blocks.WOOL.red()), List.of(1, 1), CarpentryItems.ACACIA_RED_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.ACACIA_CHAIR_PASCAL, Blocks.WOOL.orange()), List.of(1, 1), CarpentryItems.ACACIA_ORANGE_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.ACACIA_CHAIR_PASCAL, Blocks.WOOL.yellow()), List.of(1, 1), CarpentryItems.ACACIA_YELLOW_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.ACACIA_CHAIR_PASCAL, Blocks.WOOL.lime()), List.of(1, 1), CarpentryItems.ACACIA_LIME_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.ACACIA_CHAIR_PASCAL, Blocks.WOOL.green()), List.of(1, 1), CarpentryItems.ACACIA_GREEN_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.ACACIA_CHAIR_PASCAL, Blocks.WOOL.cyan()), List.of(1, 1), CarpentryItems.ACACIA_CYAN_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.ACACIA_CHAIR_PASCAL, Blocks.WOOL.lightBlue()), List.of(1, 1), CarpentryItems.ACACIA_LIGHT_BLUE_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.ACACIA_CHAIR_PASCAL, Blocks.WOOL.blue()), List.of(1, 1), CarpentryItems.ACACIA_BLUE_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.ACACIA_CHAIR_PASCAL, Blocks.WOOL.purple()), List.of(1, 1), CarpentryItems.ACACIA_PURPLE_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.ACACIA_CHAIR_PASCAL, Blocks.WOOL.magenta()), List.of(1, 1), CarpentryItems.ACACIA_MAGENTA_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.ACACIA_CHAIR_PASCAL, Blocks.WOOL.pink()), List.of(1, 1), CarpentryItems.ACACIA_PINK_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(Blocks.BAMBOO_PLANKS, Blocks.IRON_BLOCK), List.of(1, 1), CarpentryItems.BAMBOO_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.BAMBOO_CHAIR_PASCAL, Blocks.WOOL.white()), List.of(1, 1), CarpentryItems.BAMBOO_WHITE_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.BAMBOO_CHAIR_PASCAL, Blocks.WOOL.lightGray()), List.of(1, 1), CarpentryItems.BAMBOO_LIGHT_GRAY_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.BAMBOO_CHAIR_PASCAL, Blocks.WOOL.gray()), List.of(1, 1), CarpentryItems.BAMBOO_GRAY_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.BAMBOO_CHAIR_PASCAL, Blocks.WOOL.black()), List.of(1, 1), CarpentryItems.BAMBOO_BLACK_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.BAMBOO_CHAIR_PASCAL, Blocks.WOOL.brown()), List.of(1, 1), CarpentryItems.BAMBOO_BROWN_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.BAMBOO_CHAIR_PASCAL, Blocks.WOOL.red()), List.of(1, 1), CarpentryItems.BAMBOO_RED_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.BAMBOO_CHAIR_PASCAL, Blocks.WOOL.orange()), List.of(1, 1), CarpentryItems.BAMBOO_ORANGE_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.BAMBOO_CHAIR_PASCAL, Blocks.WOOL.yellow()), List.of(1, 1), CarpentryItems.BAMBOO_YELLOW_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.BAMBOO_CHAIR_PASCAL, Blocks.WOOL.lime()), List.of(1, 1), CarpentryItems.BAMBOO_LIME_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.BAMBOO_CHAIR_PASCAL, Blocks.WOOL.green()), List.of(1, 1), CarpentryItems.BAMBOO_GREEN_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.BAMBOO_CHAIR_PASCAL, Blocks.WOOL.cyan()), List.of(1, 1), CarpentryItems.BAMBOO_CYAN_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.BAMBOO_CHAIR_PASCAL, Blocks.WOOL.lightBlue()), List.of(1, 1), CarpentryItems.BAMBOO_LIGHT_BLUE_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.BAMBOO_CHAIR_PASCAL, Blocks.WOOL.blue()), List.of(1, 1), CarpentryItems.BAMBOO_BLUE_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.BAMBOO_CHAIR_PASCAL, Blocks.WOOL.purple()), List.of(1, 1), CarpentryItems.BAMBOO_PURPLE_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.BAMBOO_CHAIR_PASCAL, Blocks.WOOL.magenta()), List.of(1, 1), CarpentryItems.BAMBOO_MAGENTA_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.BAMBOO_CHAIR_PASCAL, Blocks.WOOL.pink()), List.of(1, 1), CarpentryItems.BAMBOO_PINK_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(Blocks.BIRCH_PLANKS, Blocks.IRON_BLOCK), List.of(1, 1), CarpentryItems.BIRCH_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.BIRCH_CHAIR_PASCAL, Blocks.WOOL.white()), List.of(1, 1), CarpentryItems.BIRCH_WHITE_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.BIRCH_CHAIR_PASCAL, Blocks.WOOL.lightGray()), List.of(1, 1), CarpentryItems.BIRCH_LIGHT_GRAY_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.BIRCH_CHAIR_PASCAL, Blocks.WOOL.gray()), List.of(1, 1), CarpentryItems.BIRCH_GRAY_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.BIRCH_CHAIR_PASCAL, Blocks.WOOL.black()), List.of(1, 1), CarpentryItems.BIRCH_BLACK_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.BIRCH_CHAIR_PASCAL, Blocks.WOOL.brown()), List.of(1, 1), CarpentryItems.BIRCH_BROWN_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.BIRCH_CHAIR_PASCAL, Blocks.WOOL.red()), List.of(1, 1), CarpentryItems.BIRCH_RED_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.BIRCH_CHAIR_PASCAL, Blocks.WOOL.orange()), List.of(1, 1), CarpentryItems.BIRCH_ORANGE_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.BIRCH_CHAIR_PASCAL, Blocks.WOOL.yellow()), List.of(1, 1), CarpentryItems.BIRCH_YELLOW_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.BIRCH_CHAIR_PASCAL, Blocks.WOOL.lime()), List.of(1, 1), CarpentryItems.BIRCH_LIME_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.BIRCH_CHAIR_PASCAL, Blocks.WOOL.green()), List.of(1, 1), CarpentryItems.BIRCH_GREEN_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.BIRCH_CHAIR_PASCAL, Blocks.WOOL.cyan()), List.of(1, 1), CarpentryItems.BIRCH_CYAN_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.BIRCH_CHAIR_PASCAL, Blocks.WOOL.lightBlue()), List.of(1, 1), CarpentryItems.BIRCH_LIGHT_BLUE_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.BIRCH_CHAIR_PASCAL, Blocks.WOOL.blue()), List.of(1, 1), CarpentryItems.BIRCH_BLUE_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.BIRCH_CHAIR_PASCAL, Blocks.WOOL.purple()), List.of(1, 1), CarpentryItems.BIRCH_PURPLE_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.BIRCH_CHAIR_PASCAL, Blocks.WOOL.magenta()), List.of(1, 1), CarpentryItems.BIRCH_MAGENTA_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.BIRCH_CHAIR_PASCAL, Blocks.WOOL.pink()), List.of(1, 1), CarpentryItems.BIRCH_PINK_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(Blocks.CHERRY_PLANKS, Blocks.IRON_BLOCK), List.of(1, 1), CarpentryItems.CHERRY_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.CHERRY_CHAIR_PASCAL, Blocks.WOOL.white()), List.of(1, 1), CarpentryItems.CHERRY_WHITE_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.CHERRY_CHAIR_PASCAL, Blocks.WOOL.lightGray()), List.of(1, 1), CarpentryItems.CHERRY_LIGHT_GRAY_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.CHERRY_CHAIR_PASCAL, Blocks.WOOL.gray()), List.of(1, 1), CarpentryItems.CHERRY_GRAY_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.CHERRY_CHAIR_PASCAL, Blocks.WOOL.black()), List.of(1, 1), CarpentryItems.CHERRY_BLACK_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.CHERRY_CHAIR_PASCAL, Blocks.WOOL.brown()), List.of(1, 1), CarpentryItems.CHERRY_BROWN_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.CHERRY_CHAIR_PASCAL, Blocks.WOOL.red()), List.of(1, 1), CarpentryItems.CHERRY_RED_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.CHERRY_CHAIR_PASCAL, Blocks.WOOL.orange()), List.of(1, 1), CarpentryItems.CHERRY_ORANGE_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.CHERRY_CHAIR_PASCAL, Blocks.WOOL.yellow()), List.of(1, 1), CarpentryItems.CHERRY_YELLOW_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.CHERRY_CHAIR_PASCAL, Blocks.WOOL.lime()), List.of(1, 1), CarpentryItems.CHERRY_LIME_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.CHERRY_CHAIR_PASCAL, Blocks.WOOL.green()), List.of(1, 1), CarpentryItems.CHERRY_GREEN_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.CHERRY_CHAIR_PASCAL, Blocks.WOOL.cyan()), List.of(1, 1), CarpentryItems.CHERRY_CYAN_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.CHERRY_CHAIR_PASCAL, Blocks.WOOL.lightBlue()), List.of(1, 1), CarpentryItems.CHERRY_LIGHT_BLUE_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.CHERRY_CHAIR_PASCAL, Blocks.WOOL.blue()), List.of(1, 1), CarpentryItems.CHERRY_BLUE_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.CHERRY_CHAIR_PASCAL, Blocks.WOOL.purple()), List.of(1, 1), CarpentryItems.CHERRY_PURPLE_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.CHERRY_CHAIR_PASCAL, Blocks.WOOL.magenta()), List.of(1, 1), CarpentryItems.CHERRY_MAGENTA_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.CHERRY_CHAIR_PASCAL, Blocks.WOOL.pink()), List.of(1, 1), CarpentryItems.CHERRY_PINK_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(Blocks.CRIMSON_PLANKS, Blocks.IRON_BLOCK), List.of(1, 1), CarpentryItems.CRIMSON_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.CRIMSON_CHAIR_PASCAL, Blocks.WOOL.white()), List.of(1, 1), CarpentryItems.CRIMSON_WHITE_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.CRIMSON_CHAIR_PASCAL, Blocks.WOOL.lightGray()), List.of(1, 1), CarpentryItems.CRIMSON_LIGHT_GRAY_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.CRIMSON_CHAIR_PASCAL, Blocks.WOOL.gray()), List.of(1, 1), CarpentryItems.CRIMSON_GRAY_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.CRIMSON_CHAIR_PASCAL, Blocks.WOOL.black()), List.of(1, 1), CarpentryItems.CRIMSON_BLACK_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.CRIMSON_CHAIR_PASCAL, Blocks.WOOL.brown()), List.of(1, 1), CarpentryItems.CRIMSON_BROWN_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.CRIMSON_CHAIR_PASCAL, Blocks.WOOL.red()), List.of(1, 1), CarpentryItems.CRIMSON_RED_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.CRIMSON_CHAIR_PASCAL, Blocks.WOOL.orange()), List.of(1, 1), CarpentryItems.CRIMSON_ORANGE_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.CRIMSON_CHAIR_PASCAL, Blocks.WOOL.yellow()), List.of(1, 1), CarpentryItems.CRIMSON_YELLOW_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.CRIMSON_CHAIR_PASCAL, Blocks.WOOL.lime()), List.of(1, 1), CarpentryItems.CRIMSON_LIME_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.CRIMSON_CHAIR_PASCAL, Blocks.WOOL.green()), List.of(1, 1), CarpentryItems.CRIMSON_GREEN_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.CRIMSON_CHAIR_PASCAL, Blocks.WOOL.cyan()), List.of(1, 1), CarpentryItems.CRIMSON_CYAN_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.CRIMSON_CHAIR_PASCAL, Blocks.WOOL.lightBlue()), List.of(1, 1), CarpentryItems.CRIMSON_LIGHT_BLUE_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.CRIMSON_CHAIR_PASCAL, Blocks.WOOL.blue()), List.of(1, 1), CarpentryItems.CRIMSON_BLUE_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.CRIMSON_CHAIR_PASCAL, Blocks.WOOL.purple()), List.of(1, 1), CarpentryItems.CRIMSON_PURPLE_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.CRIMSON_CHAIR_PASCAL, Blocks.WOOL.magenta()), List.of(1, 1), CarpentryItems.CRIMSON_MAGENTA_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.CRIMSON_CHAIR_PASCAL, Blocks.WOOL.pink()), List.of(1, 1), CarpentryItems.CRIMSON_PINK_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(Blocks.DARK_OAK_PLANKS, Blocks.IRON_BLOCK), List.of(1, 1), CarpentryItems.DARK_OAK_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.DARK_OAK_CHAIR_PASCAL, Blocks.WOOL.white()), List.of(1, 1), CarpentryItems.DARK_OAK_WHITE_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.DARK_OAK_CHAIR_PASCAL, Blocks.WOOL.lightGray()), List.of(1, 1), CarpentryItems.DARK_OAK_LIGHT_GRAY_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.DARK_OAK_CHAIR_PASCAL, Blocks.WOOL.gray()), List.of(1, 1), CarpentryItems.DARK_OAK_GRAY_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.DARK_OAK_CHAIR_PASCAL, Blocks.WOOL.black()), List.of(1, 1), CarpentryItems.DARK_OAK_BLACK_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.DARK_OAK_CHAIR_PASCAL, Blocks.WOOL.brown()), List.of(1, 1), CarpentryItems.DARK_OAK_BROWN_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.DARK_OAK_CHAIR_PASCAL, Blocks.WOOL.red()), List.of(1, 1), CarpentryItems.DARK_OAK_RED_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.DARK_OAK_CHAIR_PASCAL, Blocks.WOOL.orange()), List.of(1, 1), CarpentryItems.DARK_OAK_ORANGE_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.DARK_OAK_CHAIR_PASCAL, Blocks.WOOL.yellow()), List.of(1, 1), CarpentryItems.DARK_OAK_YELLOW_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.DARK_OAK_CHAIR_PASCAL, Blocks.WOOL.lime()), List.of(1, 1), CarpentryItems.DARK_OAK_LIME_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.DARK_OAK_CHAIR_PASCAL, Blocks.WOOL.green()), List.of(1, 1), CarpentryItems.DARK_OAK_GREEN_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.DARK_OAK_CHAIR_PASCAL, Blocks.WOOL.cyan()), List.of(1, 1), CarpentryItems.DARK_OAK_CYAN_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.DARK_OAK_CHAIR_PASCAL, Blocks.WOOL.lightBlue()), List.of(1, 1), CarpentryItems.DARK_OAK_LIGHT_BLUE_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.DARK_OAK_CHAIR_PASCAL, Blocks.WOOL.blue()), List.of(1, 1), CarpentryItems.DARK_OAK_BLUE_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.DARK_OAK_CHAIR_PASCAL, Blocks.WOOL.purple()), List.of(1, 1), CarpentryItems.DARK_OAK_PURPLE_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.DARK_OAK_CHAIR_PASCAL, Blocks.WOOL.magenta()), List.of(1, 1), CarpentryItems.DARK_OAK_MAGENTA_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.DARK_OAK_CHAIR_PASCAL, Blocks.WOOL.pink()), List.of(1, 1), CarpentryItems.DARK_OAK_PINK_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(Blocks.PALE_OAK_PLANKS, Blocks.IRON_BLOCK), List.of(1, 1), CarpentryItems.PALE_OAK_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.PALE_OAK_CHAIR_PASCAL, Blocks.WOOL.white()), List.of(1, 1), CarpentryItems.PALE_OAK_WHITE_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.PALE_OAK_CHAIR_PASCAL, Blocks.WOOL.lightGray()), List.of(1, 1), CarpentryItems.PALE_OAK_LIGHT_GRAY_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.PALE_OAK_CHAIR_PASCAL, Blocks.WOOL.gray()), List.of(1, 1), CarpentryItems.PALE_OAK_GRAY_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.PALE_OAK_CHAIR_PASCAL, Blocks.WOOL.black()), List.of(1, 1), CarpentryItems.PALE_OAK_BLACK_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.PALE_OAK_CHAIR_PASCAL, Blocks.WOOL.brown()), List.of(1, 1), CarpentryItems.PALE_OAK_BROWN_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.PALE_OAK_CHAIR_PASCAL, Blocks.WOOL.red()), List.of(1, 1), CarpentryItems.PALE_OAK_RED_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.PALE_OAK_CHAIR_PASCAL, Blocks.WOOL.orange()), List.of(1, 1), CarpentryItems.PALE_OAK_ORANGE_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.PALE_OAK_CHAIR_PASCAL, Blocks.WOOL.yellow()), List.of(1, 1), CarpentryItems.PALE_OAK_YELLOW_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.PALE_OAK_CHAIR_PASCAL, Blocks.WOOL.lime()), List.of(1, 1), CarpentryItems.PALE_OAK_LIME_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.PALE_OAK_CHAIR_PASCAL, Blocks.WOOL.green()), List.of(1, 1), CarpentryItems.PALE_OAK_GREEN_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.PALE_OAK_CHAIR_PASCAL, Blocks.WOOL.cyan()), List.of(1, 1), CarpentryItems.PALE_OAK_CYAN_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.PALE_OAK_CHAIR_PASCAL, Blocks.WOOL.lightBlue()), List.of(1, 1), CarpentryItems.PALE_OAK_LIGHT_BLUE_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.PALE_OAK_CHAIR_PASCAL, Blocks.WOOL.blue()), List.of(1, 1), CarpentryItems.PALE_OAK_BLUE_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.PALE_OAK_CHAIR_PASCAL, Blocks.WOOL.purple()), List.of(1, 1), CarpentryItems.PALE_OAK_PURPLE_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.PALE_OAK_CHAIR_PASCAL, Blocks.WOOL.magenta()), List.of(1, 1), CarpentryItems.PALE_OAK_MAGENTA_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.PALE_OAK_CHAIR_PASCAL, Blocks.WOOL.pink()), List.of(1, 1), CarpentryItems.PALE_OAK_PINK_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(Blocks.JUNGLE_PLANKS, Blocks.IRON_BLOCK), List.of(1, 1), CarpentryItems.JUNGLE_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.JUNGLE_CHAIR_PASCAL, Blocks.WOOL.white()), List.of(1, 1), CarpentryItems.JUNGLE_WHITE_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.JUNGLE_CHAIR_PASCAL, Blocks.WOOL.lightGray()), List.of(1, 1), CarpentryItems.JUNGLE_LIGHT_GRAY_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.JUNGLE_CHAIR_PASCAL, Blocks.WOOL.gray()), List.of(1, 1), CarpentryItems.JUNGLE_GRAY_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.JUNGLE_CHAIR_PASCAL, Blocks.WOOL.black()), List.of(1, 1), CarpentryItems.JUNGLE_BLACK_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.JUNGLE_CHAIR_PASCAL, Blocks.WOOL.brown()), List.of(1, 1), CarpentryItems.JUNGLE_BROWN_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.JUNGLE_CHAIR_PASCAL, Blocks.WOOL.red()), List.of(1, 1), CarpentryItems.JUNGLE_RED_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.JUNGLE_CHAIR_PASCAL, Blocks.WOOL.orange()), List.of(1, 1), CarpentryItems.JUNGLE_ORANGE_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.JUNGLE_CHAIR_PASCAL, Blocks.WOOL.yellow()), List.of(1, 1), CarpentryItems.JUNGLE_YELLOW_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.JUNGLE_CHAIR_PASCAL, Blocks.WOOL.lime()), List.of(1, 1), CarpentryItems.JUNGLE_LIME_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.JUNGLE_CHAIR_PASCAL, Blocks.WOOL.green()), List.of(1, 1), CarpentryItems.JUNGLE_GREEN_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.JUNGLE_CHAIR_PASCAL, Blocks.WOOL.cyan()), List.of(1, 1), CarpentryItems.JUNGLE_CYAN_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.JUNGLE_CHAIR_PASCAL, Blocks.WOOL.lightBlue()), List.of(1, 1), CarpentryItems.JUNGLE_LIGHT_BLUE_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.JUNGLE_CHAIR_PASCAL, Blocks.WOOL.blue()), List.of(1, 1), CarpentryItems.JUNGLE_BLUE_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.JUNGLE_CHAIR_PASCAL, Blocks.WOOL.purple()), List.of(1, 1), CarpentryItems.JUNGLE_PURPLE_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.JUNGLE_CHAIR_PASCAL, Blocks.WOOL.magenta()), List.of(1, 1), CarpentryItems.JUNGLE_MAGENTA_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.JUNGLE_CHAIR_PASCAL, Blocks.WOOL.pink()), List.of(1, 1), CarpentryItems.JUNGLE_PINK_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(Blocks.MANGROVE_PLANKS, Blocks.IRON_BLOCK), List.of(1, 1), CarpentryItems.MANGROVE_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.MANGROVE_CHAIR_PASCAL, Blocks.WOOL.white()), List.of(1, 1), CarpentryItems.MANGROVE_WHITE_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.MANGROVE_CHAIR_PASCAL, Blocks.WOOL.lightGray()), List.of(1, 1), CarpentryItems.MANGROVE_LIGHT_GRAY_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.MANGROVE_CHAIR_PASCAL, Blocks.WOOL.gray()), List.of(1, 1), CarpentryItems.MANGROVE_GRAY_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.MANGROVE_CHAIR_PASCAL, Blocks.WOOL.black()), List.of(1, 1), CarpentryItems.MANGROVE_BLACK_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.MANGROVE_CHAIR_PASCAL, Blocks.WOOL.brown()), List.of(1, 1), CarpentryItems.MANGROVE_BROWN_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.MANGROVE_CHAIR_PASCAL, Blocks.WOOL.red()), List.of(1, 1), CarpentryItems.MANGROVE_RED_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.MANGROVE_CHAIR_PASCAL, Blocks.WOOL.orange()), List.of(1, 1), CarpentryItems.MANGROVE_ORANGE_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.MANGROVE_CHAIR_PASCAL, Blocks.WOOL.yellow()), List.of(1, 1), CarpentryItems.MANGROVE_YELLOW_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.MANGROVE_CHAIR_PASCAL, Blocks.WOOL.lime()), List.of(1, 1), CarpentryItems.MANGROVE_LIME_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.MANGROVE_CHAIR_PASCAL, Blocks.WOOL.green()), List.of(1, 1), CarpentryItems.MANGROVE_GREEN_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.MANGROVE_CHAIR_PASCAL, Blocks.WOOL.cyan()), List.of(1, 1), CarpentryItems.MANGROVE_CYAN_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.MANGROVE_CHAIR_PASCAL, Blocks.WOOL.lightBlue()), List.of(1, 1), CarpentryItems.MANGROVE_LIGHT_BLUE_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.MANGROVE_CHAIR_PASCAL, Blocks.WOOL.blue()), List.of(1, 1), CarpentryItems.MANGROVE_BLUE_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.MANGROVE_CHAIR_PASCAL, Blocks.WOOL.purple()), List.of(1, 1), CarpentryItems.MANGROVE_PURPLE_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.MANGROVE_CHAIR_PASCAL, Blocks.WOOL.magenta()), List.of(1, 1), CarpentryItems.MANGROVE_MAGENTA_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.MANGROVE_CHAIR_PASCAL, Blocks.WOOL.pink()), List.of(1, 1), CarpentryItems.MANGROVE_PINK_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(Blocks.OAK_PLANKS, Blocks.IRON_BLOCK), List.of(1, 1), CarpentryItems.OAK_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.OAK_CHAIR_PASCAL, Blocks.WOOL.white()), List.of(1, 1), CarpentryItems.OAK_WHITE_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.OAK_CHAIR_PASCAL, Blocks.WOOL.lightGray()), List.of(1, 1), CarpentryItems.OAK_LIGHT_GRAY_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.OAK_CHAIR_PASCAL, Blocks.WOOL.gray()), List.of(1, 1), CarpentryItems.OAK_GRAY_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.OAK_CHAIR_PASCAL, Blocks.WOOL.black()), List.of(1, 1), CarpentryItems.OAK_BLACK_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.OAK_CHAIR_PASCAL, Blocks.WOOL.brown()), List.of(1, 1), CarpentryItems.OAK_BROWN_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.OAK_CHAIR_PASCAL, Blocks.WOOL.red()), List.of(1, 1), CarpentryItems.OAK_RED_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.OAK_CHAIR_PASCAL, Blocks.WOOL.orange()), List.of(1, 1), CarpentryItems.OAK_ORANGE_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.OAK_CHAIR_PASCAL, Blocks.WOOL.yellow()), List.of(1, 1), CarpentryItems.OAK_YELLOW_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.OAK_CHAIR_PASCAL, Blocks.WOOL.lime()), List.of(1, 1), CarpentryItems.OAK_LIME_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.OAK_CHAIR_PASCAL, Blocks.WOOL.green()), List.of(1, 1), CarpentryItems.OAK_GREEN_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.OAK_CHAIR_PASCAL, Blocks.WOOL.cyan()), List.of(1, 1), CarpentryItems.OAK_CYAN_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.OAK_CHAIR_PASCAL, Blocks.WOOL.lightBlue()), List.of(1, 1), CarpentryItems.OAK_LIGHT_BLUE_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.OAK_CHAIR_PASCAL, Blocks.WOOL.blue()), List.of(1, 1), CarpentryItems.OAK_BLUE_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.OAK_CHAIR_PASCAL, Blocks.WOOL.purple()), List.of(1, 1), CarpentryItems.OAK_PURPLE_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.OAK_CHAIR_PASCAL, Blocks.WOOL.magenta()), List.of(1, 1), CarpentryItems.OAK_MAGENTA_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.OAK_CHAIR_PASCAL, Blocks.WOOL.pink()), List.of(1, 1), CarpentryItems.OAK_PINK_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(Blocks.SPRUCE_PLANKS, Blocks.IRON_BLOCK), List.of(1, 1), CarpentryItems.SPRUCE_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.SPRUCE_CHAIR_PASCAL, Blocks.WOOL.white()), List.of(1, 1), CarpentryItems.SPRUCE_WHITE_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.SPRUCE_CHAIR_PASCAL, Blocks.WOOL.lightGray()), List.of(1, 1), CarpentryItems.SPRUCE_LIGHT_GRAY_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.SPRUCE_CHAIR_PASCAL, Blocks.WOOL.gray()), List.of(1, 1), CarpentryItems.SPRUCE_GRAY_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.SPRUCE_CHAIR_PASCAL, Blocks.WOOL.black()), List.of(1, 1), CarpentryItems.SPRUCE_BLACK_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.SPRUCE_CHAIR_PASCAL, Blocks.WOOL.brown()), List.of(1, 1), CarpentryItems.SPRUCE_BROWN_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.SPRUCE_CHAIR_PASCAL, Blocks.WOOL.red()), List.of(1, 1), CarpentryItems.SPRUCE_RED_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.SPRUCE_CHAIR_PASCAL, Blocks.WOOL.orange()), List.of(1, 1), CarpentryItems.SPRUCE_ORANGE_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.SPRUCE_CHAIR_PASCAL, Blocks.WOOL.yellow()), List.of(1, 1), CarpentryItems.SPRUCE_YELLOW_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.SPRUCE_CHAIR_PASCAL, Blocks.WOOL.lime()), List.of(1, 1), CarpentryItems.SPRUCE_LIME_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.SPRUCE_CHAIR_PASCAL, Blocks.WOOL.green()), List.of(1, 1), CarpentryItems.SPRUCE_GREEN_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.SPRUCE_CHAIR_PASCAL, Blocks.WOOL.cyan()), List.of(1, 1), CarpentryItems.SPRUCE_CYAN_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.SPRUCE_CHAIR_PASCAL, Blocks.WOOL.lightBlue()), List.of(1, 1), CarpentryItems.SPRUCE_LIGHT_BLUE_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.SPRUCE_CHAIR_PASCAL, Blocks.WOOL.blue()), List.of(1, 1), CarpentryItems.SPRUCE_BLUE_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.SPRUCE_CHAIR_PASCAL, Blocks.WOOL.purple()), List.of(1, 1), CarpentryItems.SPRUCE_PURPLE_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.SPRUCE_CHAIR_PASCAL, Blocks.WOOL.magenta()), List.of(1, 1), CarpentryItems.SPRUCE_MAGENTA_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.SPRUCE_CHAIR_PASCAL, Blocks.WOOL.pink()), List.of(1, 1), CarpentryItems.SPRUCE_PINK_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(Blocks.WARPED_PLANKS, Blocks.IRON_BLOCK), List.of(1, 1), CarpentryItems.WARPED_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.WARPED_CHAIR_PASCAL, Blocks.WOOL.white()), List.of(1, 1), CarpentryItems.WARPED_WHITE_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.WARPED_CHAIR_PASCAL, Blocks.WOOL.lightGray()), List.of(1, 1), CarpentryItems.WARPED_LIGHT_GRAY_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.WARPED_CHAIR_PASCAL, Blocks.WOOL.gray()), List.of(1, 1), CarpentryItems.WARPED_GRAY_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.WARPED_CHAIR_PASCAL, Blocks.WOOL.black()), List.of(1, 1), CarpentryItems.WARPED_BLACK_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.WARPED_CHAIR_PASCAL, Blocks.WOOL.brown()), List.of(1, 1), CarpentryItems.WARPED_BROWN_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.WARPED_CHAIR_PASCAL, Blocks.WOOL.red()), List.of(1, 1), CarpentryItems.WARPED_RED_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.WARPED_CHAIR_PASCAL, Blocks.WOOL.orange()), List.of(1, 1), CarpentryItems.WARPED_ORANGE_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.WARPED_CHAIR_PASCAL, Blocks.WOOL.yellow()), List.of(1, 1), CarpentryItems.WARPED_YELLOW_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.WARPED_CHAIR_PASCAL, Blocks.WOOL.lime()), List.of(1, 1), CarpentryItems.WARPED_LIME_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.WARPED_CHAIR_PASCAL, Blocks.WOOL.green()), List.of(1, 1), CarpentryItems.WARPED_GREEN_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.WARPED_CHAIR_PASCAL, Blocks.WOOL.cyan()), List.of(1, 1), CarpentryItems.WARPED_CYAN_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.WARPED_CHAIR_PASCAL, Blocks.WOOL.lightBlue()), List.of(1, 1), CarpentryItems.WARPED_LIGHT_BLUE_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.WARPED_CHAIR_PASCAL, Blocks.WOOL.blue()), List.of(1, 1), CarpentryItems.WARPED_BLUE_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.WARPED_CHAIR_PASCAL, Blocks.WOOL.purple()), List.of(1, 1), CarpentryItems.WARPED_PURPLE_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.WARPED_CHAIR_PASCAL, Blocks.WOOL.magenta()), List.of(1, 1), CarpentryItems.WARPED_MAGENTA_CHAIR_PASCAL, 1);
                createCarpentryRecipe(List.of(CarpentryBlocks.WARPED_CHAIR_PASCAL, Blocks.WOOL.pink()), List.of(1, 1), CarpentryItems.WARPED_PINK_CHAIR_PASCAL, 1);

            }

            private void createCarpentryRecipe(List<ItemLike> ItemLikes, List<Integer> inputCounts, ItemLike result, int outputCount) {
                List<Ingredient> ingredients = new ArrayList<>();
                for (ItemLike ItemLike : ItemLikes) {
                    ingredients.add(Ingredient.of(ItemLike));
                }

                CarpentryRecipeBuilder builder = CarpentryRecipeBuilder.createCarpentry(RecipeCategory.DECORATIONS, ingredients, inputCounts, result, outputCount);

                builder.unlockedBy(getHasName(result), has(result)).save(output, getSimpleRecipeName(result) + "_carpentry");
            }

            private void createCarpentryRecipe(ItemLike ItemLike, TagKey<Item> itemTag, List<Integer> inputCounts, ItemLike result, int outputCount) {
                List<Ingredient> ingredients = new ArrayList<>();
                ingredients.add(Ingredient.of(ItemLike));
                ingredients.add(Ingredient.of(provider.lookupOrThrow(Registries.ITEM).getOrThrow(itemTag)));

                CarpentryRecipeBuilder builder = CarpentryRecipeBuilder.createCarpentry(RecipeCategory.DECORATIONS, ingredients, inputCounts, result, outputCount);

                builder.unlockedBy(getHasName(result), has(result)).save(output, getSimpleRecipeName(result) + "_carpentry");
            }

            private void createBoundBambooLadderReceipe(RecipeOutput output) {
                this.shaped(RecipeCategory.DECORATIONS, CarpentryBlocks.BOUND_BAMBOO_LADDER, 3)
                        .define('B', Items.BAMBOO)
                        .define('S', Items.STRING)
                        .pattern("B B")
                        .pattern("BSB")
                        .pattern("B B")
                        .unlockedBy("has_bamboo", has(Items.BAMBOO))
                        .save(output);
            }
        };
    }

    @Override
    public @NotNull String getName() {
        return "Nemo's Carpentry Recipes";
    }
}
