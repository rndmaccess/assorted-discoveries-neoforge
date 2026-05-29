package rndm_access.assorteddiscoveries.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantBodyBlock;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.LiquidBlockContainer;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.Shapes;
import org.jetbrains.annotations.Nullable;
import rndm_access.assorteddiscoveries.core.ModBlocks;

public class BloodKelpPlantBlock extends GrowingPlantBodyBlock implements LiquidBlockContainer, BloodKelp {
    public static final MapCodec<BloodKelpPlantBlock> CODEC = simpleCodec(BloodKelpPlantBlock::new);

    public BloodKelpPlantBlock(BlockBehaviour.Properties settings) {
        super(settings, Direction.UP, Shapes.block(), true);
        this.registerDefaultState(this.getStateDefinition().any().setValue(LIT, false));
    }

    @Override
    protected MapCodec<BloodKelpPlantBlock> codec() {
        return CODEC;
    }

    @Override
    protected GrowingPlantHeadBlock getHeadBlock() {
        return (GrowingPlantHeadBlock) ModBlocks.BLOOD_KELP;
    }

    public BlockState getPlantState(RandomSource random) {
        return this.defaultBlockState().setValue(LIT, BloodKelp.isLit(random));
    }

    @Override
    public BlockState updateShape(BlockState state, LevelReader world, ScheduledTickAccess tickView,
                                                BlockPos pos, Direction direction, BlockPos neighborPos,
                                                BlockState neighborState, RandomSource random) {
        return super.updateShape(state, world, tickView, pos, direction, neighborPos,
                        neighborState, random).setValue(LIT, state.getValue(LIT));
    }

    @Override
    public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random) {
        BloodKelp.playParticles(world, state, pos, random);
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
    public FluidState getFluidState(BlockState state) {
        return Fluids.WATER.getSource(false);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LIT);
    }

    public boolean canPlaceLiquid(@Nullable LivingEntity filler, BlockGetter world, BlockPos pos, BlockState state,
                                    Fluid fluid) {
        return false;
    }

    public boolean placeLiquid(LevelAccessor world, BlockPos pos, BlockState state, FluidState fluidState) {
        return false;
    }
}
