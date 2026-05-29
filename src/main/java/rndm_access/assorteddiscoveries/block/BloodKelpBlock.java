package rndm_access.assorteddiscoveries.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.LiquidBlockContainer;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;
import rndm_access.assorteddiscoveries.core.ModBlocks;

public class BloodKelpBlock extends GrowingPlantHeadBlock implements LiquidBlockContainer, BloodKelp {
    public static final MapCodec<BloodKelpBlock> CODEC = simpleCodec(BloodKelpBlock::new);
    private static final VoxelShape SHAPE = Block.box(0.0, 0.0, 0.0,
            16.0, 9.0, 16.0);

    public BloodKelpBlock(BlockBehaviour.Properties settings) {
        super(settings, Direction.UP, SHAPE, true, 0.14);
        this.registerDefaultState(this.defaultBlockState().setValue(LIT, false));
    }

    @Override
    protected Block getBodyBlock() {
        return ModBlocks.BLOOD_KELP_PLANT;
    }

    @Override
    public BlockState updateShape(BlockState state, LevelReader world, ScheduledTickAccess tickView,
                                                BlockPos pos, Direction direction, BlockPos neighborPos,
                                                BlockState neighborState, RandomSource random) {
        return super.updateShape(state, world, tickView, pos, direction, neighborPos,
                        neighborState, random).setValue(LIT, state.getValue(LIT));
    }

    @Override
    protected boolean canGrowInto(BlockState state) {
        return state.is(Blocks.WATER);
    }

    @Override
    protected MapCodec<BloodKelpBlock> codec() {
        return CODEC;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        BlockPos newStemPos = pos.relative(this.growthDirection);
        int age = state.getValue(AGE);

        if (age < 25 && this.canGrowInto(world.getBlockState(newStemPos))) {
            world.setBlock(pos, this.growStemToPlant(state), 2);
            world.setBlockAndUpdate(newStemPos, this.getStemState(random, age).cycle(AGE));
        }
    }

    @Override
    protected boolean canAttachTo(BlockState state) {
        return !state.is(Blocks.MAGMA_BLOCK);
    }

    @Override
    public void performBonemeal(ServerLevel world, RandomSource random, BlockPos pos, BlockState state) {
        BlockPos newStemPos = pos.mutable().relative(this.growthDirection);
        int age = Math.min(state.getValue(AGE) + 1, 25);

        if(this.canGrowInto(world.getBlockState(newStemPos))) {
            world.setBlock(pos, this.growStemToPlant(state), 2);
            world.setBlockAndUpdate(newStemPos, this.getStemState(random, age));
        }
    }

    public BlockState getStemState(RandomSource random, int age) {
        return this.defaultBlockState().setValue(LIT, BloodKelp.isLit(random)).setValue(AGE, age);
    }

    private BlockState growStemToPlant(BlockState stemState) {
        boolean isLit = stemState.getValue(LIT);

        return this.getBodyBlock().defaultBlockState().setValue(BloodKelpPlantBlock.LIT, isLit);
    }

    @Override
    public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random) {
        BloodKelp.playParticles(world, state, pos, random);
    }

    @Override
    protected int getBlocksToGrowWhenBonemealed(RandomSource random) {
        return 1;
    }

    @Override
    public InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit) {
        return BloodKelp.pickSeedCluster(world, player, state, pos);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader world, BlockPos pos, BlockState state) {
        return !state.getValue(LIT) && super.isValidBonemealTarget(world, pos, state);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LIT, AGE);
    }

    @Override
    public boolean canPlaceLiquid(@Nullable LivingEntity filler, BlockGetter world, BlockPos pos, BlockState state,
                                    Fluid fluid) {
        return false;
    }

    @Override
    public boolean placeLiquid(LevelAccessor world, BlockPos pos, BlockState state, FluidState fluidState) {
        return false;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        FluidState fluidState = ctx.getLevel().getFluidState(ctx.getClickedPos());
        return fluidState.is(FluidTags.WATER) && fluidState.getAmount() == 8 ? super.getStateForPlacement(ctx) : null;
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return Fluids.WATER.getSource(false);
    }
}
