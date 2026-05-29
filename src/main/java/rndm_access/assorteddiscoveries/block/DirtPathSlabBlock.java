package rndm_access.assorteddiscoveries.block;

import com.mojang.serialization.MapCodec;
import rndm_access.assorteddiscoveries.core.ModBlocks;

import java.util.HashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class DirtPathSlabBlock extends SlabBlock {
    public static final MapCodec<DirtPathSlabBlock> CODEC = simpleCodec(DirtPathSlabBlock::new);
    protected static final HashMap<SlabType, VoxelShape> SHAPE;

    public DirtPathSlabBlock(Properties settings) {
        super(settings);
    }

    @Override
    public MapCodec<DirtPathSlabBlock> codec() {
        return CODEC;
    }

    @Override
    public BlockState updateShape(BlockState state, LevelReader world, ScheduledTickAccess tickView,
                                                BlockPos pos, Direction direction, BlockPos neighborPos,
                                                BlockState neighborState, RandomSource random) {
        if (state.getValue(WATERLOGGED)) {
            tickView.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
        }

        if(direction.equals(Direction.UP) && !state.getValue(TYPE).equals(SlabType.BOTTOM)) {
            return ModBlocks.DIRT_SLAB.defaultBlockState().setValue(TYPE, state.getValue(TYPE))
                    .setValue(WATERLOGGED, state.getValue(WATERLOGGED));
        } else {
            return state;
        }
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        SlabType slabType = state.getValue(TYPE);
        return SHAPE.get(slabType);
    }

    static {
        SHAPE = new HashMap<>();
        SHAPE.put(SlabType.DOUBLE, Block.box(0.0, 0.0, 0.0, 16.0, 15.0,
                16.0));
        SHAPE.put(SlabType.BOTTOM, Block.box(0.0, 0.0, 0.0, 16.0, 7.0,
                16.0));
        SHAPE.put(SlabType.TOP, Block.box(0.0, 8.0, 0.0, 16.0, 15.0,
                16.0));
    }
}
