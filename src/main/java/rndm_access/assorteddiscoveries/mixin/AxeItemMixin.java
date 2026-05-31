package rndm_access.assorteddiscoveries.mixin;

import com.google.common.collect.ImmutableMap;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.item.context.UseOnContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import rndm_access.assorteddiscoveries.core.ModBlocks;

import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockState;

@Mixin(AxeItem.class)
public abstract class AxeItemMixin {
    @Unique
    private static final Map<Block, Block> STRIPPABLE_WALLS = new ImmutableMap.Builder<Block, Block>()
            .put(ModBlocks.OAK_WALL.get(), ModBlocks.STRIPPED_OAK_WALL.get())
            .put(ModBlocks.SPRUCE_WALL.get(), ModBlocks.STRIPPED_SPRUCE_WALL.get())
            .put(ModBlocks.BIRCH_WALL.get(), ModBlocks.STRIPPED_BIRCH_WALL.get())
            .put(ModBlocks.JUNGLE_WALL.get(), ModBlocks.STRIPPED_JUNGLE_WALL.get())
            .put(ModBlocks.ACACIA_WALL.get(), ModBlocks.STRIPPED_ACACIA_WALL.get())
            .put(ModBlocks.DARK_OAK_WALL.get(), ModBlocks.STRIPPED_DARK_OAK_WALL.get())
            .put(ModBlocks.MANGROVE_WALL.get(), ModBlocks.STRIPPED_MANGROVE_WALL.get())
            .put(ModBlocks.CRIMSON_WALL.get(), ModBlocks.STRIPPED_CRIMSON_WALL.get())
            .put(ModBlocks.WARPED_WALL.get(), ModBlocks.STRIPPED_WARPED_WALL.get())
            .put(ModBlocks.CHERRY_WALL.get(), ModBlocks.STRIPPED_CHERRY_WALL.get()).build();

    @ModifyReturnValue(method = "useOn", at = @At("RETURN"))
    private InteractionResult useOn(InteractionResult original, UseOnContext context) {
        Level world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();
        BlockState state = world.getBlockState(pos);
        Block block = state.getBlock();

        if (STRIPPABLE_WALLS.containsKey(block) && block instanceof WallBlock) {
            world.playSound(player, pos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);
            world.setBlockAndUpdate(pos, STRIPPABLE_WALLS.get(block).defaultBlockState()
                    .setValue(WallBlock.NORTH, state.getValue(WallBlock.NORTH))
                    .setValue(WallBlock.SOUTH, state.getValue(WallBlock.SOUTH))
                    .setValue(WallBlock.WEST, state.getValue(WallBlock.WEST))
                    .setValue(WallBlock.EAST, state.getValue(WallBlock.EAST))
                    .setValue(WallBlock.UP, state.getValue(WallBlock.UP))
                    .setValue(WallBlock.WATERLOGGED, state.getValue(WallBlock.WATERLOGGED)));
            return InteractionResult.SUCCESS;
        }
        return original;
    }
}
