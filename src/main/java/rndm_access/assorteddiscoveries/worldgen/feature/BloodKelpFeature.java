package rndm_access.assorteddiscoveries.worldgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.material.Fluids;
import rndm_access.assorteddiscoveries.block.BloodKelpBlock;
import rndm_access.assorteddiscoveries.block.BloodKelpPlantBlock;
import rndm_access.assorteddiscoveries.core.ModBlocks;

public class BloodKelpFeature extends Feature<NoneFeatureConfiguration> {
    public BloodKelpFeature(Codec<NoneFeatureConfiguration> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel world = context.level();
        BlockPos originPos = context.origin();
        int x = originPos.getX();
        int z = originPos.getZ();
        int y = world.getHeight(Heightmap.Types.OCEAN_FLOOR, x, z);
        BlockPos.MutableBlockPos placePos = new BlockPos(x, y, z).mutable();

        return placeBloodKelpStalk(world, context.random(), placePos);
    }

    private boolean placeBloodKelpStalk(WorldGenLevel world, RandomSource random, BlockPos.MutableBlockPos placePos) {
        BloodKelpBlock stemBlock = (BloodKelpBlock) ModBlocks.BLOOD_KELP;
        BloodKelpPlantBlock plantBlock = (BloodKelpPlantBlock) ModBlocks.BLOOD_KELP_PLANT;
        int maxLength = 1 + random.nextInt(10);
        boolean canSustainPlant = stemBlock.defaultBlockState().canSurvive(world, placePos);
        boolean isInWater = world.getFluidState(placePos).is(Fluids.WATER);

        if(!canSustainPlant || !isInWater) {
            return false;
        }

        // Place a stalk of blood kelp.
        for (int length = 0; length <= maxLength; ++length) {
            boolean isEmptyAbove = world.getFluidState(placePos.above()).isEmpty();

            if (isEmptyAbove || length == maxLength) {
                world.setBlock(placePos, stemBlock.getStemState(random, random.nextInt(4) + 20), 2);
                return true;
            } else {
                world.setBlock(placePos, plantBlock.getPlantState(random), 2);
                placePos.move(Direction.UP);
            }
        }
        return false;
    }
}
