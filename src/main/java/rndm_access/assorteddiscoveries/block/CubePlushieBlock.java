package rndm_access.assorteddiscoveries.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;
import rndm_access.assorteddiscoveries.block.state.ModBlockStateProperties;
import rndm_access.assorteddiscoveries.util.ShapeHelper;

import java.util.HashMap;
import java.util.Objects;

public class CubePlushieBlock extends AbstractPlushieBlock {
    public static final MapCodec<CubePlushieBlock> CODEC = simpleCodec(CubePlushieBlock::new);
    public static final IntegerProperty STACK_SIZE = ModBlockStateProperties.STACK_SIZE;
    public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
    private static final VoxelShape NORTH_BOTTOM_SHAPE = Block.box(2.5D, 0.0D, 2.5D,
            13.5D, 9.5D, 13.5D);
    private static final VoxelShape NORTH_MIDDLE_SHAPE = Block.box(3.5D, 7.0D, 3.5D,
            12.5D, 16.5D, 12.5D);
    private static final VoxelShape NORTH_TOP_SHAPE = Block.box(5.5D, 0.0D, 5.5D,
            10.5D, 4.5D, 10.5D);
    private static final HashMap<Direction, VoxelShape> BOTTOM_SHAPES
            = ShapeHelper.makeShapeRotMap(NORTH_BOTTOM_SHAPE);
    private static final HashMap<Direction, VoxelShape> MIDDLE_SHAPES
            = ShapeHelper.makeShapeRotMap(NORTH_BOTTOM_SHAPE, NORTH_MIDDLE_SHAPE);
    private static final HashMap<Direction, VoxelShape> TOP_SHAPES = ShapeHelper.makeShapeRotMap(NORTH_TOP_SHAPE);

    public CubePlushieBlock(BlockBehaviour.Properties settings) {
        super(settings);
        this.registerDefaultState(this.getStateDefinition().any().setValue(HALF, DoubleBlockHalf.LOWER)
                .setValue(STACK_SIZE, 1).setValue(WATERLOGGED, false).setValue(FACING, Direction.NORTH));
    }

    @Override
    protected MapCodec<CubePlushieBlock> codec() {
        return CODEC;
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos pos = context.getClickedPos();
        Level world = context.getLevel();
        BlockState state = world.getBlockState(pos);

        if (this.isCubePlush(state)) {
            return state.setValue(STACK_SIZE, this.getNextStackSize(state));
        }
        return super.getStateForPlacement(context);
    }

    @Override
    public void setPlacedBy(Level world, BlockPos pos, BlockState state, @Nullable LivingEntity placer,
                         ItemStack itemStack) {
        BlockPos abovePos = pos.above();
        FluidState fluidState = world.getFluidState(abovePos);

        // Top off the stack with the final cube plush.
        if (this.isTripleStacked(state)) {
            BlockState placedState = state.setValue(HALF, DoubleBlockHalf.UPPER).setValue(STACK_SIZE, 3)
                    .setValue(WATERLOGGED, fluidState.is(Fluids.WATER));

            world.setBlock(abovePos, placedState, 3);
        }
    }

    @Override
    public boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
        BlockPos abovePos = context.getClickedPos().above();
        BlockState aboveState = context.getLevel().getBlockState(abovePos);

        return (this.isCubePlush(context) && this.isStackWithinOneBlock(state))
                || (this.isCubePlush(context) && aboveState.canBeReplaced() && this.isDoubleStacked(state));
    }

    @Override
    public BlockState updateShape(BlockState state, LevelReader world, ScheduledTickAccess tickView,
                                                BlockPos pos, Direction direction, BlockPos neighborPos,
                                                BlockState neighborState, RandomSource random) {
        if(this.canStay(state, neighborState, direction)) {
            return state;
        } else {
            return Blocks.AIR.defaultBlockState();
        }
    }

    private boolean canStay(BlockState state, BlockState neighborState, Direction direction) {
        if(this.isTripleStacked(state) && (direction == Direction.DOWN && isUpperHalf(state)
                || direction == Direction.UP && isLowerHalf(state))) {
            return this.isCubePlush(neighborState);
        } else {
            return true;
        }
    }

    public BlockState playerWillDestroy(Level world, BlockPos pos, BlockState state, Player player) {
        // Prevents items from being dropped when breaking a 3-tier plush in creative.
        if ((!world.isClientSide() && (player.isCreative() || !player.hasCorrectToolForDrops(state)))
                && this.isUpperHalf(state)) {
            BlockPos belowPos = pos.below();
            BlockState belowState = world.getBlockState(belowPos);
            if (this.isCubePlush(belowState) && this.isLowerHalf(belowState)) {
                BlockState newState = belowState.getValue(WATERLOGGED) ? Blocks.WATER.defaultBlockState()
                        : Blocks.AIR.defaultBlockState();

                // Replace the cube plush's lower half with either air or water.
                world.setBlock(belowPos, newState, 3);
                world.levelEvent(player, 2001, belowPos, Block.getId(belowState));
            }
        }
        return super.playerWillDestroy(world, pos, state, player);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        Direction direction = state.getValue(FACING);
        VoxelShape bottomShape = BOTTOM_SHAPES.get(direction);
        VoxelShape middleShape = MIDDLE_SHAPES.get(direction);
        VoxelShape topShape = TOP_SHAPES.get(direction);

        return switch (state.getValue(STACK_SIZE)) {
            case 1 -> bottomShape;
            case 2 -> middleShape;
            default -> (this.isUpperHalf(state) ? topShape : middleShape);
        };
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(STACK_SIZE, HALF, WATERLOGGED, FACING);
    }

    private boolean isCubePlush(BlockPlaceContext context) {
        return context.getItemInHand().is(this.asItem());
    }

    private boolean isCubePlush(BlockState state) {
        return state.is(this);
    }

    private boolean isStackWithinOneBlock(BlockState state) {
        return state.getValue(STACK_SIZE) < 2;
    }

    private boolean isDoubleStacked(BlockState state) {
        return Objects.equals(state.getValue(STACK_SIZE), 2);
    }

    private boolean isTripleStacked(BlockState state) {
        return Objects.equals(state.getValue(STACK_SIZE), 3);
    }

    private boolean isUpperHalf(BlockState state) {
        return Objects.equals(state.getValue(HALF), DoubleBlockHalf.UPPER);
    }

    private boolean isLowerHalf(BlockState state) {
        return Objects.equals(state.getValue(HALF), DoubleBlockHalf.LOWER);
    }

    private int getNextStackSize(BlockState state) {
        return Math.min(3, state.getValue(STACK_SIZE) + 1);
    }
}
