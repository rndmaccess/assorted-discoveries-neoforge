package rndm_access.assorteddiscoveries.block;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import rndm_access.assorteddiscoveries.core.ModSoundEvents;

public class PurpleMushroomBlock extends HugeMushroomBlock {
    public PurpleMushroomBlock(BlockBehaviour.Properties settings) {
        super(settings);
    }

    @Override
    public void fallOn(Level world, BlockState state, BlockPos pos, Entity entity, double fallDistance) {
        SoundEvent sound = ModSoundEvents.BLOCK_MUSHROOM_BOUNCE;
        RandomSource random = world.getRandom();
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        if(entity.isShiftKeyDown()) {
            world.playLocalSound(x, y, z, sound, SoundSource.BLOCKS,
                    1.0F, 0.8F + random.nextFloat() / 0.4F, true);
        } else {
            world.playLocalSound(x, y, z, sound, SoundSource.BLOCKS, 1.0F,
                    0.8F + random.nextFloat() * 0.4F, true);
        }
    }

    @Override
    public void updateEntityMovementAfterFallOn(BlockGetter world, Entity entity) {
        float jumpHeight = 0.2F;

        if(entity.getDeltaMovement().y() < -jumpHeight && !entity.isShiftKeyDown()) {
            Vec3 velocity = entity.getDeltaMovement();
            double minBounce = 2;
            double bounceHeight = (entity.getDeltaMovement().y() * jumpHeight) + minBounce;
            double bounceYVelocity = Math.sqrt(jumpHeight * (bounceHeight + jumpHeight));

            entity.setDeltaMovement(velocity.x(), -velocity.y() + bounceYVelocity,
                    velocity.z());
        } else {
            entity.setDeltaMovement(entity.getDeltaMovement().multiply(1.0, 0.0, 1.0));
        }
    }
}
