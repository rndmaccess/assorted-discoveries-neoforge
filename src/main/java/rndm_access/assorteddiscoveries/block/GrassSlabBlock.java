package rndm_access.assorteddiscoveries.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Util;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.Optional;

public class GrassSlabBlock extends SnowySlabBlock implements BonemealableBlock {
    public GrassSlabBlock(Properties settings) {
        super(settings);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return !isBottom(state);
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        BlockPos above = pos.above();
        BlockState grassState = Blocks.SHORT_GRASS.defaultBlockState();
        Block grassBlock = grassState.getBlock();
        Optional<Holder.Reference<PlacedFeature>> grassFeature = level.registryAccess()
                .lookupOrThrow(Registries.PLACED_FEATURE).get(VegetationPlacements.GRASS_BONEMEAL);
        BlockPos.MutableBlockPos testPos = above.mutable();

        label48:
        for(int j = 0; j < 128; ++j) {
            testPos.set(above); // Reset the blocks after every 128 attempts!

            for(int i = 0; i < j / 16; ++i) {
                int xOffset = random.nextInt(3) - 1;
                int yOffset = (random.nextInt(3) - 1) * random.nextInt(3) / 2;
                int zOffset = random.nextInt(3) - 1;
                testPos.move(xOffset, yOffset, zOffset);

                if (!(level.getBlockState(testPos.below()).getBlock() instanceof BonemealableBlock)
                        || level.getBlockState(testPos).isCollisionShapeFullBlock(level, testPos)) {
                    continue label48;
                }
            }

            BlockState testState = level.getBlockState(testPos);
            if (testState.is(grassBlock) && random.nextInt(10) == 0) {
                BonemealableBlock bonemealableBlock = (BonemealableBlock) grassBlock;

                if (bonemealableBlock.isValidBonemealTarget(level, testPos, testState)) {
                    bonemealableBlock.performBonemeal(level, random, testPos, testState);
                }
            }

            // Place a short grass or flower block (according to the biome)
            if (testState.isAir() && !level.isOutsideBuildHeight(testPos)) {
                if (random.nextInt(8) == 0) {
                    List<ConfiguredFeature<?, ?>> features = level.getBiome(testPos).value().getGenerationSettings().getBoneMealFeatures();

                    if (!features.isEmpty()) {
                        ConfiguredFeature<?, ?> placementFeature = Util.getRandom(features, random);
                        placementFeature.place(level, level.getChunkSource().getGenerator(), random, testPos);
                    }
                } else {
                    grassFeature.ifPresent(placedFeatureRef -> (placedFeatureRef.value())
                            .place(level, level.getChunkSource().getGenerator(), random, testPos));
                }
            }
        }
    }

    @Override
    public @NonNull Type getType() {
        return Type.NEIGHBOR_SPREADER;
    }
}
