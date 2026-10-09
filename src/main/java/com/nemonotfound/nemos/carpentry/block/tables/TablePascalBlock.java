package com.nemonotfound.nemos.carpentry.block.tables;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Stream;

public class TablePascalBlock extends RotatableTableBlock {


    private static final VoxelShape NORTH_SHAPE = Shapes.join(
            Block.box(0, 7, 0, 16, 8, 16), Stream.of(
            Block.box(14, 0, 13, 15, 7, 15),
            Block.box(2, 0, 13, 14, 1, 15),
            Block.box(1, 0, 13, 2, 7, 15),
            Block.box(14, 0, 1, 15, 7, 3),
            Block.box(2, 0, 1, 14, 1, 3),
            Block.box(1, 0, 1, 2, 7, 3)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get(), BooleanOp.OR);
    private static final VoxelShape EAST_SHAPE = Shapes.join(
            Block.box(0, 7, 0, 16, 8, 16), Stream.of(
            Block.box(1, 0, 14, 3, 7, 15),
            Block.box(1, 0, 2, 3, 1, 14),
            Block.box(1, 0, 1, 3, 7, 2),
            Block.box(13, 0, 14, 15, 7, 15),
            Block.box(13, 0, 2, 15, 1, 14),
            Block.box(13, 0, 1, 15, 7, 2)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get(), BooleanOp.OR);
    private static final VoxelShape SOUTH_SHAPE = Shapes.join(
            Block.box(0, 7, 0, 16, 8, 16), Stream.of(
            Block.box(1, 0, 1, 2, 7, 3),
            Block.box(2, 0, 1, 14, 1, 3),
            Block.box(14, 0, 1, 15, 7, 3),
            Block.box(1, 0, 13, 2, 7, 15),
            Block.box(2, 0, 13, 14, 1, 15),
            Block.box(14, 0, 13, 15, 7, 15)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get(), BooleanOp.OR);
    private static final VoxelShape WEST_SHAPE = Shapes.join(
            Block.box(0, 7, 0, 16, 8, 16), Stream.of(
            Block.box(13, 0, 1, 15, 7, 2),
            Block.box(13, 0, 2, 15, 1, 14),
            Block.box(13, 0, 14, 15, 7, 15),
            Block.box(1, 0, 1, 3, 7, 2),
            Block.box(1, 0, 2, 3, 1, 14),
            Block.box(1, 0, 14, 3, 7, 15)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get(), BooleanOp.OR);

    public TablePascalBlock(Properties properties) {
        super(properties, NORTH_SHAPE, EAST_SHAPE, SOUTH_SHAPE, WEST_SHAPE);
    }

}
