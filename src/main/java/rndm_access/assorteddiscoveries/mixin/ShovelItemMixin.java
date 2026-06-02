package rndm_access.assorteddiscoveries.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.item.context.UseOnContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import rndm_access.assorteddiscoveries.AssortedDiscoveries;
import rndm_access.assorteddiscoveries.block.SnowySlabBlock;
import rndm_access.assorteddiscoveries.core.ModBlocks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.function.Supplier;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;

@Mixin(ShovelItem.class)
public abstract class ShovelItemMixin {
    @Unique
    private static final List<Supplier<Block>> WRAPPED_DIRT_SLABS;
    @Unique
    private static final HashSet<Block> DIRT_SLABS;

    @ModifyReturnValue(method = "useOn", at = @At("RETURN"))
    private InteractionResult useOn(InteractionResult original, UseOnContext context) {
        Level world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();
        BlockState state = world.getBlockState(pos);
        Block block = state.getBlock();

        if(containsSlab(block) && block instanceof SlabBlock) {
            if(state.hasProperty(SnowySlabBlock.SNOWY) && state.getValue(SnowySlabBlock.SNOWY).equals(true)) {
                return InteractionResult.FAIL;
            }

            world.playSound(player, pos, SoundEvents.SHOVEL_FLATTEN, SoundSource.BLOCKS, 1.0F, 1.0F);
            world.setBlockAndUpdate(pos, ModBlocks.DIRT_PATH_SLAB.defaultBlockState()
                    .setValue(SlabBlock.WATERLOGGED, state.getValue(SlabBlock.WATERLOGGED))
                    .setValue(SlabBlock.TYPE, state.getValue(SlabBlock.TYPE)));
            return InteractionResult.SUCCESS;
        }
        return original;
    }

    @Unique
    private static boolean containsSlab(Block block) {
        if (DIRT_SLABS.isEmpty()) {
            // Unwrap the slab blocks here to ensure that they are registered!
            for (Supplier<Block> slab : WRAPPED_DIRT_SLABS) {
                DIRT_SLABS.add(slab.get());
            }
        }
        return DIRT_SLABS.contains(block);
    }

    static {
        DIRT_SLABS = new HashSet<>();
        WRAPPED_DIRT_SLABS = new ArrayList<>();
        WRAPPED_DIRT_SLABS.add(ModBlocks.GRASS_SLAB);
        WRAPPED_DIRT_SLABS.add(ModBlocks.PODZOL_SLAB);
        WRAPPED_DIRT_SLABS.add(ModBlocks.COARSE_DIRT_SLAB);
        WRAPPED_DIRT_SLABS.add(ModBlocks.DIRT_SLAB);
        WRAPPED_DIRT_SLABS.add(ModBlocks.MYCELIUM_SLAB);
        WRAPPED_DIRT_SLABS.add(ModBlocks.ROOTED_DIRT_SLAB);
    }
}
