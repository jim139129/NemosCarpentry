package com.nemonotfound.nemos.carpentry.block.seats;


import com.nemonotfound.nemos.carpentry.block.seats.parents.SitableBlock;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class ChairBlock extends SitableBlock implements SimpleWaterloggedBlock {

    
    private static final VoxelShape SOUTH_SHAPE = Shapes.or(Block.box(1, 6, 1, 15, 8, 15),
            Block.box(1, 8, 1, 15, 16, 3),
            Block.box(12, 0, 12, 14, 6, 14),
            Block.box(2, 0, 12, 4, 6, 14),
            Block.box(2, 0, 2, 4, 6, 4),
            Block.box(12, 0, 2, 14, 6, 4));
    private static final VoxelShape WEST_SHAPE = Shapes.or(Block.box(1, 6, 1, 15, 8, 15),
            Block.box(13, 8, 1, 15, 16, 15),
            Block.box(2, 0, 12, 4, 6, 14),
            Block.box(2, 0, 2, 4, 6, 4),
            Block.box(12, 0, 2, 14, 6, 4),
            Block.box(12, 0, 12, 14, 6, 14));
    private static final VoxelShape NORTH_SHAPE = Shapes.or(Block.box(1, 6, 1, 15, 8, 15),
            Block.box(1, 8, 13, 15, 16, 15),
            Block.box(2, 0, 2, 4, 6, 4),
            Block.box(12, 0, 2, 14, 6, 4),
            Block.box(12, 0, 12, 14, 6, 14),
            Block.box(2, 0, 12, 4, 6, 14));
    private static final VoxelShape EAST_SHAPE = Shapes.or(Block.box(1, 6, 1, 15, 8, 15),
            Block.box(1, 8, 1, 3, 16, 15),
            Block.box(12, 0, 2, 14, 6, 4),
            Block.box(12, 0, 12, 14, 6, 14),
            Block.box(2, 0, 12, 4, 6, 14),
            Block.box(2, 0, 2, 4, 6, 4));

    public ChairBlock(Properties properties) {
        super(properties, 0.5f, NORTH_SHAPE, EAST_SHAPE, SOUTH_SHAPE, WEST_SHAPE);
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(FACING, Direction.NORTH)
                .setValue(WATERLOGGED, false));
    }

}
