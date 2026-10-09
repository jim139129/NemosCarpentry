package com.nemonotfound.nemos.carpentry.block;

import com.nemonotfound.nemos.carpentry.screen.CarpentryMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.nemonotfound.nemos.carpentry.NemosCarpentry.MOD_ID;

public class CarpentersWorkbenchBlock extends Block {

    private static final Component TITLE = Component.translatable(MOD_ID + ".container.carpenters_workbench");

    public CarpentersWorkbenchBlock(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull RenderShape getRenderShape(@NotNull BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public @NotNull InteractionResult useWithoutItem(@NotNull BlockState state, Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull BlockHitResult hit) {
        if (!level.isClientSide()) {
            player.openMenu(createScreenHandlerFactory(level, pos));
            player.awardStat(Stats.ITEM_USED.get(this.asItem()));
        }

        return InteractionResult.SUCCESS;
    }

    @Nullable
    protected MenuProvider createScreenHandlerFactory(Level level, BlockPos pos) {
        return new SimpleMenuProvider(
                (syncId, playerInventory, player) ->
                        new CarpentryMenu(syncId, playerInventory, ContainerLevelAccess.create(level, pos)), TITLE);
    }
}
