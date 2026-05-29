package rndm_access.assorteddiscoveries.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.SlabType;
import org.jspecify.annotations.NonNull;
import rndm_access.assorteddiscoveries.core.ModBlockTags;
import rndm_access.assorteddiscoveries.core.ModBlocks;

public class SnowySlabBlock extends SlabBlock {
    public static final MapCodec<SnowySlabBlock> CODEC = simpleCodec(SnowySlabBlock::new);
    public static final BooleanProperty SNOWY = BlockStateProperties.SNOWY;

    public SnowySlabBlock(Properties settings) {
        super(settings);
        this.registerDefaultState(this.getStateDefinition().any().setValue(SNOWY, false)
                .setValue(WATERLOGGED, false).setValue(TYPE, SlabType.BOTTOM));
    }

    @Override
    public @NonNull MapCodec<SnowySlabBlock> codec() {
        return CODEC;
    }

    @Override
    protected @NonNull BlockState updateShape(@NonNull BlockState state, @NonNull LevelReader world,
                                              @NonNull ScheduledTickAccess tickView, @NonNull BlockPos pos,
                                              @NonNull Direction direction, @NonNull BlockPos neighborPos,
                                              @NonNull BlockState neighborState, @NonNull RandomSource random) {
        return direction == Direction.UP ? state.setValue(SNOWY, isSnow(world, state, neighborPos, neighborState)) : state;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        Level world = ctx.getLevel();
        BlockPos neighborPos = ctx.getClickedPos().above();
        BlockState neighborState = world.getBlockState(neighborPos);
        BlockState state = super.getStateForPlacement(ctx);

        return state != null ? state.setValue(SNOWY, isSnow(world, state, neighborPos, neighborState)) : null;
    }

    public static boolean canGrowGrass(BlockState state, LevelReader world, BlockPos pos) {
        BlockPos neighborPos = pos.above();
        BlockState neighborState = world.getBlockState(neighborPos);

        if (isSnow(world, state, neighborPos, neighborState)) {
            return true;
        } else if (neighborState.getFluidState().getAmount() == 8 || state.getValue(WATERLOGGED)) {
            return false;
        } else {
            return !isCovered(world, neighborPos, neighborState) || isBottom(state) || !neighborState.canOcclude();
        }
    }

    public static boolean isSnow(LevelReader world, BlockState state, BlockPos neighborPos,
                                  BlockState neighborState) {
        return (neighborState.is(BlockTags.SNOW) && !isBottom(state)) ||
                (neighborState.is(ModBlockTags.SNOW_STAIRS) && !isBottom(state) &&
                        isCovered(world, neighborPos, neighborState)) ||
                (neighborState.is(ModBlockTags.SNOW_SLABS) && !isBottom(state) &&
                        isCovered(world, neighborPos, neighborState));
    }

    private static boolean isCovered(LevelReader world, BlockPos neighborPos, BlockState neighborState) {
        return neighborState.isFaceSturdy(world, neighborPos, Direction.DOWN);
    }

    protected static boolean isBottom(BlockState state) {
        return state.getValue(TYPE).equals(SlabType.BOTTOM);
    }

    @Override
    public void randomTick(@NonNull BlockState state, @NonNull ServerLevel world,
                           @NonNull BlockPos pos, @NonNull RandomSource random) {
        if(!canGrowGrass(state, world, pos)) {
            world.setBlockAndUpdate(pos, ModBlocks.DIRT_SLAB.defaultBlockState().setValue(TYPE, state.getValue(TYPE))
                    .setValue(WATERLOGGED, state.getValue(WATERLOGGED)));
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(TYPE, WATERLOGGED, SNOWY);
    }
}
