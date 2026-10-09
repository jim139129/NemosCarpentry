package com.nemonotfound.nemos.carpentry.block.seats;

import com.nemonotfound.nemos.carpentry.block.enums.BenchPart;
import com.nemonotfound.nemos.carpentry.block.seats.parents.OldSitableBlock;
import com.nemonotfound.nemos.carpentry.property.CarpentryBlockStateProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Stream;

import static net.minecraft.core.Direction.*;

public class ParkBenchBlock extends OldSitableBlock implements SimpleWaterloggedBlock {

    public static final EnumProperty<@NotNull BenchPart> PART = CarpentryBlockStateProperties.BENCH_BLOCK_PART;

    private static final VoxelShape NORTH_SHAPE_LEFT = Stream.of(
            Block.box(12, 0, 3, 14, 8, 5),
            Block.box(12, 0, 10, 14, 8, 12),
            Block.box(-14, 0, 10, -12, 8, 12),
            Block.box(-14, 0, 3, -12, 8, 5),
            Block.box(-15, 8, 2, 15, 9, 5),
            Block.box(-15, 8, 6, 15, 9, 9),
            Block.box(-15, 8, 10, 15, 9, 13),
            Block.box(-13.5, 6, 5, -12.5, 8, 10),
            Block.box(12.5, 6, 5, 13.5, 8, 10),
            Block.box(-12, 6, 3.5, 12, 8, 4.5),
            Block.box(-12, 6, 10.5, 12, 8, 11.5),
            Stream.of(
                    Block.box(9, 8, 13, 11, 16, 14),
                    Block.box(-11, 8, 13, -9, 16, 14),
                    Block.box(-15, 10, 12, 15, 13, 13),
                    Block.box(-15, 14, 12, 15, 17, 13)
            ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get()
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape NORTH_SHAPE_RIGHT = Stream.of(
            Block.box(28, 0, 3, 30, 8, 5),
            Block.box(28, 0, 10, 30, 8, 12),
            Block.box(2, 0, 10, 4, 8, 12),
            Block.box(2, 0, 3, 4, 8, 5),
            Block.box(1, 8, 2, 31, 9, 5),
            Block.box(1, 8, 6, 31, 9, 9),
            Block.box(1, 8, 10, 31, 9, 13),
            Block.box(2.5, 6, 5, 3.5, 8, 10),
            Block.box(28.5, 6, 5, 29.5, 8, 10),
            Block.box(4, 6, 3.5, 28, 8, 4.5),
            Block.box(4, 6, 10.5, 28, 8, 11.5),
            Stream.of(
                    Block.box(25, 8, 13, 27, 16, 14),
                    Block.box(5, 8, 13, 7, 16, 14),
                    Block.box(1, 10, 12, 31, 13, 13),
                    Block.box(1, 14, 12, 31, 17, 13)
            ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get()
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape EAST_SHAPE_LEFT = Stream.of(
            Block.box(11, 0, 12, 13, 8, 14),
            Block.box(4, 0, 12, 6, 8, 14),
            Block.box(4, 0, -14, 6, 8, -12),
            Block.box(11, 0, -14, 13, 8, -12),
            Block.box(11, 8, -15, 14, 9, 15),
            Block.box(7, 8, -15, 10, 9, 15),
            Block.box(3, 8, -15, 6, 9, 15),
            Block.box(6, 6, -13.5, 11, 8, -12.5),
            Block.box(6, 6, 12.5, 11, 8, 13.5),
            Block.box(11.5, 6, -12, 12.5, 8, 12),
            Block.box(4.5, 6, -12, 5.5, 8, 12),
            Stream.of(
                    Block.box(2, 8, 9, 3, 16, 11),
                    Block.box(2, 8, -11, 3, 16, -9),
                    Block.box(3, 10, -15, 4, 13, 15),
                    Block.box(3, 14, -15, 4, 17, 15)
            ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get()
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape EAST_SHAPE_RIGHT = Stream.of(
            Block.box(11, 0, 28, 13, 8, 30),
            Block.box(4, 0, 28, 6, 8, 30),
            Block.box(4, 0, 2, 6, 8, 4),
            Block.box(11, 0, 2, 13, 8, 4),
            Block.box(11, 8, 1, 14, 9, 31),
            Block.box(7, 8, 1, 10, 9, 31),
            Block.box(3, 8, 1, 6, 9, 31),
            Block.box(6, 6, 2.5, 11, 8, 3.5),
            Block.box(6, 6, 28.5, 11, 8, 29.5),
            Block.box(11.5, 6, 4, 12.5, 8, 28),
            Block.box(4.5, 6, 4, 5.5, 8, 28),
            Stream.of(
                    Block.box(2, 8, 25, 3, 16, 27),
                    Block.box(2, 8, 5, 3, 16, 7),
                    Block.box(3, 10, 1, 4, 13, 31),
                    Block.box(3, 14, 1, 4, 17, 31)
            ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get()
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape SOUTH_SHAPE_LEFT = Stream.of(
            Block.box(2, 0, 11, 4, 8, 13),
            Block.box(2, 0, 4, 4, 8, 6),
            Block.box(28, 0, 4, 30, 8, 6),
            Block.box(28, 0, 11, 30, 8, 13),
            Block.box(1, 8, 11, 31, 9, 14),
            Block.box(1, 8, 7, 31, 9, 10),
            Block.box(1, 8, 3, 31, 9, 6),
            Block.box(28.5, 6, 6, 29.5, 8, 11),
            Block.box(2.5, 6, 6, 3.5, 8, 11),
            Block.box(4, 6, 11.5, 28, 8, 12.5),
            Block.box(4, 6, 4.5, 28, 8, 5.5),
            Stream.of(
                    Block.box(5, 8, 2, 7, 16, 3),
                    Block.box(25, 8, 2, 27, 16, 3),
                    Block.box(1, 10, 3, 31, 13, 4),
                    Block.box(1, 14, 3, 31, 17, 4)
            ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get()
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape SOUTH_SHAPE_RIGHT = Stream.of(
            Block.box(-14, 0, 11, -12, 8, 13),
            Block.box(-14, 0, 4, -12, 8, 6),
            Block.box(12, 0, 4, 14, 8, 6),
            Block.box(12, 0, 11, 14, 8, 13),
            Block.box(-15, 8, 11, 15, 9, 14),
            Block.box(-15, 8, 7, 15, 9, 10),
            Block.box(-15, 8, 3, 15, 9, 6),
            Block.box(12.5, 6, 6, 13.5, 8, 11),
            Block.box(-13.5, 6, 6, -12.5, 8, 11),
            Block.box(-12, 6, 11.5, 12, 8, 12.5),
            Block.box(-12, 6, 4.5, 12, 8, 5.5),
            Stream.of(
                    Block.box(-11, 8, 2, -9, 16, 3),
                    Block.box(9, 8, 2, 11, 16, 3),
                    Block.box(-15, 10, 3, 15, 13, 4),
                    Block.box(-15, 14, 3, 15, 17, 4)
            ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get()
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape WEST_SHAPE_LEFT = Stream.of(
            Block.box(3, 0, 2, 5, 8, 4),
            Block.box(10, 0, 2, 12, 8, 4),
            Block.box(10, 0, 28, 12, 8, 30),
            Block.box(3, 0, 28, 5, 8, 30),
            Block.box(2, 8, 1, 5, 9, 31),
            Block.box(6, 8, 1, 9, 9, 31),
            Block.box(10, 8, 1, 13, 9, 31),
            Block.box(5, 6, 28.5, 10, 8, 29.5),
            Block.box(5, 6, 2.5, 10, 8, 3.5),
            Block.box(3.5, 6, 4, 4.5, 8, 28),
            Block.box(10.5, 6, 4, 11.5, 8, 28),
            Stream.of(
                    Block.box(13, 8, 5, 14, 16, 7),
                    Block.box(13, 8, 25, 14, 16, 27),
                    Block.box(12, 10, 1, 13, 13, 31),
                    Block.box(12, 14, 1, 13, 17, 31)
            ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get()
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape WEST_SHAPE_RIGHT = Stream.of(
            Block.box(3, 0, -14, 5, 8, -12),
            Block.box(10, 0, -14, 12, 8, -12),
            Block.box(10, 0, 12, 12, 8, 14),
            Block.box(3, 0, 12, 5, 8, 14),
            Block.box(2, 8, -15, 5, 9, 15),
            Block.box(6, 8, -15, 9, 9, 15),
            Block.box(10, 8, -15, 13, 9, 15),
            Block.box(5, 6, 12.5, 10, 8, 13.5),
            Block.box(5, 6, -13.5, 10, 8, -12.5),
            Block.box(3.5, 6, -12, 4.5, 8, 12),
            Block.box(10.5, 6, -12, 11.5, 8, 12),
            Stream.of(
                    Block.box(13, 8, -11, 14, 16, -9),
                    Block.box(13, 8, 9, 14, 16, 11),
                    Block.box(12, 10, -15, 13, 13, 15),
                    Block.box(12, 14, -15, 13, 17, 15)
            ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get()
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public ParkBenchBlock(BlockBehaviour.Properties properties) {
        super(properties, 0.6f);
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(FACING, Direction.NORTH)
                .setValue(WATERLOGGED, false)
                .setValue(PART, BenchPart.LEFT));
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
        BenchPart benchPart = state.getValue(PART);
        boolean isBenchPartLeft = benchPart == BenchPart.LEFT;

        switch (state.getValue(FACING)) {
            case NORTH -> {
                return isBenchPartLeft ? NORTH_SHAPE_LEFT : NORTH_SHAPE_RIGHT;
            }
            case SOUTH -> {
                return isBenchPartLeft ? SOUTH_SHAPE_LEFT : SOUTH_SHAPE_RIGHT;
            }
            case EAST -> {
                return isBenchPartLeft ? EAST_SHAPE_LEFT : EAST_SHAPE_RIGHT;
            }
            case WEST -> {
                return isBenchPartLeft ? WEST_SHAPE_LEFT : WEST_SHAPE_RIGHT;
            }
        }
        return NORTH_SHAPE_LEFT;
    }

    @Override
    public @NotNull BlockState updateShape(
            BlockState state,
            @NotNull LevelReader levelReader,
            @NotNull ScheduledTickAccess tickView,
            @NotNull BlockPos pos,
            @NotNull Direction direction,
            @NotNull BlockPos neighborPos,
            @NotNull BlockState neighborState,
            @NotNull RandomSource random
    ) {
        if (state.getValue(WATERLOGGED)) {
            tickView.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(levelReader));
        }

        if (direction == getDirectionToOtherBenchHalf(state.getValue(FACING), state.getValue(PART)) && (!neighborState.is(this) ||
                neighborState.getValue(PART) == state.getValue(PART))) {
            return Blocks.AIR.defaultBlockState();
        }

        return super.updateShape(state, levelReader, tickView, pos, direction, neighborPos, neighborState, random);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        var level = context.getLevel();
        BlockPos blockPos = context.getClickedPos();
        Direction direction = context.getHorizontalDirection().getOpposite();
        Direction directionToOtherBenchHalf = getDirectionToOtherBenchHalf(direction, BenchPart.LEFT);
        BlockPos blockPosOfOtherBenchPart = blockPos.relative(directionToOtherBenchHalf);

        if (canBlockBePlaced(level, blockPosOfOtherBenchPart, context)) {
            FluidState fluidState = level.getFluidState(context.getClickedPos());

            return this.defaultBlockState()
                    .setValue(FACING, direction)
                    .setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
        }

        return null;
    }

    private boolean canBlockBePlaced(Level level, BlockPos blockPos, BlockPlaceContext ctx) {
        boolean canSecondBenchPartBePlaced = level.getBlockState(blockPos).canBeReplaced(ctx);
        boolean isSecondBenchPartInWorldBorder = level.getWorldBorder().isWithinBounds(blockPos);

        return canSecondBenchPartBePlaced && isSecondBenchPartInWorldBorder;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, @NotNull BlockState> builder) {
        builder.add(FACING, WATERLOGGED, PART);
    }

    @Override
    public void setPlacedBy(@NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState state, LivingEntity placer, @NotNull ItemStack itemStack) {
        super.setPlacedBy(level, pos, state, placer, itemStack);

        if (!level.isClientSide()) {
            BlockPos blockPos = pos.relative(getDirectionToOtherBenchHalf(state.getValue(FACING), BenchPart.LEFT));
            level.setBlock(blockPos, state.setValue(PART, BenchPart.RIGHT), Block.UPDATE_ALL);
            level.updateNeighborsAt(pos, Blocks.AIR);
            state.updateNeighbourShapes(level, pos, Block.UPDATE_ALL);
        }
    }

    @Override
    public @NotNull BlockState playerWillDestroy(Level level, @NotNull BlockPos pos, @NotNull BlockState state, @NotNull Player player) {
        if (!level.isClientSide() && player.isCreative()) {
            BenchPart benchPart = state.getValue(PART);

            if (benchPart.equals(BenchPart.RIGHT)) {
                breakOtherBenchPart(level, pos, state, player, benchPart);
            }
        }

        return super.playerWillDestroy(level, pos, state, player);
    }

    private void breakOtherBenchPart(Level level, BlockPos pos, BlockState state, Player player, BenchPart benchPart) {
        Direction directionOfOtherBenchHalf = getDirectionToOtherBenchHalf(state.getValue(FACING), benchPart);
        BlockPos positionOfOtherBenchHalf = pos.relative(directionOfOtherBenchHalf);
        BlockState stateOfOtherBenchHalf = level.getBlockState(positionOfOtherBenchHalf);

        if (stateOfOtherBenchHalf.is(this) && stateOfOtherBenchHalf.getValue(PART) == BenchPart.getOppositeBenchPart(benchPart)) {
            level.setBlock(positionOfOtherBenchHalf, Blocks.AIR.defaultBlockState(), Block.UPDATE_ALL | Block.UPDATE_SUPPRESS_DROPS);
            level.levelEvent(player, LevelEvent.PARTICLES_DESTROY_BLOCK, positionOfOtherBenchHalf, Block.getId(stateOfOtherBenchHalf));
        }
    }

    private Direction getDirectionToOtherBenchHalf(Direction direction, BenchPart benchPart) {
        Direction directionOfRightBenchHalf;
        boolean isBenchPartLeftHalf = benchPart.equals(BenchPart.LEFT);

        switch (direction) {
            case NORTH -> directionOfRightBenchHalf = WEST;
            case SOUTH -> directionOfRightBenchHalf = EAST;
            case WEST -> directionOfRightBenchHalf = SOUTH;
            default -> directionOfRightBenchHalf = NORTH;
        }

        return isBenchPartLeftHalf ? directionOfRightBenchHalf : directionOfRightBenchHalf.getOpposite();
    }
}
