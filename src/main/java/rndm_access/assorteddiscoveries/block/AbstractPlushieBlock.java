package rndm_access.assorteddiscoveries.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

public abstract class AbstractPlushieBlock extends HorizontalDirectionalBlock implements SimpleWaterloggedBlock {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    protected AbstractPlushieBlock(BlockBehaviour.Properties settings) {
        super(settings);
    }

    @Override
    protected abstract MapCodec<? extends AbstractPlushieBlock> codec();

    @Override
    protected abstract void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder);

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos pos = context.getClickedPos();
        Level world = context.getLevel();
        FluidState fluidState = world.getFluidState(pos);

        for (Direction direction : context.getNearestLookingDirections()) {
            if (direction.getAxis().isHorizontal()) {
                return this.defaultBlockState().setValue(FACING, direction.getOpposite())
                        .setValue(WATERLOGGED, fluidState.is(Fluids.WATER));
            }
        }
        return null;
    }

    @Override
    public BlockState updateShape(BlockState state, LevelReader world, ScheduledTickAccess tickView,
                                                BlockPos pos, Direction direction, BlockPos neighborPos,
                                                BlockState neighborState, RandomSource random) {
        if (state.getValue(WATERLOGGED)) {
            tickView.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
        }
        return direction.getAxis().isHorizontal() ? state.setValue(FACING, state.getValue(FACING)) : state;
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : Fluids.EMPTY.defaultFluidState();
    }
}
