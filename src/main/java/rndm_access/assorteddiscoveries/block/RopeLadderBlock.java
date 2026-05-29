package rndm_access.assorteddiscoveries.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LadderBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import rndm_access.assorteddiscoveries.block.state.ModBlockStateProperties;

public class RopeLadderBlock extends LadderBlock {
    public static final IntegerProperty LENGTH = ModBlockStateProperties.LENGTH;
    public static final BooleanProperty DOWN = BlockStateProperties.DOWN;

    public RopeLadderBlock(BlockBehaviour.Properties settings) {
        super(settings);
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH)
                .setValue(WATERLOGGED, false).setValue(LENGTH, 0).setValue(DOWN, false));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Level world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        FluidState fluidState = world.getFluidState(pos);
        BlockState placedState = this.defaultBlockState().setValue(WATERLOGGED, this.isWaterSource(fluidState))
                .setValue(DOWN, this.isEnd(world, pos));

        if (this.hasSupport(world, pos)) {
            return this.placeHangingLadder(world, pos, placedState);
        } else {
            return this.placeLadder(context, placedState);
        }
    }

    private BlockState placeHangingLadder(Level world, BlockPos pos, BlockState placedState) {
        BlockState stateAboveLadder = world.getBlockState(pos.above());
        Direction facing = stateAboveLadder.getValue(FACING);
        int length = this.getNextLength(world, pos);

        if (length <= this.getMaxLength()) {
            if (!this.hasSupportingBlock(world, facing, pos)) {
                return placedState.setValue(LENGTH, length).setValue(FACING, facing);
            }
            return placedState.setValue(FACING, facing);
        }
        return null;
    }

    private BlockState placeLadder(BlockPlaceContext context, BlockState placedState) {
        for (Direction direction : context.getNearestLookingDirections()) {
            if (direction.getAxis().isHorizontal()) {
                return placedState.setValue(FACING, direction.getOpposite());
            }
        }
        return null;
    }

    @Override
    public BlockState updateShape(BlockState state, LevelReader world, ScheduledTickAccess tickView,
                                                BlockPos pos, Direction direction, BlockPos neighborPos,
                                                BlockState neighborState, RandomSource random) {
        Direction facing = state.getValue(FACING);
        BlockState stateAbove = world.getBlockState(pos.above());

        if (canSurvive(state, world, pos)) {
            if (state.getValue(WATERLOGGED)) {
                tickView.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
            }

            // Set the ladder's length to 0 when a block is placed behind it.
            if (this.hasSupportingBlock(world, facing, pos)) {
                return state.setValue(LENGTH, 0).setValue(DOWN, this.isEnd(world, pos));
            }

            // Update each ladders length after the new support block to keep each ladder's length consistent.
            if (this.isRopeLadder(stateAbove)) {
                return state.setValue(LENGTH, this.getNextLength(world, pos)).setValue(DOWN, this.isEnd(world, pos));
            }
        }
        return Blocks.AIR.defaultBlockState();
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        Direction facing = state.getValue(FACING);
        BlockState stateAboveLadder = world.getBlockState(pos.above());

        if (this.isRopeLadder(stateAboveLadder)) {
            int length = this.getNextLength(world, pos);
            return length <= this.getMaxLength();
        }
        return this.hasSupportingBlock(world, facing, pos);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LENGTH, DOWN, FACING, WATERLOGGED);
    }

    private boolean hasSupportingBlock(LevelReader world, Direction facing, BlockPos pos) {
        BlockPos posBehindLadder = pos.relative(facing.getOpposite());
        BlockState stateBehindLadder = world.getBlockState(posBehindLadder);

        return stateBehindLadder.isFaceSturdy(world, posBehindLadder, facing);
    }

    private boolean isEnd(LevelReader world, BlockPos pos) {
        BlockState stateBelowLadder = world.getBlockState(pos.below());

        return this.isRopeLadder(stateBelowLadder);
    }

    private int getMaxLength() {
        return 16;
    }

    private int getNextLength(LevelReader world, BlockPos pos) {
        BlockState stateAboveLadder = world.getBlockState(pos.above());

        return stateAboveLadder.getValue(LENGTH) + 1;
    }

    private boolean isRopeLadder(BlockState state) {
        return state.is(this);
    }

    private boolean hasSupport(Level world, BlockPos pos) {
        return this.isRopeLadder(world.getBlockState(pos.above()));
    }

    private boolean isWaterSource(FluidState fluidState) {
        return fluidState.is(FluidTags.WATER) && fluidState.isSource();
    }
}
