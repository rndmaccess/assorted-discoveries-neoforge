package rndm_access.assorteddiscoveries.block;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import rndm_access.assorteddiscoveries.core.ModItems;
import rndm_access.assorteddiscoveries.core.ModParticleTypes;

public interface BloodKelp {
    BooleanProperty LIT = BlockStateProperties.LIT;

    static boolean isLit(RandomSource random) {
        float numPicked = random.nextFloat();

        return numPicked < 0.3F;
    }

    static InteractionResult pickSeedCluster(Level world, Player player, BlockState state, BlockPos pos) {
        RandomSource random = RandomSource.create();

        if (state.getValue(LIT)) {
            player.addItem(new ItemStack(ModItems.BLOOD_KELP_SEED_CLUSTER,
                    random.nextInt(3) + 1));
            world.setBlockAndUpdate(pos, state.setValue(LIT, false));
            return InteractionResult.SUCCESS;
        } else {
            return InteractionResult.PASS;
        }
    }

    static void playParticles(Level world, BlockState state, BlockPos pos, RandomSource random) {
        double x = pos.getX() + (random.nextDouble() / 2.0);
        double y = pos.getY() + (random.nextDouble() / 2.0);
        double z = pos.getZ() + (random.nextDouble() / 2.0);

        if (state.getValue(LIT)) {
            world.addParticle(ModParticleTypes.BLOOD_KELP_SPORE, x, y, z, 0.0D,
                    0.0D, 0.0D);
        }
    }
}
