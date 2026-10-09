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

public class PascalChairWoolBlock extends MultipleBlockChair implements SimpleWaterloggedBlock {


    private static final VoxelShape NORTH_SHAPE = Shapes.join(Stream.of(
            Block.box(12, 0, 3, 14, 1, 13),
            Block.box(12, 0, 13, 14, 6, 14),
            Block.box(2, 0, 2, 4, 6, 3),
            Block.box(2, 0, 3, 4, 1, 13),
            Block.box(2, 0, 13, 4, 6, 14),
            Block.box(12, 0, 2, 14, 6, 3)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get(), Stream.of(
            Block.box(2, 8, 13, 14, 20, 14),
            Block.box(1, 6, 1, 15, 7, 15),
            Block.box(1, 7, 14, 15, 21, 15),
            Block.box(2, 7, 2, 14, 8, 14)
            ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get(), BooleanOp.OR);
    private static final VoxelShape EAST_SHAPE = Shapes.join(Stream.of(
            Block.box(3, 0, 12, 13, 1, 14),
            Block.box(2, 0, 12, 3, 6, 14),
            Block.box(13, 0, 2, 14, 6, 4),
            Block.box(3, 0, 2, 13, 1, 4),
            Block.box(2, 0, 2, 3, 6, 4),
            Block.box(13, 0, 12, 14, 6, 14)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get(), Stream.of(
            Block.box(2, 8, 2, 3, 20, 14),
            Block.box(1, 6, 1, 15, 7, 15),
            Block.box(1, 7, 1, 2, 21, 15),
            Block.box(2, 7, 2, 14, 8, 14)
            ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get(), BooleanOp.OR);
    private static final VoxelShape SOUTH_SHAPE = Shapes.join(Stream.of(
            Block.box(2, 0, 3, 4, 1, 13),
            Block.box(2, 0, 2, 4, 6, 3),
            Block.box(12, 0, 13, 14, 6, 14),
            Block.box(12, 0, 3, 14, 1, 13),
            Block.box(12, 0, 2, 14, 6, 3),
            Block.box(2, 0, 13, 4, 6, 14)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get(), Stream.of(
            Block.box(2, 8, 2, 14, 20, 3),
            Block.box(1, 6, 1, 15, 7, 15),
            Block.box(1, 7, 1, 15, 21, 2),
            Block.box(2, 7, 2, 14, 8, 14)
            ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get(), BooleanOp.OR);
    private static final VoxelShape WEST_SHAPE = Shapes.join(Stream.of(
            Block.box(3, 0, 2, 13, 1, 4),
            Block.box(13, 0, 2, 14, 6, 4),
            Block.box(2, 0, 12, 3, 6, 14),
            Block.box(3, 0, 12, 13, 1, 14),
            Block.box(13, 0, 12, 14, 6, 14),
            Block.box(2, 0, 2, 3, 6, 4)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get(), Stream.of(
            Block.box(13, 8, 2, 14, 20, 14),
            Block.box(1, 6, 1, 15, 7, 15),
            Block.box(14, 7, 1, 15, 21, 15),
            Block.box(2, 7, 2, 14, 8, 14)
            ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get(), BooleanOp.OR);

    public PascalChairWoolBlock(Properties properties) {
        super(properties, 0.48f, NORTH_SHAPE, EAST_SHAPE, SOUTH_SHAPE, WEST_SHAPE);
    }

}
