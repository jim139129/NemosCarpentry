package com.nemonotfound.nemos.carpentry.screen;

import com.nemonotfound.nemos.carpentry.recipe.display.CarpentryRecipeDisplay;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import com.nemonotfound.nemos.carpentry.network.SelectCarpentryRecipePayload;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.context.ContextMap;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplayContext;
import org.jetbrains.annotations.NotNull;

import static com.nemonotfound.nemos.carpentry.NemosCarpentry.MOD_ID;

@Environment(value= EnvType.CLIENT)
public class CarpentryScreen extends AbstractContainerScreen<CarpentryMenu> {

    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(MOD_ID, "textures/gui/container/carpenters_workbench.png");
    private static final Identifier SCROLLER_TEXTURE = Identifier.fromNamespaceAndPath(MOD_ID, "container/carpenters_workbench/scroller");
    private static final Identifier SCROLLER_DISABLED_TEXTURE = Identifier.fromNamespaceAndPath(MOD_ID, "container/carpenters_workbench/scroller_disabled");
    private static final Identifier RECIPE_SELECTED_TEXTURE = Identifier.fromNamespaceAndPath(MOD_ID, "container/carpenters_workbench/recipe_selected");
    private static final Identifier RECIPE_HIGHLIGHTED_TEXTURE = Identifier.fromNamespaceAndPath(MOD_ID, "container/carpenters_workbench/recipe_highlighted");
    private static final Identifier RECIPE_DISABLED_TEXTURE = Identifier.fromNamespaceAndPath(MOD_ID, "container/carpenters_workbench/recipe_disabled");
    private static final Identifier RECIPE_TEXTURE = Identifier.fromNamespaceAndPath(MOD_ID, "container/carpenters_workbench/recipe");

    private static final int RECIPES_IMAGE_SIZE_WIDTH = 16;
    private static final int RECIPES_IMAGE_SIZE_HEIGHT = 18;
    private static final int RELATIVE_RECIPE_X = 52;
    private static final int RELATIVE_RECIPE_Y = 14;
    private float scrollAmount;
    private int firstVisibleRecipeIndex;
    private boolean scrolling;
    private boolean hasAvailableRecipes;

    public CarpentryScreen(CarpentryMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
        menu.setSlotUpdateListener(this::onInventoryChange);
        --this.titleLabelY;
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float delta) {
        super.extractBackground(guiGraphics, mouseX, mouseY, delta);
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, leftPos, topPos, 0.0F, 0.0F, this.imageWidth, this.imageHeight, 256, 256);
        drawScroll(guiGraphics);

