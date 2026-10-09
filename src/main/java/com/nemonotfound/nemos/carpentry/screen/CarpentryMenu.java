package com.nemonotfound.nemos.carpentry.screen;

import com.nemonotfound.nemos.carpentry.block.CarpentryBlocks;
import com.nemonotfound.nemos.carpentry.recipe.CarpentryRecipe;
import com.nemonotfound.nemos.carpentry.recipe.CarpentryRecipeInput;
import com.nemonotfound.nemos.carpentry.recipe.CarpentryRecipeService;
import com.nemonotfound.nemos.carpentry.recipe.display.CarpentryRecipeDisplay;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Prediction;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;

import java.util.List;

import static com.nemonotfound.nemos.carpentry.screen.CarpentryMenuTypes.CARPENTRY_SCREEN_HANDLER;

public class CarpentryMenu extends AbstractContainerMenu {
    private final ContainerLevelAccess access;
    private final Level level;
    private final DataSlot selectedRecipeIndex = DataSlot.standalone();
    private CarpentryRecipeDisplay.Grouping availableRecipes = CarpentryRecipeDisplay.Grouping.empty();
    private Identifier selectedId;
    private boolean consuming;
    private long lastTakeTime;
    private Runnable slotUpdateListener = () -> {};
    public final Container input = new SimpleContainer(2) {
        @Override
        public void setChanged() {
            super.setChanged();
            if (!consuming) {
                CarpentryMenu.this.slotsChanged(this);
            }
        }
    };
    private final ResultContainer output = new ResultContainer();
    private final Slot outputSlot;

    public CarpentryMenu(int syncId, Inventory inventory) {
        this(syncId, inventory, ContainerLevelAccess.NULL);
    }

