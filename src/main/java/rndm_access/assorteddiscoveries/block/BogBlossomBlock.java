package rndm_access.assorteddiscoveries.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.*;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import rndm_access.assorteddiscoveries.core.ModParticleTypes;

public class BogBlossomBlock extends Block implements BonemealableBlock {
    private static final VoxelShape SHAPE = Block.box(2.0, 0.0, 2.0,
            14.0, 3.0, 14.0);
    public static final MapCodec<BogBlossomBlock> CODEC = simpleCodec(BogBlossomBlock::new);

    public BogBlossomBlock(Properties settings) {
        super(settings);
    }

    @Override
    protected MapCodec<BogBlossomBlock> codec() {
        return CODEC;
    }

    @Override
    public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random) {
        this.playAirNectarParticles(world, random, pos.getX(), pos.getY(), pos.getZ());
    }

    private void playAirNectarParticles(Level world, RandomSource random, int x, int y, int z) {
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        int floatingCount = 10;
        int floatingArea = random.nextInt(4) + 10;
        int risingNum = random.nextInt(2) + 1;

        // Play rising particles
        for (int l = 0; l < risingNum; l++) {
            double risingX = x + random.nextDouble();
            double risingY = y + random.nextDouble();
            double risingZ = z + random.nextDouble();

            world.addParticle(ModParticleTypes.BOG_BLOSSOM_NECTAR, risingX, risingY, risingZ,
                    random.nextDouble(), 2 + random.nextDouble(), random.nextDouble());
        }

        // Play floating particles
        for(int l = 0; l < floatingCount; ++l) {
            int floatingXOrigin = x + Mth.nextInt(random, -floatingArea, floatingArea);
            int floatingYOrigin = y + random.nextInt(floatingArea);
            int floatingZOrigin = z + Mth.nextInt(random, -floatingArea, floatingArea);

            mutable.set(floatingXOrigin, floatingYOrigin, floatingZOrigin);
            BlockState blockState = world.getBlockState(mutable);

            if (!blockState.isCollisionShapeFullBlock(world, mutable)) {
                double floatingX = mutable.getX() + random.nextDouble();
                double floatingY = mutable.getY() + random.nextDouble();
                double floatingZ = mutable.getZ() + random.nextDouble();

                world.addParticle(ModParticleTypes.BOG_BLOSSOM_NECTAR, floatingX, floatingY, floatingZ,
                        0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        return Block.canSupportCenter(world, pos.below(), Direction.DOWN) && !world.isWaterAt(pos);
    }

    @Override
    public BlockState updateShape(BlockState state, LevelReader world, ScheduledTickAccess tickView,
                                                BlockPos pos, Direction direction, BlockPos neighborPos,
                                                BlockState neighborState, RandomSource random) {
        return direction == Direction.DOWN && !this.canSurvive(state, world, pos) ? Blocks.AIR.defaultBlockState()
                : state;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader world, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(Level world, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel world, RandomSource random, BlockPos pos, BlockState state) {
        BlockPos.MutableBlockPos mutablePos = pos.mutable();
        boolean placed = false;
        int tries = 0;

        do {
            int xOffset = random.nextInt(4) - random.nextInt(4);
            int yOffset = random.nextInt(4) - random.nextInt(4);
            int zOffset = random.nextInt(4) - random.nextInt(4);
            mutablePos.move(xOffset, yOffset, zOffset);
            BlockPos placePos = mutablePos.immutable();
            BlockState worldState = world.getBlockState(placePos);

            if (this.canSurvive(null, world, placePos) && (worldState.isAir() || worldState.canBeReplaced())) {
                world.setBlockAndUpdate(placePos, this.defaultBlockState());
                placed = true;
            }
            tries++;
            mutablePos = pos.mutable();
        } while (!placed && tries < 24); // Try to place a block 24 times before giving up!
    }
}
