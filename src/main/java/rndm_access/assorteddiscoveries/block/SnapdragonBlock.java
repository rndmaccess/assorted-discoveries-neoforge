package rndm_access.assorteddiscoveries.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import rndm_access.assorteddiscoveries.core.ModBlockTags;

public class SnapdragonBlock extends FlowerBlock {
    public SnapdragonBlock(Holder<MobEffect> stewEffect, int effectDuration,
                           BlockBehaviour.Properties settings) {
        super(stewEffect, effectDuration, settings);
    }

    @Override
    protected boolean mayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos) {
        return floor.is(ModBlockTags.SNAPDRAGON_PLANTABLE_ON);
    }

    @Override
    public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random) {
        double x = (double) pos.getX() + random.nextFloat();
        double y = (double) pos.getY() + random.nextFloat();
        double z = (double) pos.getZ() + random.nextFloat();

        world.addParticle(ParticleTypes.PORTAL, x, y, z, 0.0D,
                0.0D, 0.0D);
    }
}