    public CarpentryMenu(int syncId, Inventory inventory, ContainerLevelAccess access) {
        super(CARPENTRY_SCREEN_HANDLER, syncId);
        this.level = inventory.player.level();
        this.access = access;
        selectedRecipeIndex.set(-1);
        addSlot(new Slot(input, 0, 20, 19));
        addSlot(new Slot(input, 1, 20, 47));
        outputSlot = addSlot(new Slot(output, 0, 143, 33) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }

            @Override
            public boolean mayPickup(Player player) {
                if (level.isClientSide()) {
                    return canCraftSelectedRecipe();
                }
                return selectedServerRecipe() != null;
            }

            @Override
            public void onTake(Player player, ItemStack stack) {
                var holder = selectedServerRecipe();
                if (holder == null) {
                    setupResultSlot();
                    return;
                }
                var recipe = holder.value();
                stack.onCraftedBy(player, stack.getCount());
                output.awardUsedRecipes(player, List.of(input.getItem(0).copy(), input.getItem(1).copy()));
                // Avoid re-entrant selection changes halfway through consuming two materials.
                consuming = true;
                try {
                    for (int slot = 0; slot < recipe.inputCounts().size(); slot++) {
                        input.removeItem(slot, recipe.inputCounts().get(slot));
                    }
                } finally {
                    consuming = false;
                }
                slotsChanged(input);
                access.execute((world, pos) -> {
                    long time = world.getGameTime();
                    if (lastTakeTime != time) {
                        world.playSound(null, pos, SoundEvents.UI_STONECUTTER_TAKE_RESULT, SoundSource.BLOCKS, 1, 1);
                        lastTakeTime = time;
                    }
                });
                super.onTake(player, stack);
            }
        });
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 9; column++) {
                addSlot(new Slot(inventory, column + row * 9 + 9, 8 + column * 18, 84 + row * 18));
            }
        }
        for (int column = 0; column < 9; column++) {
            addSlot(new Slot(inventory, column, 8 + column * 18, 142));
        }
        addDataSlot(selectedRecipeIndex);
        refreshRecipes();
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        if (index < 0 || index >= slots.size()) {
            return ItemStack.EMPTY;
        }
        Slot slot = slots.get(index);
        if (!slot.hasItem() || !slot.mayPickup(player)) {
            return ItemStack.EMPTY;
        }
        ItemStack moving = slot.getItem();
        ItemStack original = moving.copy();
        if (index == 2) {
            if (!moveItemStackTo(moving, 3, 39, true)) {
                return ItemStack.EMPTY;
            }
            slot.onQuickCraft(moving, original);
        } else if (index < 2) {
            if (!moveItemStackTo(moving, 3, 39, false)) {
                return ItemStack.EMPTY;
            }
        } else if (isSecondIngredient(moving)) {
            if (!moveItemStackTo(moving, 1, 2, false)) {
                return ItemStack.EMPTY;
            }
        } else if (!CarpentryRecipeService.getRecipes(level).filter(moving).isEmpty()) {
            if (!moveItemStackTo(moving, 0, 1, false)) {
                return ItemStack.EMPTY;
            }
        } else if (index < 30) {
            if (!moveItemStackTo(moving, 30, 39, false)) {
                return ItemStack.EMPTY;
            }
        } else if (!moveItemStackTo(moving, 3, 30, false)) {
            return ItemStack.EMPTY;
        }
        if (moving.getCount() == original.getCount()) {
            return ItemStack.EMPTY;
        }
        if (moving.isEmpty()) {
            slot.set(ItemStack.EMPTY);
        } else {
            slot.setChanged();
        }
        slot.onTake(player, original);
        if (index == 2 && !moving.isEmpty()) {
            // A partial inventory transfer still consumes one complete recipe.
            // Preserve the remainder before the result slot is populated again.
            player.drop(moving, false, Prediction.PREDICTED);
        }
        broadcastChanges();
        return original;
    }

    private boolean isSecondIngredient(ItemStack stack) {
        return CarpentryRecipeService.getRecipes(level).filter(input.getItem(0)).entries().stream()
                .anyMatch(entry -> entry.ingredients().size() == 2 && entry.ingredients().get(1).test(stack));
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(access, player, CarpentryBlocks.CARPENTERS_WORKBENCH);
    }

    @Override
    public boolean canTakeItemForPickAll(ItemStack stack, Slot slot) {
        return slot.container != output && super.canTakeItemForPickAll(stack, slot);
    }

    /** All selections use stable IDs and a snapshot revision, including client prediction. */
    public boolean selectRecipe(long revision, Identifier id) {
        if (revision != CarpentryRecipeService.getRecipes(level).revision() || revision != availableRecipes.revision()) {
            refreshRecipes();
            return false;
        }
        for (int index = 0; index < availableRecipes.size(); index++) {
            if (availableRecipes.entries().get(index).id().equals(id) && canCraftRecipe(index)) {
                selectedId = id;
                selectedRecipeIndex.set(index);
                setupResultSlot();
                return true;
            }
        }
        clearSelection();
        setupResultSlot();
        return false;
    }

    private void clearSelection() {
        selectedId = null;
        selectedRecipeIndex.set(-1);
    }

    public void refreshRecipes() {
        if (level.isClientSide() && outputSlot != null) {
            outputSlot.set(ItemStack.EMPTY);
        }
        clearSelection();
        slotsChanged(input);
    }

    @Override
    public void slotsChanged(Container container) {
        var snapshot = CarpentryRecipeService.getRecipes(level);
        if (snapshot.revision() != availableRecipes.revision()) {
            clearSelection();
        }
        availableRecipes = snapshot.filter(input.getItem(0));
        int selected = -1;
        for (int index = 0; index < availableRecipes.size(); index++) {
            if (availableRecipes.entries().get(index).id().equals(selectedId)) {
                selected = index;
                break;
            }
        }
        selectedRecipeIndex.set(selected);
        if (selected == -1) {
            selectedId = null;
        }
        setupResultSlot();
        slotUpdateListener.run();
    }

    private CarpentryRecipeInput recipeInput() {
        return new CarpentryRecipeInput(input.getItem(0), input.getItem(1));
    }

    private RecipeHolder<CarpentryRecipe> selectedServerRecipe() {
        if (selectedId == null) {
            return null;
        }
        var holder = CarpentryRecipeService.find(level, availableRecipes.revision(), selectedId);
        return holder != null && holder.value().matches(recipeInput(), level) ? holder : null;
    }

    private void setupResultSlot() {
        if (level.isClientSide() || outputSlot == null) {
            return;
        }
        var holder = selectedServerRecipe();
        output.setRecipeUsed(holder);
        outputSlot.set(holder == null ? ItemStack.EMPTY : holder.value().assemble(recipeInput()));
        broadcastChanges();
    }

    public boolean canCraftRecipe(int index) {
        if (index < 0 || index >= availableRecipes.size()) {
            return false;
        }
        var recipe = availableRecipes.entries().get(index);
        for (int slot = 0; slot < recipe.ingredients().size(); slot++) {
            var stack = input.getItem(slot);
            if (!recipe.ingredients().get(slot).test(stack) || stack.getCount() < recipe.inputCounts().get(slot)) {
                return false;
            }
        }
        return true;
    }

    public boolean canCraftSelectedRecipe() {
        return canCraftRecipe(getSelectedRecipeIndex());
    }

    public int getSelectedRecipeIndex() {
        return selectedRecipeIndex.get();
    }

    public CarpentryRecipeDisplay.Grouping getAvailableRecipes() {
        return availableRecipes;
    }

    public int getAvailableRecipeCount() {
        return availableRecipes.size();
    }

    public boolean hasAvailableRecipes() {
        return !availableRecipes.isEmpty();
    }

    public void setSlotUpdateListener(Runnable listener) {
        slotUpdateListener = listener;
        listener.run();
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        output.removeItemNoUpdate(0);
        access.execute((world, pos) -> clearContainer(player, input));
    }
}
