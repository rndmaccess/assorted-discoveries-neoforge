package rndm_access.assorteddiscoveries.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.gameevent.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import rndm_access.assorteddiscoveries.config.ModClientConfig;
import rndm_access.assorteddiscoveries.config.ModServerConfig;
import rndm_access.assorteddiscoveries.config.ModServerConfigKeys;
import rndm_access.assorteddiscoveries.config.json.deserializer.entries.BooleanConfigEntry;
import rndm_access.assorteddiscoveries.core.ModBlockTags;
import rndm_access.assorteddiscoveries.core.ModBlocks;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

@Mixin(BoneMealItem.class)
public abstract class BoneMealItemMixin {

    @ModifyReturnValue(method = "useOn", at = @At("RETURN"))
    private InteractionResult useOn(InteractionResult original, UseOnContext context) {
        BlockPos soilPos = context.getClickedPos();
        Level level = context.getLevel();
        boolean isBoneMealable = level.getBlockState(soilPos).is(ModBlockTags.END_BONE_MEALABLE_BLOCKS);
        BlockPos centerPos = soilPos.above();
        boolean isEmptyAbove = level.getBlockState(centerPos).isAir();
        boolean isEnabled = ((BooleanConfigEntry) ModServerConfig.getInstance()
                .getEntry(ModServerConfigKeys.ENABLE_ENDER_PLANTS)).getValue();

        if (!level.isClientSide() && !isEnabled) {
            return original;
        }

        boolean isEnabledClient = ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_ENDER_PLANTS);
        if (level.isClientSide() && !isEnabledClient) {
            return original;
        }

        // Grow snapdragons and ender grass on blocks in the END_BONE_MEALABLE_BLOCKS when using bone meal.
        if (isBoneMealable && !level.isOutsideBuildHeight(centerPos) && isEmptyAbove) {
            applyBoneMeal(context, level, centerPos);
            return InteractionResult.SUCCESS;
        }
        return original;
    }

    @Unique
    private static void applyBoneMeal(UseOnContext context, Level level, BlockPos centerPos) {
        Player player = context.getPlayer();
        ItemStack boneMealStack = context.getItemInHand();

        if (!level.isClientSide()) {
            if (player != null) {
                boneMealStack.causeUseVibration(player, GameEvent.ITEM_INTERACT_FINISH);
            }
            growEnderPlants(level, centerPos);
        }
        spawnGrowthParticles(level, centerPos);

        boneMealStack.shrink(1);
        level.playSound(null, centerPos, SoundEvents.BONE_MEAL_USE, SoundSource.BLOCKS);
    }

    @Unique
    private static void spawnGrowthParticles(final Level level, final BlockPos pos) {
        int count = level.getRandom().nextInt(10);
        ParticleUtils.spawnParticles(level, pos, count * 3, 3.0D, 1.0D,
                false, ParticleTypes.HAPPY_VILLAGER);
    }

    @Unique
    private static void growEnderPlants(Level level, BlockPos centerPos) {
        RandomSource random = level.getRandom();
        BlockPos.MutableBlockPos plantPos = centerPos.mutable();
        BlockPos.MutableBlockPos soilPos = centerPos.below().mutable();

        for (int i = 0; i < 256; ++i) {
            plantPos.set(centerPos);

            // A short walk ensuring that it stays relatively close to the center. This "walk" favors the center.
            int steps = 4 + random.nextInt(5);
            for (int j = 0; j < steps; ++j) {
                plantPos.move(random.nextInt(3) - 1, random.nextInt(2) - random.nextInt(2), random.nextInt(3) - 1);
            }

            // Skip the blocks where plants can't grow!
            if (!level.getBlockState(plantPos).isAir()) continue;

            // Calculate distance from the true center
            double distSq = plantPos.distSqr(centerPos);
            double maxRadiusSq = 100.0; // 10 blocks out

            // 1.0 at center, tapering to 0.0 at edge
            float chance = (float) Math.max(0, 1.0 - (distSq / maxRadiusSq));

            // High density multiplier (0.9F) keeps the center thick
            if (random.nextFloat() > (chance * 0.9F)) continue;

            soilPos.set(plantPos.getX(), plantPos.getY() - 1, plantPos.getZ());
            if (level.getBlockState(soilPos).is(ModBlockTags.END_BONE_MEALABLE_BLOCKS)) {
                placeBlock(level, random, plantPos);
            }
        }
    }

    @Unique
    private static void placeBlock(Level level, RandomSource random, BlockPos pos) {
        boolean placeSnapdragon = random.nextFloat() <= 0.4F; // 40% chance

        if(placeSnapdragon) {
            level.setBlockAndUpdate(pos, ModBlocks.SNAPDRAGON.defaultBlockState());
        } else {
            level.setBlockAndUpdate(pos, ModBlocks.SHORT_ENDER_GRASS.defaultBlockState());
        }
    }
}