        this.renderRecipeBackground(guiGraphics, mouseX, mouseY, leftPos + 52, topPos + 14, firstVisibleRecipeIndex + 12);
        this.renderRecipeIcons(guiGraphics, leftPos + 52, topPos + 14, firstVisibleRecipeIndex + 12);
    }

    @Override
    public void extractRenderState(@NotNull GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float delta) {
        super.extractRenderState(guiGraphics, mouseX, mouseY, delta);
        this.extractTooltip(guiGraphics, mouseX, mouseY);
    }

    //TODO: REFACTOR
    @Override
    public boolean mouseClicked(@NotNull MouseButtonEvent mouseButtonEvent, boolean isDoubleClick) {
        this.scrolling = false;

        if (hasAvailableRecipes && mouseButtonEvent.button() == InputConstants.MOUSE_BUTTON_LEFT) {
            int firstRecipeX = this.leftPos + RELATIVE_RECIPE_X;
            int firstRecipeY = this.topPos + RELATIVE_RECIPE_Y;
            var maxVisibleRecipeCount = 12;
            int lastVisibleRecipeIndex = this.firstVisibleRecipeIndex + maxVisibleRecipeCount;

            for (int recipeIndex = this.firstVisibleRecipeIndex; recipeIndex < Math.min(lastVisibleRecipeIndex, menu.getAvailableRecipeCount()); ++recipeIndex) {
                int visibleRecipeIndex = recipeIndex - this.firstVisibleRecipeIndex;
                double mouseDistanceToRecipeX = mouseButtonEvent.x() - (double) (firstRecipeX + visibleRecipeIndex % 4 * 16);
                double mouseDistanceToRecipeY = mouseButtonEvent.y() - (double) (firstRecipeY + visibleRecipeIndex / 4 * 18);

                if (mouseDistanceToRecipeX >= 0.0 && mouseDistanceToRecipeY >= 0.0 && mouseDistanceToRecipeX < RECIPES_IMAGE_SIZE_WIDTH && mouseDistanceToRecipeY < RECIPES_IMAGE_SIZE_HEIGHT) {
                    var recipes = menu.getAvailableRecipes();
                    var recipe = recipes.entries().get(recipeIndex);
                    boolean selected = menu.selectRecipe(recipes.revision(), recipe.id());
                    Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(
                            SoundEvents.UI_STONECUTTER_SELECT_RECIPE, selected ? 1.0f : 4.0f));
                    if (selected) {
                        ClientPlayNetworking.send(new SelectCarpentryRecipePayload(menu.containerId, recipes.revision(), recipe.id()));
                    }
                    return true;
                }
            }
            firstRecipeX = this.leftPos + 119;
            firstRecipeY = this.topPos + 9;

            if (mouseButtonEvent.x() >= (double) firstRecipeX && mouseButtonEvent.x() < (double) (firstRecipeX + 12) && mouseButtonEvent.y() >= (double) firstRecipeY && mouseButtonEvent.y() < (double) (firstRecipeY + 54)) {
                this.scrolling = true;
            }
        }

        return super.mouseClicked(mouseButtonEvent, isDoubleClick);
    }

    @Override
    protected boolean hasClickedOutside(double mouseX, double mouseY, int left, int top) {
        return mouseX < (double) left - 22 ||
                mouseY < (double) top ||
                mouseX >= (double) (left + this.imageWidth) ||
                mouseY >= (double) (top + this.imageHeight);
    }

    //TODO: REFACTOR
    @Override
    public boolean mouseDragged(@NotNull MouseButtonEvent mouseButtonEvent, double offsetX, double offsetY) {
        if (this.scrolling && this.shouldScroll()) {
            int i = this.topPos + 14;
            int j = i + 54;
            this.scrollAmount = ((float) mouseButtonEvent.y() - (float) i - 7.5f) / ((float) (j - i) - 15.0f);
            this.scrollAmount = Mth.clamp(this.scrollAmount, 0.0f, 1.0f);
            this.firstVisibleRecipeIndex = (int) ((double) (this.scrollAmount * (float) this.getMaxScroll()) + 0.5) * 4;
            return true;
        }

        return super.mouseDragged(mouseButtonEvent, offsetX, offsetY);
    }

    //TODO: REFACTOR
    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
        if (this.shouldScroll()) {
            int i = this.getMaxScroll();
            float f = (float) verticalAmount / (float) i;
            this.scrollAmount = Mth.clamp(this.scrollAmount - f, 0.0f, 1.0f);
            this.firstVisibleRecipeIndex = (int) ((double) (this.scrollAmount * (float) i) + 0.5) * 4;
        }
        return true;
    }

    //TODO: REFACTOR
    @Override
    protected void extractTooltip(@NotNull GuiGraphicsExtractor guiGraphics, int x, int y) {
        super.extractTooltip(guiGraphics, x, y);

        int toolPosX = this.leftPos + 52;
        int toolPosY = this.topPos + 14;
        int scrollOffset = this.firstVisibleRecipeIndex + 12;
        CarpentryRecipeDisplay.Grouping availableRecipes = this.menu.getAvailableRecipes();

        for (int l = this.firstVisibleRecipeIndex; l < scrollOffset && l < availableRecipes.size(); ++l) {
            int m = l - this.firstVisibleRecipeIndex;
            int n = toolPosX + m % 4 * 16;
            int o = toolPosY + m / 4 * 18 + 2;
            if (x >= n && x < n + 16 && y >= o && y < o + 18) {
                ContextMap contextMap = SlotDisplayContext.fromLevel(this.minecraft.level);
                SlotDisplay slotDisplay = availableRecipes.entries().get(l).optionDisplay();
                guiGraphics.setTooltipForNextFrame(this.font, slotDisplay.resolveForFirstStack(contextMap), x, y);
            }
        }
    }

    //TODO: REFACTOR
    private void renderRecipeBackground(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, int x, int y, int scrollOffset) {
        for (int i = this.firstVisibleRecipeIndex; i < scrollOffset && i < this.menu.getAvailableRecipeCount(); ++i) {
            int j = i - this.firstVisibleRecipeIndex;
            int k = x + j % 4 * 16;
            int l = j / 4;
            int m = y + l * 18 + 2;

            if (!this.menu.canCraftRecipe(i)) {
                guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED, RECIPE_DISABLED_TEXTURE, k, m - 1, 16, 18);
            } else {
                renderRecipeBackgroundForCraftableRecipe(guiGraphics, i, mouseX, mouseY, k, m);
            }
        }
    }

    private void renderRecipeBackgroundForCraftableRecipe(GuiGraphicsExtractor guiGraphics, int i, int mouseX, int mouseY, int k, int m) {
        Identifier identifier = i == this.menu.getSelectedRecipeIndex() ? RECIPE_SELECTED_TEXTURE :
                (mouseX >= k && mouseY >= m && mouseX < k + 16 && mouseY < m + 18 ? RECIPE_HIGHLIGHTED_TEXTURE : RECIPE_TEXTURE);
        guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED, identifier, k, m - 1, 16, 18);
    }

    //TODO: REFACTOR
    private void renderRecipeIcons(GuiGraphicsExtractor guiGraphics, int x, int y, int scrollOffset) {
        CarpentryRecipeDisplay.Grouping availableRecipes = this.menu.getAvailableRecipes();
        ContextMap contextMap = SlotDisplayContext.fromLevel(this.minecraft.level);

        for (int i = this.firstVisibleRecipeIndex; i < scrollOffset && i < availableRecipes.size(); ++i) {
            int yPosWithoutScrollOffset = i - this.firstVisibleRecipeIndex;
            int k = x + yPosWithoutScrollOffset % 4 * 16;
            int l = yPosWithoutScrollOffset / 4;
            int m = y + l * 18 + 2;
            SlotDisplay slotDisplay = (availableRecipes.entries().get(i)).optionDisplay();
            guiGraphics.item(slotDisplay.resolveForFirstStack(contextMap), k, m);
        }
    }

    private void drawScroll(GuiGraphicsExtractor guiGraphics) {
        int yPosAfterScrolling = (int) (41.0f * this.scrollAmount);
        Identifier identifier = this.shouldScroll() ? SCROLLER_TEXTURE : SCROLLER_DISABLED_TEXTURE;
        guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED, identifier, leftPos + 119, topPos + 15 + yPosAfterScrolling, 12, 15);
    }

    private boolean shouldScroll() {
        return (this.menu).getAvailableRecipeCount() > 12;
    }

    protected int getMaxScroll() {
        return ((this.menu).getAvailableRecipeCount() + 4 - 1) / 4 - 3;
    }

    private void onInventoryChange() {
        hasAvailableRecipes = this.menu.hasAvailableRecipes();
        if (!hasAvailableRecipes || firstVisibleRecipeIndex > Math.max(0, getMaxScroll()) * 4) {
            this.scrollAmount = 0.0f;
            this.firstVisibleRecipeIndex = 0;
        }
    }
}
