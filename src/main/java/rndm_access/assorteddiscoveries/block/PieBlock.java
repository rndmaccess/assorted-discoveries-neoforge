package rndm_access.assorteddiscoveries.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PieBlock extends Block {
    public static final IntegerProperty BITES = BlockStateProperties.BITES;
    private static final VoxelShape[] SHAPE_BY_BITE = new VoxelShape[] {
            Block.box(1.0D, 0.0D, 1.0D, 15.0D, 6.0D, 15.0D),
            Block.box(3.0D, 0.0D, 1.0D, 15.0D, 6.0D, 15.0D),
            Block.box(5.0D, 0.0D, 1.0D, 15.0D, 6.0D, 15.0D),
            Block.box(7.0D, 0.0D, 1.0D, 15.0D, 6.0D, 15.0D),
            Block.box(9.0D, 0.0D, 1.0D, 15.0D, 6.0D, 15.0D),
            Block.box(11.0D, 0.0D, 1.0D, 15.0D, 6.0D, 15.0D),
            Block.box(13.0D, 0.0D, 1.0D, 15.0D, 6.0D, 15.0D)
    };
    private final int nutrition;
    private final float saturationMod;

    public PieBlock(BlockBehaviour.Properties settings, int nutrition, float saturationMod) {
        super(settings);
        this.nutrition = nutrition;
        this.saturationMod = saturationMod;
    }

    @Override
    public BlockState updateShape(BlockState state, LevelReader world, ScheduledTickAccess tickView,
                                                BlockPos pos, Direction direction, BlockPos neighborPos,
                                                BlockState neighborState, RandomSource random) {
        if (direction == Direction.DOWN && !state.canSurvive(world, pos)) {
            return Blocks.AIR.defaultBlockState();
        }
        return state;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        BlockPos floorPos = pos.below();

        return world.getBlockState(floorPos).isSolid();
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return SHAPE_BY_BITE[state.getValue(BITES)];
    }

    @Override
    public InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit) {
        if (world.isClientSide() && this.tryEat(world, pos, state, player).consumesAction()) {
            return InteractionResult.SUCCESS;
        }
        return this.tryEat(world, pos, state, player);
    }

    private InteractionResult tryEat(LevelAccessor world, BlockPos pos, BlockState state, Player player) {
        if (player.canEat(false)) {
            int bitesTaken = state.getValue(BITES);

            player.getFoodData().eat(this.nutrition, this.saturationMod);
            return ModdedCakeBlock.eat(world, pos, state, player, bitesTaken, BITES);
        }
        return InteractionResult.PASS;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BITES);
    }
}
