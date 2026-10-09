package com.nemonotfound.nemos.carpentry.recipe;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.triggers.Criterion;
import net.minecraft.advancements.triggers.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CarpentryRecipeBuilder implements RecipeBuilder {

    private final RecipeCategory category;
    private final List<Ingredient> ingredients;
    private final List<Integer> inputCounts;
    private final Item output;
    private final int count;
    private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();

    public CarpentryRecipeBuilder(RecipeCategory category,
                                  List<Ingredient> ingredients, List<Integer> inputCounts, ItemLike output,
                                  int count) {
        this.category = category;
        this.ingredients = ingredients;
        this.inputCounts = inputCounts;
        this.output = output.asItem();
        this.count = count;
    }

    public static CarpentryRecipeBuilder createCarpentry(RecipeCategory category, List<Ingredient> ingredients,
                                                         List<Integer> inputCounts, ItemLike output, int count) {
        return new CarpentryRecipeBuilder(category, ingredients, inputCounts, output, count);
    }

    @Override
    public @NotNull CarpentryRecipeBuilder unlockedBy(@NotNull String string, @NotNull Criterion<?> criterion) {
        this.criteria.put(string, criterion);
        return this;
    }

    @Override
    public @NotNull RecipeBuilder group(@Nullable String group) {
        return this;
    }

    @Override
    public @NotNull ResourceKey<Recipe<?>> defaultId() {
        return RecipeBuilder.getDefaultRecipeId(new ItemStackTemplate(this.output));
    }

    @Override
    public void save(RecipeOutput recipeOutput, @NotNull ResourceKey<Recipe<?>> recipeKey) {
        this.validate(recipeKey);
        Advancement.Builder builder = recipeOutput.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(recipeOutput.lookup(Registries.RECIPE).getOrThrow(recipeKey)))
                .rewards(AdvancementRewards.Builder.recipe(recipeKey))
                .requirements(AdvancementRequirements.Strategy.OR);
        this.criteria.forEach(builder::addCriterion);
        CarpentryRecipe carpentryRecipe = new CarpentryRecipe(this.ingredients, this.inputCounts,
                new ItemStackTemplate(this.output, this.count));
        recipeOutput.accept(recipeKey, carpentryRecipe, builder.build(recipeKey.identifier().withPrefix("recipes/" + this.category.getFolderName() + "/")));
    }

    private void validate(ResourceKey<Recipe<?>> recipeKey) {
        if (this.criteria.isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + recipeKey.identifier());
        }
    }
}
