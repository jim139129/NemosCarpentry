package com.nemonotfound.nemos.carpentry.block.seats.parents;

import com.nemonotfound.nemos.carpentry.entity.ChairEntity;
import com.nemonotfound.nemos.carpentry.entity.CarpentryEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public abstract class SitableBlock extends HorizontalDirectionalBlock {

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    private final VoxelShape northShape;
    private final VoxelShape eastShape;
    private final VoxelShape southShape;
    private final VoxelShape westShape;
    protected ChairEntity chairEntity;
    public float height;

    public SitableBlock(Properties properties, float height, VoxelShape northShape, VoxelShape eastShape,
                        VoxelShape southShape, VoxelShape westShape) {
        super(properties);
        registerDefaultState(this.getStateDefinition().any().setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH));
        this.northShape = northShape;
        this.eastShape = eastShape;
        this.southShape = southShape;
        this.westShape = westShape;
        this.height = height;
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter blockGetter, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return getShapeIndex(state);
    }

    @Override
    public @NotNull VoxelShape getCollisionShape(@NotNull BlockState state, @NotNull BlockGetter blockGetter, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return getShapeIndex(state);
    }

    private VoxelShape getShapeIndex(BlockState state) {
        switch (state.getValue(FACING)) {
            case NORTH -> {
                return northShape;
            }
            case SOUTH -> {
                return southShape;
            }
            case EAST -> {
                return eastShape;
            }
            case WEST -> {
                return westShape;
            }
        }

        return northShape;
    }

    @Override
    public @NotNull RenderShape getRenderShape(@NotNull BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public @NotNull BlockState updateShape(
            BlockState state,
            @NotNull LevelReader levelReader,
            @NotNull ScheduledTickAccess scheduledTickAccess,
            @NotNull BlockPos pos,
            @NotNull Direction direction,
            @NotNull BlockPos neighborPos,
            @NotNull BlockState neighborState,
            @NotNull RandomSource random
    ) {
        if (state.getValue(WATERLOGGED)) {
            scheduledTickAccess.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(levelReader));
        }

        return super.updateShape(state, levelReader, scheduledTickAccess, pos, direction, neighborPos, neighborState, random);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        FluidState fluidState = ctx.getLevel().getFluidState(ctx.getClickedPos());

        return this.defaultBlockState()
                .setValue(FACING, ctx.getHorizontalDirection().getOpposite())
                .setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
    }

    @Override
    public @NotNull FluidState getFluidState(BlockState state) {
        if (state.getValue(WATERLOGGED)) {
            return Fluids.WATER.getSource(false);
        }

        return super.getFluidState(state);
    }

    @Override
    public @NotNull InteractionResult useWithoutItem(@NotNull BlockState state, Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull BlockHitResult hit) {
        if (level.isClientSide()) {
            return InteractionResult.CONSUME;
        }

        if (player.isSpectator() || player.isShiftKeyDown()) {
            return InteractionResult.FAIL;
        }

        List<ChairEntity> active = level.getEntitiesOfClass(ChairEntity.class, new AABB(pos), Entity::isVehicle);
        List<Entity> hasPassenger = new ArrayList<>();
        active.forEach(chairEntity -> hasPassenger.add(chairEntity.getFirstPassenger()));

        if (!active.isEmpty() && hasPassenger.stream().anyMatch(Entity::isAlwaysTicking)) {
            return InteractionResult.FAIL;
        } else if (!active.isEmpty()) {
            hasPassenger.forEach(Entity::stopRiding);
            return InteractionResult.SUCCESS;
        } else if (sitEntity(level, pos, state, player) == InteractionResult.SUCCESS) {
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.CONSUME;
    }


    protected InteractionResult sitEntity(Level level, BlockPos pos, BlockState state, Entity entityToSit) {
        double posX;
        double posZ;

        Direction direction = state.getValue(FACING);
        switch (direction) {
            case EAST -> {
                posX = pos.getX() + 0.4;
                posZ = pos.getZ() + 0.5;
            }
            case WEST -> {
                posX = pos.getX() + 0.6;
                posZ = pos.getZ() + 0.5;
            }
            case SOUTH -> {
                posX = pos.getX() + 0.5;
                posZ = pos.getZ() + 0.4;
            }
            default -> {
                posX = pos.getX() + 0.5;
                posZ = pos.getZ() + 0.6;
            }
        }

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

    @Override
    protected boolean isPathfindable(@NotNull BlockState state, @NotNull PathComputationType type) {
        return false;
    }

    @Override
    public void destroy(@NotNull LevelAccessor levelAccessor, @NotNull BlockPos pos, @NotNull BlockState state) {
        if (chairEntity != null) {
            chairEntity.discard();
        }
    }

    @Override
    public @NotNull BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    public @NotNull BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, @NotNull BlockState> builder) {
        builder.add(FACING, WATERLOGGED);
    }
}

