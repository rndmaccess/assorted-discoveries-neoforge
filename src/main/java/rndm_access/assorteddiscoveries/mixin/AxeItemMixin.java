package rndm_access.assorteddiscoveries.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.item.context.UseOnContext;
import net.neoforged.neoforge.registries.DeferredBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import oshi.util.tuples.Pair;
import rndm_access.assorteddiscoveries.core.ModBlocks;

import java.util.HashMap;
import java.util.List;
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
    private static final List<Pair<DeferredBlock<Block>, DeferredBlock<Block>>> WRAPPED_STRIPPABLE_WALLS
            = List.of(new Pair<>(ModBlocks.OAK_WALL, ModBlocks.STRIPPED_OAK_WALL),
                    new Pair<>(ModBlocks.SPRUCE_WALL, ModBlocks.STRIPPED_SPRUCE_WALL),
                    new Pair<>(ModBlocks.BIRCH_WALL, ModBlocks.STRIPPED_BIRCH_WALL),
                    new Pair<>(ModBlocks.JUNGLE_WALL, ModBlocks.STRIPPED_JUNGLE_WALL),
                    new Pair<>(ModBlocks.ACACIA_WALL, ModBlocks.STRIPPED_ACACIA_WALL),
                    new Pair<>(ModBlocks.DARK_OAK_WALL, ModBlocks.STRIPPED_DARK_OAK_WALL),
                    new Pair<>(ModBlocks.MANGROVE_WALL, ModBlocks.STRIPPED_MANGROVE_WALL),
                    new Pair<>(ModBlocks.CRIMSON_WALL, ModBlocks.STRIPPED_CRIMSON_WALL),
                    new Pair<>(ModBlocks.WARPED_WALL, ModBlocks.STRIPPED_WARPED_WALL),
                    new Pair<>(ModBlocks.CHERRY_WALL, ModBlocks.STRIPPED_CHERRY_WALL),
                    new Pair<>(ModBlocks.PALE_OAK_WALL, ModBlocks.STRIPPED_PALE_OAK_WALL),
                    new Pair<>(ModBlocks.BAMBOO_WALL, ModBlocks.STRIPPED_BAMBOO_WALL));
    @Unique
    private static final Map<Block, Block> STRIPPABLE_WALLS = new HashMap<>();

    @ModifyReturnValue(method = "useOn", at = @At("RETURN"))
    private InteractionResult useOn(InteractionResult original, UseOnContext context) {
        Level world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();
        BlockState state = world.getBlockState(pos);
        Block block = state.getBlock();

        assorted_discoveries_neoforge$unwrapStrippedWalls();

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

    @Unique
    private static void assorted_discoveries_neoforge$unwrapStrippedWalls() {
        if (STRIPPABLE_WALLS.isEmpty()) {
            for (Pair<DeferredBlock<Block>, DeferredBlock<Block>> entry : WRAPPED_STRIPPABLE_WALLS) {
                Block wall = entry.getA().get();
                Block strippedWall = entry.getB().get();
                STRIPPABLE_WALLS.put(wall, strippedWall);
            }
        }
    }
}
