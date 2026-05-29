package rndm_access.assorteddiscoveries.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.crafting.CampfireCookingRecipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import rndm_access.assorteddiscoveries.block_entity.DyedCampfireBlockEntity;
import rndm_access.assorteddiscoveries.core.ModBlockEntityTypes;

public class DyedCampfireBlock extends CampfireBlock {
    private final ParticleOptions emberParticle;

    public DyedCampfireBlock(BlockBehaviour.Properties settings, ParticleOptions sparkParticle) {
        super(false, 1, settings);
        this.emberParticle = sparkParticle;
    }

    @Override
    public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random) {
        if (state.getValue(LIT)) {
            if (random.nextInt(10) == 0) {
                world.playLocalSound(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D,
                        SoundEvents.CAMPFIRE_CRACKLE, SoundSource.BLOCKS, 0.5F + random.nextFloat(),
                        random.nextFloat() * 0.7F + 0.6F, false);
            }

            // Spawn the spark particle randomly.
            if (random.nextInt(5) == 0) {
                for (int i = 0; i < random.nextInt(1) + 1; ++i) {
                    world.addParticle(emberParticle, pos.getX() + 0.5D, pos.getY() + 0.5D,
                            pos.getZ() + 0.5D, random.nextFloat() / 2.0F, 5.0E-5D,
                            random.nextFloat() / 2.0F);
                }
            }
        }
    }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> type) {
        boolean isLit = state.getValue(LIT);

        if (world instanceof ServerLevel serverWorld) {
            if (isLit) {
                RecipeManager.CachedCheck<SingleRecipeInput, CampfireCookingRecipe> matchGetter
                        = RecipeManager.createCheck(RecipeType.CAMPFIRE_COOKING);

                return createTickerHelper(type, ModBlockEntityTypes.DYED_CAMPFIRE,
                        (worldx, pos, statex, blockEntity) ->
                                DyedCampfireBlockEntity.cookTick(serverWorld, pos, statex, blockEntity, matchGetter));
            }
            return createTickerHelper(type, ModBlockEntityTypes.DYED_CAMPFIRE, DyedCampfireBlockEntity::cooldownTick);
        }
        return isLit ? createTickerHelper(type, ModBlockEntityTypes.DYED_CAMPFIRE, DyedCampfireBlockEntity::particleTick) : null;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new DyedCampfireBlockEntity(pos, state);
    }
}
