package com.nemonotfound.nemos.carpentry.block.seats;

import com.nemonotfound.nemos.carpentry.block.seats.parents.SitableBlock;
import com.nemonotfound.nemos.carpentry.entity.CarpentryEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class LogSeat extends SitableBlock implements SimpleWaterloggedBlock {


    private static final VoxelShape SOUTH_SHAPE = Block.box(2, 0, 5, 14, 6, 11);
    private static final VoxelShape WEST_SHAPE = Block.box(5, 0, 2, 11, 6, 14);
    private static final VoxelShape NORTH_SHAPE = Block.box(2, 0, 5, 14, 6, 11);
    private static final VoxelShape EAST_SHAPE = Block.box(5, 0, 2, 11, 6, 14);

    public LogSeat(Properties properties) {
        super(properties, 0.4f, NORTH_SHAPE, EAST_SHAPE, SOUTH_SHAPE, WEST_SHAPE);
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(FACING, Direction.NORTH)
                .setValue(WATERLOGGED, false));
    }

    @Override
    protected InteractionResult sitEntity(Level level, BlockPos pos, BlockState state, Entity entityToSit) {
        double posX = pos.getX() + 0.5;
        double posZ = pos.getZ() + 0.5;
        double posY = pos.getY() + this.height;
        float yaw = entityToSit.getYRot();
        this.chairEntity = CarpentryEntities.CHAIR_ENTITY.create(level, EntitySpawnReason.DISPENSER);

        chairEntity.snapTo(posX, posY, posZ, yaw, 0);
        chairEntity.setNoGravity(true);
        chairEntity.setSilent(true);
        chairEntity.setInvisible(false);
        chairEntity.setPermanentlyInvulnerable(true);

        if (level.addFreshEntity(chairEntity)) {
            entityToSit.setYRot(yaw);
            entityToSit.setYBodyRot(yaw);
            entityToSit.setYHeadRot(yaw);
            entityToSit.startRiding(chairEntity);

            return InteractionResult.SUCCESS;
        }
        return InteractionResult.CONSUME;
    }
}
