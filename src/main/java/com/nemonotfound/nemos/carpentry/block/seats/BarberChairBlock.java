package com.nemonotfound.nemos.carpentry.block.seats;

import com.nemonotfound.nemos.carpentry.block.seats.parents.SitableBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Stream;

public class BarberChairBlock extends SitableBlock implements SimpleWaterloggedBlock {


    private static final VoxelShape NORTH_SHAPE = Shapes.join(Stream.of(
            Block.box(4, 0, 5, 5, 1, 11),
            Block.box(5, 8, 5, 11, 9, 11),
            Block.box(7, 2, 7, 9, 8, 9),
            Block.box(5, 0, 5, 11, 2, 11),
            Block.box(5, 0, 4, 11, 1, 5),
            Block.box(11, 0, 5, 12, 1, 11),
            Block.box(5, 0, 11, 11, 1, 12)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get(),
            Shapes.join(Block.box(3, 9, 3, 13, 11, 13),
                    Block.box(3, 11, 11, 13, 16, 13), BooleanOp.OR), BooleanOp.OR);
    private static final VoxelShape EAST_SHAPE = Shapes.join(Stream.of(
            Block.box(5, 0, 4, 11, 1, 5),
            Block.box(5, 8, 5, 11, 9, 11),
            Block.box(7, 2, 7, 9, 8, 9),
            Block.box(5, 0, 5, 11, 2, 11),
            Block.box(11, 0, 5, 12, 1, 11),
            Block.box(5, 0, 11, 11, 1, 12),
            Block.box(4, 0, 5, 5, 1, 11)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get(),
            Shapes.join(Block.box(3, 9, 3, 13, 11, 13),
                    Block.box(3, 11, 3, 5, 16, 13), BooleanOp.OR), BooleanOp.OR);
    private static final VoxelShape SOUTH_SHAPE = Shapes.join(Stream.of(
            Block.box(11, 0, 5, 12, 1, 11),
            Block.box(5, 8, 5, 11, 9, 11),
            Block.box(7, 2, 7, 9, 8, 9),
            Block.box(5, 0, 5, 11, 2, 11),
            Block.box(5, 0, 11, 11, 1, 12),
            Block.box(4, 0, 5, 5, 1, 11),
            Block.box(5, 0, 4, 11, 1, 5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get(),
            Shapes.join(Block.box(3, 9, 3, 13, 11, 13),
                    Block.box(3, 11, 3, 13, 16, 5), BooleanOp.OR), BooleanOp.OR);
    private static final VoxelShape WEST_SHAPE = Shapes.join(Stream.of(
            Block.box(5, 0, 11, 11, 1, 12),
            Block.box(5, 8, 5, 11, 9, 11),
            Block.box(7, 2, 7, 9, 8, 9),
            Block.box(5, 0, 5, 11, 2, 11),
            Block.box(4, 0, 5, 5, 1, 11),
            Block.box(5, 0, 4, 11, 1, 5),
            Block.box(11, 0, 5, 12, 1, 11)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get(),
            Shapes.join(Block.box(3, 9, 3, 13, 11, 13),
                    Block.box(11, 11, 3, 13, 16, 13), BooleanOp.OR), BooleanOp.OR);

    public BarberChairBlock(Properties properties) {
        super(properties, 0.7f, NORTH_SHAPE, EAST_SHAPE, SOUTH_SHAPE, WEST_SHAPE);
    }

}
