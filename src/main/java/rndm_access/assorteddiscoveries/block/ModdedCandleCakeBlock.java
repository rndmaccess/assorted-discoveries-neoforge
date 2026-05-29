package rndm_access.assorteddiscoveries.block;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.AbstractCandleBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import rndm_access.assorteddiscoveries.util.HashPair;

import java.util.Map;

public class ModdedCandleCakeBlock extends AbstractCandleBlock {
    public static final MapCodec<ModdedCandleCakeBlock> CODEC
            = RecordCodecBuilder.mapCodec((instance) -> instance.group(
            BuiltInRegistries.BLOCK.byNameCodec().fieldOf("cake").forGetter((block) -> block.cake),
            BuiltInRegistries.BLOCK.byNameCodec().fieldOf("candle").forGetter((item) -> item.candle),
            propertiesCodec()).apply(instance, ModdedCandleCakeBlock::new));
    public static final BooleanProperty LIT = AbstractCandleBlock.LIT;
    private static final VoxelShape CAKE_SHAPE = Block.box(1.0D, 0.0D, 1.0D,
            15.0D, 8.0D, 15.0D);
    private static final VoxelShape CANDLE_SHAPE = Block.box(7.0D, 8.0D, 7.0D,
            9.0D, 14.0D, 9.0D);
    private static final VoxelShape SHAPE = Shapes.or(CAKE_SHAPE, CANDLE_SHAPE);
    private static final Map<HashPair<Block, Block>, ModdedCandleCakeBlock> CANDLES_TO_CANDLE_CAKES = Maps.newHashMap();
    private final Block cake;
    private final Block candle;

    public ModdedCandleCakeBlock(Block cake, Block candle, Properties settings) {
        super(settings);
        this.registerDefaultState(this.stateDefinition.any().setValue(LIT, false));
        this.cake = cake;
        this.candle = candle;

        CANDLES_TO_CANDLE_CAKES.put(new HashPair<>(cake, candle), this);
    }

    @Override
    public ItemStack getCloneItemStack(LevelReader world, BlockPos pos, BlockState state, boolean includeData) {
        return new ItemStack(cake);
    }

    @Override
    public InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit) {
        InteractionHand hand = player.getUsedItemHand();
        ItemStack handStack = player.getItemInHand(hand);

        if (handStack.is(Items.FLINT_AND_STEEL) && handStack.is(Items.FIRE_CHARGE)) {
            return InteractionResult.PASS;
        }

        if (isHittingCandle(hit) && player.getItemInHand(hand).isEmpty() && state.getValue(LIT)) {
            extinguish(player, state, world, pos);
            return InteractionResult.SUCCESS;
        } else {
            InteractionResult actionResult = ModdedCakeBlock.tryEatCake(world, pos,
                    this.cake.defaultBlockState(), player);

            if (actionResult.consumesAction()) {
                dropResources(state, world, pos);
            }
            return actionResult;
        }
    }

    private static boolean isHittingCandle(BlockHitResult hitResult) {
        return hitResult.getLocation().y - (double)hitResult.getBlockPos().getY() > 0.5D;
    }

    public static BlockState getCandleCake(Block cake, Block candle) {
        return CANDLES_TO_CANDLE_CAKES.get(new HashPair<>(cake, candle)).defaultBlockState();
    }

    public static boolean containsCandleCake(Block cake, Block candle) {
        return CANDLES_TO_CANDLE_CAKES.containsKey(new HashPair<>(cake, candle));
    }

    @Override
    protected MapCodec<? extends AbstractCandleBlock> codec() {
        return CODEC;
    }

    @Override
    protected Iterable<Vec3> getParticleOffsets(BlockState state) {
        return ImmutableList.of(new Vec3(0.5D, 1.0D, 0.5D));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public BlockState updateShape(BlockState state, LevelReader world, ScheduledTickAccess tickView,
                                                BlockPos pos, Direction direction, BlockPos neighborPos,
                                                BlockState neighborState, RandomSource random) {
        return direction == Direction.DOWN && !state.canSurvive(world, pos) ? Blocks.AIR.defaultBlockState() : state;
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        return world.getBlockState(pos.below()).isRedstoneConductor(world, pos);
    }

    @Override
    public int getAnalogOutputSignal(BlockState state, Level world, BlockPos pos, Direction direction) {
        return CakeBlock.FULL_CAKE_SIGNAL;
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType type) {
        return false;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LIT);
    }
}
