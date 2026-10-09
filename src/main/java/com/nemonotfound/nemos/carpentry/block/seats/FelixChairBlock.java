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

public class FelixChairBlock extends SitableBlock implements SimpleWaterloggedBlock {

    
    private static final VoxelShape NORTH_SHAPE = Stream.of(
            Block.box(13, 7, 13, 15, 16, 15),
            Block.box(1, 7, 13, 3, 16, 15),
            Block.box(1, 5, 1, 15, 7, 15),
            Block.box(12, 0, 12, 14, 5, 14),
            Block.box(2, 0, 12, 4, 5, 14),
            Block.box(2, 0, 2, 4, 5, 4),
            Block.box(12, 0, 2, 14, 5, 4),
            Block.box(4, 2, 2.5, 12, 4, 3.5),
            Block.box(4, 2, 12.5, 12, 4, 13.5),
            Block.box(3, 13, 13.5, 13, 15, 14.5),
            Block.box(3, 9, 13.5, 13, 11, 14.5),
            Block.box(2.5, 2, 4, 3.5, 4, 12),
            Block.box(12.5, 2, 4, 13.5, 4, 12)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape EAST_SHAPE = Stream.of(
            Block.box(1, 7, 13, 3, 16, 15),
            Block.box(1, 7, 1, 3, 16, 3),
            Block.box(1, 5, 1, 15, 7, 15),
            Block.box(2, 0, 12, 4, 5, 14),
            Block.box(2, 0, 2, 4, 5, 4),
            Block.box(12, 0, 2, 14, 5, 4),
            Block.box(12, 0, 12, 14, 5, 14),
            Block.box(12.5, 2, 4, 13.5, 4, 12),
            Block.box(2.5, 2, 4, 3.5, 4, 12),
            Block.box(1.5, 13, 3, 2.5, 15, 13),
            Block.box(1.5, 9, 3, 2.5, 11, 13),
            Block.box(4, 2, 2.5, 12, 4, 3.5),
            Block.box(4, 2, 12.5, 12, 4, 13.5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape SOUTH_SHAPE = Stream.of(
            Block.box(1, 7, 1, 3, 16, 3),
            Block.box(13, 7, 1, 15, 16, 3),
            Block.box(1, 5, 1, 15, 7, 15),
            Block.box(2, 0, 2, 4, 5, 4),
            Block.box(12, 0, 2, 14, 5, 4),
            Block.box(12, 0, 12, 14, 5, 14),
            Block.box(2, 0, 12, 4, 5, 14),
            Block.box(4, 2, 12.5, 12, 4, 13.5),
            Block.box(4, 2, 2.5, 12, 4, 3.5),
            Block.box(3, 13, 1.5, 13, 15, 2.5),
            Block.box(3, 9, 1.5, 13, 11, 2.5),
            Block.box(12.5, 2, 4, 13.5, 4, 12),
            Block.box(2.5, 2, 4, 3.5, 4, 12)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape WEST_SHAPE = Stream.of(
            Block.box(13, 7, 1, 15, 16, 3),
            Block.box(13, 7, 13, 15, 16, 15),
            Block.box(1, 5, 1, 15, 7, 15),
            Block.box(12, 0, 2, 14, 5, 4),
            Block.box(12, 0, 12, 14, 5, 14),
            Block.box(2, 0, 12, 4, 5, 14),
            Block.box(2, 0, 2, 4, 5, 4),
            Block.box(2.5, 2, 4, 3.5, 4, 12),
            Block.box(12.5, 2, 4, 13.5, 4, 12),
            Block.box(13.5, 13, 3, 14.5, 15, 13),
            Block.box(13.5, 9, 3, 14.5, 11, 13),
            Block.box(4, 2, 12.5, 12, 4, 13.5),
            Block.box(4, 2, 2.5, 12, 4, 3.5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public FelixChairBlock(Properties properties) {
        super(properties, 0.4f, NORTH_SHAPE, EAST_SHAPE, SOUTH_SHAPE, WEST_SHAPE);
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(FACING, Direction.NORTH)
                .setValue(WATERLOGGED, false));
    }

}
