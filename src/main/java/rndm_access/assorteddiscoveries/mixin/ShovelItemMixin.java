package rndm_access.assorteddiscoveries.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.item.context.UseOnContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import rndm_access.assorteddiscoveries.block.SnowySlabBlock;
import rndm_access.assorteddiscoveries.core.ModBlocks;

import java.util.HashSet;
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
    private static final HashSet<Block> DIRT_SLAB_LIST;

    @ModifyReturnValue(method = "useOn", at = @At("RETURN"))
    private InteractionResult useOn(InteractionResult original, UseOnContext context) {
        Level world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();
        BlockState state = world.getBlockState(pos);
        Block block = state.getBlock();

        if(DIRT_SLAB_LIST.contains(block) && block instanceof SlabBlock) {
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

    static {
        DIRT_SLAB_LIST = new HashSet<>();
        DIRT_SLAB_LIST.add(ModBlocks.GRASS_SLAB);
        DIRT_SLAB_LIST.add(ModBlocks.PODZOL_SLAB);
        DIRT_SLAB_LIST.add(ModBlocks.COARSE_DIRT_SLAB);
        DIRT_SLAB_LIST.add(ModBlocks.DIRT_SLAB);
        DIRT_SLAB_LIST.add(ModBlocks.MYCELIUM_SLAB);
        DIRT_SLAB_LIST.add(ModBlocks.ROOTED_DIRT_SLAB);
    }
}
