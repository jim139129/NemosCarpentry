package com.nemonotfound.nemos.carpentry.block.seats;

import com.nemonotfound.nemos.carpentry.block.seats.parents.MultipleBlockChair;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Stream;

public class GregoryChairBlock extends MultipleBlockChair implements SimpleWaterloggedBlock {


    private static final VoxelShape NORTH_SHAPE = Shapes.join(Stream.of(
            Block.box(1, 7, 1, 15, 9, 15),
            Block.box(13, 9, 13, 15, 17, 15),
            Block.box(1, 9, 13, 3, 17, 15),
            Block.box(1, 17, 13, 15, 23, 15),
            Block.box(3, 12, 13.5, 13, 15, 14.5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get(), Stream.of(
            Block.box(2, 0, 2, 4, 7, 4),
            Block.box(2, 0, 12, 4, 7, 14),
            Block.box(12, 0, 12, 14, 7, 14),
            Block.box(12, 0, 2, 14, 7, 4)
            ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get(), BooleanOp.OR);
    private static final VoxelShape EAST_SHAPE = Shapes.join(Stream.of(
            Block.box(1, 7, 1, 15, 9, 15),
            Block.box(1, 9, 13, 3, 17, 15),
            Block.box(1, 9, 1, 3, 17, 3),
            Block.box(1, 17, 1, 3, 23, 15),
            Block.box(1.5, 12, 3, 2.5, 15, 13)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get(), Stream.of(
            Block.box(12, 0, 2, 14, 7, 4),
            Block.box(2, 0, 2, 4, 7, 4),
            Block.box(2, 0, 12, 4, 7, 14),
            Block.box(12, 0, 12, 14, 7, 14)
            ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get(), BooleanOp.OR);
    private static final VoxelShape SOUTH_SHAPE = Shapes.join(Stream.of(
            Block.box(1, 7, 1, 15, 9, 15),
            Block.box(1, 9, 1, 3, 17, 3),
            Block.box(13, 9, 1, 15, 17, 3),
            Block.box(1, 17, 1, 15, 23, 3),
            Block.box(3, 12, 1.5, 13, 15, 2.5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get(), Stream.of(
            Block.box(12, 0, 12, 14, 7, 14),
            Block.box(12, 0, 2, 14, 7, 4),
            Block.box(2, 0, 2, 4, 7, 4),
            Block.box(2, 0, 12, 4, 7, 14)
            ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get(), BooleanOp.OR);
    private static final VoxelShape WEST_SHAPE = Shapes.join(Stream.of(
            Block.box(1, 7, 1, 15, 9, 15),
            Block.box(13, 9, 1, 15, 17, 3),
            Block.box(13, 9, 13, 15, 17, 15),
            Block.box(13, 17, 1, 15, 23, 15),
            Block.box(13.5, 12, 3, 14.5, 15, 13)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get(), Stream.of(
            Block.box(2, 0, 12, 4, 7, 14),
            Block.box(12, 0, 12, 14, 7, 14),
            Block.box(12, 0, 2, 14, 7, 4),
            Block.box(2, 0, 2, 4, 7, 4)
            ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get(), BooleanOp.OR);

    public GregoryChairBlock(Properties properties) {
        super(properties, 0.58f, NORTH_SHAPE, EAST_SHAPE, SOUTH_SHAPE, WEST_SHAPE);
    }

}
