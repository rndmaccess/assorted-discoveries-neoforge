package rndm_access.assorteddiscoveries.mixin;

import net.minecraft.world.level.WorldGenLevel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rndm_access.assorteddiscoveries.core.ModBlockTags;

import java.util.function.BiConsumer;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;

@Mixin(TrunkPlacer.class)
public abstract class TrunkPlacerMixin {
    @Inject(method = "placeBelowTrunkBlock", at = @At("HEAD"), cancellable = true)
    private static void placeBelowTrunkBlock(WorldGenLevel level, BiConsumer<BlockPos, BlockState> trunkSetter,
                                             RandomSource random, BlockPos pos, TreeConfiguration config, CallbackInfo ci) {
        if(isPlanterBox(level, pos)) {
            ci.cancel();
        }
    }

    @Unique
    private static boolean isPlanterBox(LevelSimulatedReader world, BlockPos pos) {
        return world.isStateAtPosition(pos,
                (state) -> state.is(ModBlockTags.OVERWORLD_PLANTER_BOXES)
                        || state.is(ModBlockTags.NETHER_PLANTER_BOXES));
    }
}
