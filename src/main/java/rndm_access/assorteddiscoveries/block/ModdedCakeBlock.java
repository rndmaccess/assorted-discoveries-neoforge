package rndm_access.assorteddiscoveries.block;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

public class ModdedCakeBlock extends CakeBlock {
    public ModdedCakeBlock(Properties settings) {
        super(settings);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level world, BlockPos pos,
                                         Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack heldStack = player.getItemInHand(hand);

        if (state.getValue(BITES) == 0) {
            Item item = heldStack.getItem();
            Block block = Block.byItem(item);

            if (ModdedCandleCakeBlock.containsCandleCake(this, block)) {
                return this.placeCandleCake(world, player, pos, heldStack, block, item);
            }
        }
        return InteractionResult.TRY_WITH_EMPTY_HAND;
    }

    private InteractionResult placeCandleCake(Level world, Player player, BlockPos pos, ItemStack itemStack,
                                         Block block, Item item) {
        itemStack.consume(1, player);
        world.playSound(null, pos, SoundEvents.CAKE_ADD_CANDLE, SoundSource.BLOCKS,
                1.0F, 1.0F);
        world.setBlockAndUpdate(pos, ModdedCandleCakeBlock.getCandleCake(this, block));
        world.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
        player.awardStat(Stats.ITEM_USED.get(item));
        return InteractionResult.SUCCESS;
    }

    public static InteractionResult tryEatCake(LevelAccessor world, BlockPos pos, BlockState state, Player player) {
        if (!player.canEat(false)) {
            return InteractionResult.PASS;
        }
        else {
            int bitesTaken = state.getValue(BITES);

            player.awardStat(Stats.EAT_CAKE_SLICE);
            player.getFoodData().eat(2, 0.1F);
            return eat(world, pos, state, player, bitesTaken, BITES);
        }
    }

    @NotNull
    public static InteractionResult eat(LevelAccessor world, BlockPos pos, BlockState state, Player player,
                                   int bitesTaken, IntegerProperty property) {
        world.gameEvent(player, GameEvent.EAT, pos);

        if (bitesTaken < 6) {
            world.setBlock(pos, state.setValue(property, ++bitesTaken), 3);
        } else {
            world.removeBlock(pos, false);
            world.gameEvent(player, GameEvent.BLOCK_DESTROY, pos);
        }
        return InteractionResult.SUCCESS;
    }
}
