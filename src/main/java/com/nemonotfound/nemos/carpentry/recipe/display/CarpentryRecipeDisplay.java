package com.nemonotfound.nemos.carpentry.recipe.display;

import com.nemonotfound.nemos.carpentry.recipe.CarpentryRecipe;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.display.SlotDisplay;

import java.util.HashSet;
import java.util.List;

/** Immutable wire data shared by the workbench and recipe viewers. */
public final class CarpentryRecipeDisplay {
    private CarpentryRecipeDisplay() {}

    public record GroupEntry(Identifier id, List<Ingredient> ingredients, List<Integer> inputCounts,
                             ItemStackTemplate result) {
        public GroupEntry {
            ingredients = List.copyOf(ingredients);
            inputCounts = List.copyOf(inputCounts);
            CarpentryRecipe.validateInputs(ingredients, inputCounts);
            java.util.Objects.requireNonNull(id, "id");
            java.util.Objects.requireNonNull(result, "result");
        }

        public SlotDisplay optionDisplay() {
            return new SlotDisplay.ItemStackSlotDisplay(result);
        }

        public static StreamCodec<RegistryFriendlyByteBuf, GroupEntry> codec() {
            return StreamCodec.composite(
                    Identifier.STREAM_CODEC, GroupEntry::id,
                    Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.list(2)), GroupEntry::ingredients,
                    ByteBufCodecs.VAR_INT.apply(ByteBufCodecs.list(2)), GroupEntry::inputCounts,
                    ItemStackTemplate.STREAM_CODEC, GroupEntry::result,
                    GroupEntry::new);
        }
    }

    public record Grouping(long revision, List<GroupEntry> entries) {
        public Grouping {
            entries = List.copyOf(entries);
            var ids = new HashSet<Identifier>();
            for (var entry : entries) {
                if (!ids.add(entry.id())) {
                    throw new IllegalArgumentException("Duplicate carpentry recipe: " + entry.id());
                }
            }
        }

        public static Grouping empty() {
            return new Grouping(0, List.of());
        }

        public static StreamCodec<RegistryFriendlyByteBuf, Grouping> codec() {
            return StreamCodec.composite(ByteBufCodecs.VAR_LONG, Grouping::revision,
                    GroupEntry.codec().apply(ByteBufCodecs.list(16384)), Grouping::entries, Grouping::new);
        }

        public Grouping filter(ItemStack stack) {
            return new Grouping(revision, entries.stream().filter(entry -> entry.ingredients().getFirst().test(stack)).toList());
        }

        public boolean isEmpty() {
            return entries.isEmpty();
        }

        public int size() {
            return entries.size();
        }
    }
}
