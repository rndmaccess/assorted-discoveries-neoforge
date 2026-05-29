package rndm_access.assorteddiscoveries.block;

import com.google.common.collect.ImmutableList;
import org.jetbrains.annotations.Nullable;
import rndm_access.assorteddiscoveries.util.ShapeHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlanterBoxBlock extends Block {
    public static final BooleanProperty NORTH = BlockStateProperties.NORTH;
    public static final BooleanProperty SOUTH = BlockStateProperties.SOUTH;
    public static final BooleanProperty WEST = BlockStateProperties.WEST;
    public static final BooleanProperty EAST = BlockStateProperties.EAST;
    private static final HashMap<List<Boolean>, VoxelShape> SHAPES = collectStateShapes();

    public PlanterBoxBlock(BlockBehaviour.Properties settings) {
        super(settings);
        this.registerDefaultState(this.defaultBlockState().setValue(NORTH, false).setValue(SOUTH, false)
                .setValue(WEST, false).setValue(EAST, false));
    }

    /**
     * Makes a map of all shapes that the planter box can take according to its state.
     */
    private static HashMap<List<Boolean>, VoxelShape> collectStateShapes() {
        VoxelShape bottomShape = Block.box(0.0, 0.0, 0.0, 16.0,
                15.0, 16.0);
        VoxelShape northBorderShape = Block.box(0.0, 15.0, 13.0, 16.0,
                16.0, 16.0);
        List<VoxelShape> borderShapes = ShapeHelper.makeShapeRotList(northBorderShape);
        HashMap<List<Boolean>, VoxelShape> shapes = new HashMap<>();
        int borderNum = 4;
        int stateNum = (int) Math.pow(2, borderNum);

        for (int i = 0; i < stateNum; i++) {
            ArrayList<Boolean> borders = new ArrayList<>(4);
            VoxelShape tempBorderShape = Shapes.empty();

            for (int j = 0; j < borderNum; j++) {
                int bit = (i >> j) & 0x01;

                // When the bit is 1 there is a planter box edge here.
                if (bit == 1) {
                    borders.add(false);
                    tempBorderShape = Shapes.or(tempBorderShape, borderShapes.get(j));
                } else {
                    borders.add(true);
                }
            }
            shapes.put(borders, Shapes.or(tempBorderShape, bottomShape));
        }
        return shapes;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        List<Boolean> stateProperties = ImmutableList.of(state.getValue(SOUTH), state.getValue(NORTH), state.getValue(EAST),
                state.getValue(WEST));

        return SHAPES.get(stateProperties);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.getPlanterBoxState(this.defaultBlockState(), context.getLevel(), context.getClickedPos());
    }

    @Override
    public BlockState updateShape(BlockState state, LevelReader world, ScheduledTickAccess tickView,
                                                BlockPos pos, Direction direction, BlockPos neighborPos,
                                                BlockState neighborState, RandomSource random) {
        return this.getPlanterBoxState(state, world, pos);
    }

    private BlockState getPlanterBoxState(BlockState state, LevelReader world, BlockPos pos) {
        return state.setValue(NORTH, world.getBlockState(pos.north()).is(this))
                .setValue(SOUTH, world.getBlockState(pos.south()).is(this))
                .setValue(WEST, world.getBlockState(pos.west()).is(this))
                .setValue(EAST, world.getBlockState(pos.east()).is(this));
    }

    /**
     * When a structure is rotated with this block in it. This method decides what
     * state each block should be in.
     */
    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        boolean north = state.getValue(NORTH);
        boolean south = state.getValue(SOUTH);
        boolean west = state.getValue(WEST);
        boolean east = state.getValue(EAST);

        switch (rotation) {
            case CLOCKWISE_180 -> {
                return state.setValue(SOUTH, north)
                        .setValue(NORTH, south)
                        .setValue(EAST, west)
                        .setValue(WEST, east);
            }
            case CLOCKWISE_90 -> {
                return state.setValue(EAST, north)
                        .setValue(WEST, south)
                        .setValue(NORTH, west)
                        .setValue(SOUTH, east);
            }
            case COUNTERCLOCKWISE_90 -> {
                return state.setValue(WEST, north)
                        .setValue(EAST, south)
                        .setValue(SOUTH, west)
                        .setValue(NORTH, east);
            }
            default -> {
                return state;
            }
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(NORTH, SOUTH, WEST, EAST);
    }
}
