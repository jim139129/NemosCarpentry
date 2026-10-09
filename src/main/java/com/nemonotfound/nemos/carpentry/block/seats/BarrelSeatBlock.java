package com.nemonotfound.nemos.carpentry.block.seats;


import com.nemonotfound.nemos.carpentry.block.seats.parents.SitableBlock;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Stream;

public class BarrelSeatBlock extends SitableBlock implements SimpleWaterloggedBlock {


    private static final VoxelShape NORTH_SHAPE = Shapes.join(Stream.of(
            Stream.of(
                    Block.box(4, 10, 14, 12, 12, 14.5),
                    Block.box(12, 10, 12, 12.5, 12, 14),
                    Block.box(3.5, 10, 12, 4, 12, 14),
                    Block.box(12.5, 10, 12, 14, 12, 12.5),
                    Block.box(1.5, 10, 12, 3.5, 12, 12.5),
                    Block.box(2, 10, 3.5, 3.5, 12, 4),
                    Block.box(3.5, 10, 2, 4, 12, 4),
                    Block.box(4, 10, 1.5, 12, 12, 2),
                    Block.box(12, 10, 2, 12.5, 12, 4),
                    Block.box(12.5, 10, 3.5, 14.5, 12, 4),
                    Block.box(14, 10, 4, 14.5, 12, 12),
                    Block.box(1.5, 10, 4, 2, 12, 12)
            ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get(),
            Stream.of(
                    Block.box(4, 6, 14, 12, 8, 14.5),
                    Block.box(12, 6, 12, 12.5, 8, 14),
                    Block.box(3.5, 6, 12, 4, 8, 14),
                    Block.box(12.5, 6, 12, 14, 8, 12.5),
                    Block.box(1.5, 6, 12, 3.5, 8, 12.5),
                    Block.box(2, 6, 3.5, 3.5, 8, 4),
                    Block.box(3.5, 6, 2, 4, 8, 4),
                    Block.box(4, 6, 1.5, 12, 8, 2),
                    Block.box(12, 6, 2, 12.5, 8, 4),
                    Block.box(12.5, 6, 3.5, 14.5, 8, 4),
                    Block.box(14, 6, 4, 14.5, 8, 12),
                    Block.box(1.5, 6, 4, 2, 8, 12)
            ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get(),
            Stream.of(
                    Block.box(4, 2, 14, 12, 4, 14.5),
                    Block.box(12, 2, 12, 12.5, 4, 14),
                    Block.box(3.5, 2, 12, 4, 4, 14),
                    Block.box(12.5, 2, 12, 14, 4, 12.5),
                    Block.box(1.5, 2, 12, 3.5, 4, 12.5),
                    Block.box(2, 2, 3.5, 3.5, 4, 4),
                    Block.box(3.5, 2, 2, 4, 4, 4),
                    Block.box(4, 2, 1.5, 12, 4, 2),
                    Block.box(12, 2, 2, 12.5, 4, 4),
                    Block.box(12.5, 2, 3.5, 14.5, 4, 4),
                    Block.box(14, 2, 4, 14.5, 4, 12),
                    Block.box(1.5, 2, 4, 2, 4, 12)
            ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get(),
            Stream.of(
                    Block.box(3, 13, 4, 4, 14, 12),
                    Block.box(12, 13, 4, 13, 14, 12),
                    Block.box(4, 13, 3, 12, 14, 4),
                    Block.box(4, 13, 12, 12, 14, 13)
            ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get(),
            Stream.of(
                    Block.box(12, 0, 4, 13, 1, 12),
                    Block.box(4, 0, 3, 12, 1, 4),
                    Block.box(4, 0, 12, 12, 1, 13),
                    Block.box(3, 0, 4, 4, 1, 12)
            ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get()
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get(), Stream.of(
            Block.box(4, 1, 2, 12, 13, 4),
            Block.box(12, 1, 4, 14, 13, 12),
            Block.box(2, 1, 4, 4, 13, 12),
            Block.box(4, 1, 12, 12, 13, 14),
            Block.box(4, 0, 4, 12, 13, 12)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get(), BooleanOp.OR);
    private static final VoxelShape EAST_SHAPE = Shapes.join(Stream.of(
            Stream.of(
                    Block.box(1.5, 10, 4, 2, 12, 12),
                    Block.box(2, 10, 12, 4, 12, 12.5),
                    Block.box(2, 10, 3.5, 4, 12, 4),
                    Block.box(3.5, 10, 12.5, 4, 12, 14),
                    Block.box(3.5, 10, 1.5, 4, 12, 3.5),
                    Block.box(12, 10, 2, 12.5, 12, 3.5),
                    Block.box(12, 10, 3.5, 14, 12, 4),
                    Block.box(14, 10, 4, 14.5, 12, 12),
                    Block.box(12, 10, 12, 14, 12, 12.5),
                    Block.box(12, 10, 12.5, 12.5, 12, 14.5),
                    Block.box(4, 10, 14, 12, 12, 14.5),
                    Block.box(4, 10, 1.5, 12, 12, 2)
            ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get(),
            Stream.of(
            Block.box(1.5, 6, 4, 2, 8, 12),
            Block.box(2, 6, 12, 4, 8, 12.5),
            Block.box(2, 6, 3.5, 4, 8, 4),
            Block.box(3.5, 6, 12.5, 4, 8, 14),
            Block.box(3.5, 6, 1.5, 4, 8, 3.5),
            Block.box(12, 6, 2, 12.5, 8, 3.5),
            Block.box(12, 6, 3.5, 14, 8, 4),
            Block.box(14, 6, 4, 14.5, 8, 12),
            Block.box(12, 6, 12, 14, 8, 12.5),
            Block.box(12, 6, 12.5, 12.5, 8, 14.5),
            Block.box(4, 6, 14, 12, 8, 14.5),
            Block.box(4, 6, 1.5, 12, 8, 2)
            ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get(),
            Stream.of(
            Block.box(1.5, 2, 4, 2, 4, 12),
            Block.box(2, 2, 12, 4, 4, 12.5),
            Block.box(2, 2, 3.5, 4, 4, 4),
            Block.box(3.5, 2, 12.5, 4, 4, 14),
            Block.box(3.5, 2, 1.5, 4, 4, 3.5),
            Block.box(12, 2, 2, 12.5, 4, 3.5),
            Block.box(12, 2, 3.5, 14, 4, 4),
            Block.box(14, 2, 4, 14.5, 4, 12),
            Block.box(12, 2, 12, 14, 4, 12.5),
            Block.box(12, 2, 12.5, 12.5, 4, 14.5),
            Block.box(4, 2, 14, 12, 4, 14.5),
            Block.box(4, 2, 1.5, 12, 4, 2)
            ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get(),
            Stream.of(
            Block.box(4, 13, 3, 12, 14, 4),
            Block.box(4, 13, 12, 12, 14, 13),
            Block.box(12, 13, 4, 13, 14, 12),
            Block.box(3, 13, 4, 4, 14, 12)
            ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get(),
            Stream.of(
            Block.box(4, 0, 12, 12, 1, 13),
            Block.box(12, 0, 4, 13, 1, 12),
            Block.box(3, 0, 4, 4, 1, 12),
            Block.box(4, 0, 3, 12, 1, 4)
            ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get())
            .reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get(), Stream.of(
            Block.box(12, 1, 4, 14, 13, 12),
            Block.box(4, 1, 12, 12, 13, 14),
            Block.box(4, 1, 2, 12, 13, 4),
            Block.box(2, 1, 4, 4, 13, 12),
            Block.box(4, 0, 4, 12, 13, 12)
            ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get(), BooleanOp.OR);
    private static final VoxelShape SOUTH_SHAPE = Shapes.join(Stream.of(
            Stream.of(
                    Block.box(4, 10, 1.5, 12, 12, 2),
                    Block.box(3.5, 10, 2, 4, 12, 4),
                    Block.box(12, 10, 2, 12.5, 12, 4),
                    Block.box(2, 10, 3.5, 3.5, 12, 4),
                    Block.box(12.5, 10, 3.5, 14.5, 12, 4),
                    Block.box(12.5, 10, 12, 14, 12, 12.5),
                    Block.box(12, 10, 12, 12.5, 12, 14),
                    Block.box(4, 10, 14, 12, 12, 14.5),
                    Block.box(3.5, 10, 12, 4, 12, 14),
                    Block.box(1.5, 10, 12, 3.5, 12, 12.5),
                    Block.box(1.5, 10, 4, 2, 12, 12),
                    Block.box(14, 10, 4, 14.5, 12, 12)
            ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get(),
            Stream.of(
            Block.box(4, 6, 1.5, 12, 8, 2),
            Block.box(3.5, 6, 2, 4, 8, 4),
            Block.box(12, 6, 2, 12.5, 8, 4),
            Block.box(2, 6, 3.5, 3.5, 8, 4),
            Block.box(12.5, 6, 3.5, 14.5, 8, 4),
            Block.box(12.5, 6, 12, 14, 8, 12.5),
            Block.box(12, 6, 12, 12.5, 8, 14),
            Block.box(4, 6, 14, 12, 8, 14.5),
            Block.box(3.5, 6, 12, 4, 8, 14),
            Block.box(1.5, 6, 12, 3.5, 8, 12.5),
            Block.box(1.5, 6, 4, 2, 8, 12),
            Block.box(14, 6, 4, 14.5, 8, 12)
            ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get(),
            Stream.of(
            Block.box(4, 2, 1.5, 12, 4, 2),
            Block.box(3.5, 2, 2, 4, 4, 4),
            Block.box(12, 2, 2, 12.5, 4, 4),
            Block.box(2, 2, 3.5, 3.5, 4, 4),
            Block.box(12.5, 2, 3.5, 14.5, 4, 4),
            Block.box(12.5, 2, 12, 14, 4, 12.5),
            Block.box(12, 2, 12, 12.5, 4, 14),
            Block.box(4, 2, 14, 12, 4, 14.5),
            Block.box(3.5, 2, 12, 4, 4, 14),
            Block.box(1.5, 2, 12, 3.5, 4, 12.5),
            Block.box(1.5, 2, 4, 2, 4, 12),
            Block.box(14, 2, 4, 14.5, 4, 12)
            ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get(),
            Stream.of(
            Block.box(12, 13, 4, 13, 14, 12),
            Block.box(3, 13, 4, 4, 14, 12),
            Block.box(4, 13, 12, 12, 14, 13),
            Block.box(4, 13, 3, 12, 14, 4)
            ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get(),
            Stream.of(
            Block.box(3, 0, 4, 4, 1, 12),
            Block.box(4, 0, 12, 12, 1, 13),
            Block.box(4, 0, 3, 12, 1, 4),
            Block.box(12, 0, 4, 13, 1, 12)
            ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get())
            .reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get(), Stream.of(
            Block.box(4, 1, 12, 12, 13, 14),
            Block.box(2, 1, 4, 4, 13, 12),
            Block.box(12, 1, 4, 14, 13, 12),
            Block.box(4, 1, 2, 12, 13, 4),
            Block.box(4, 0, 4, 12, 13, 12)
            ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get(), BooleanOp.OR);
    private static final VoxelShape WEST_SHAPE = Shapes.join(Stream.of(
            Stream.of(
                    Block.box(14, 10, 4, 14.5, 12, 12),
                    Block.box(12, 10, 3.5, 14, 12, 4),
                    Block.box(12, 10, 12, 14, 12, 12.5),
                    Block.box(12, 10, 2, 12.5, 12, 3.5),
                    Block.box(12, 10, 12.5, 12.5, 12, 14.5),
                    Block.box(3.5, 10, 12.5, 4, 12, 14),
                    Block.box(2, 10, 12, 4, 12, 12.5),
                    Block.box(1.5, 10, 4, 2, 12, 12),
                    Block.box(2, 10, 3.5, 4, 12, 4),
                    Block.box(3.5, 10, 1.5, 4, 12, 3.5),
                    Block.box(4, 10, 1.5, 12, 12, 2),
                    Block.box(4, 10, 14, 12, 12, 14.5)
            ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get(),
            Stream.of(
            Block.box(14, 6, 4, 14.5, 8, 12),
            Block.box(12, 6, 3.5, 14, 8, 4),
            Block.box(12, 6, 12, 14, 8, 12.5),
            Block.box(12, 6, 2, 12.5, 8, 3.5),
            Block.box(12, 6, 12.5, 12.5, 8, 14.5),
            Block.box(3.5, 6, 12.5, 4, 8, 14),
            Block.box(2, 6, 12, 4, 8, 12.5),
            Block.box(1.5, 6, 4, 2, 8, 12),
            Block.box(2, 6, 3.5, 4, 8, 4),
            Block.box(3.5, 6, 1.5, 4, 8, 3.5),
            Block.box(4, 6, 1.5, 12, 8, 2),
            Block.box(4, 6, 14, 12, 8, 14.5)
            ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get(),
            Stream.of(
            Block.box(14, 2, 4, 14.5, 4, 12),
            Block.box(12, 2, 3.5, 14, 4, 4),
            Block.box(12, 2, 12, 14, 4, 12.5),
            Block.box(12, 2, 2, 12.5, 4, 3.5),
            Block.box(12, 2, 12.5, 12.5, 4, 14.5),
            Block.box(3.5, 2, 12.5, 4, 4, 14),
            Block.box(2, 2, 12, 4, 4, 12.5),
            Block.box(1.5, 2, 4, 2, 4, 12),
            Block.box(2, 2, 3.5, 4, 4, 4),
            Block.box(3.5, 2, 1.5, 4, 4, 3.5),
            Block.box(4, 2, 1.5, 12, 4, 2),
            Block.box(4, 2, 14, 12, 4, 14.5)
            ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get(),
            Stream.of(
            Block.box(4, 13, 12, 12, 14, 13),
            Block.box(4, 13, 3, 12, 14, 4),
            Block.box(3, 13, 4, 4, 14, 12),
            Block.box(12, 13, 4, 13, 14, 12)
            ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get(),
            Stream.of(
            Block.box(4, 0, 3, 12, 1, 4),
            Block.box(3, 0, 4, 4, 1, 12),
            Block.box(12, 0, 4, 13, 1, 12),
            Block.box(4, 0, 12, 12, 1, 13)
            ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get())
            .reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get(), Stream.of(
            Block.box(2, 1, 4, 4, 13, 12),
            Block.box(4, 1, 2, 12, 13, 4),
            Block.box(4, 1, 12, 12, 13, 14),
            Block.box(12, 1, 4, 14, 13, 12),
            Block.box(4, 0, 4, 12, 13, 12)
            ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get(), BooleanOp.OR);

    public BarrelSeatBlock(Properties properties) {
        super(properties, 0.8f, NORTH_SHAPE, EAST_SHAPE, SOUTH_SHAPE, WEST_SHAPE);
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(FACING, Direction.NORTH)
                .setValue(WATERLOGGED, false));
    }

}
