package rndm_access.assorteddiscoveries.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.NonNull;

import java.util.Objects;

public abstract class AbstractBerryBushBlock extends VegetationBlock implements BonemealableBlock {
    public static IntegerProperty AGE = BlockStateProperties.AGE_3;
    public static VoxelShape SMALL_SHAPE = Block.box(3.0, 0.0, 3.0,
            13.0, 8.0, 13.0);
    public static VoxelShape LARGE_SHAPE = Block.box(1.0, 0.0, 1.0,
            15.0, 16.0, 15.0);

    public AbstractBerryBushBlock(BlockBehaviour.Properties settings) {
        super(settings);
    }

    @Override
    protected abstract @NonNull MapCodec<? extends AbstractBerryBushBlock> codec();

    @Override
    protected abstract void createBlockStateDefinition(StateDefinition.@NonNull Builder<Block, BlockState> builder);

    protected abstract Item berryItem();

    protected abstract TagKey<EntityType<?>> mobsImmune();

    protected abstract boolean bushDamages();

    protected abstract boolean needsLightToGrow();

    @Override
    public @NonNull ItemStack getCloneItemStack(@NonNull LevelReader world, @NonNull BlockPos pos,
                                                @NonNull BlockState state, boolean includeData) {
        return new ItemStack(this.berryItem());
    }

    @Override
    public @NonNull VoxelShape getShape(BlockState state, @NonNull BlockGetter world,
                                        @NonNull BlockPos pos, @NonNull CollisionContext context) {
        if (state.getValue(AGE) == 0) {
            return SMALL_SHAPE;
        } else {
            return this.isBushYoung(state) ? LARGE_SHAPE : Shapes.block();
        }
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return this.isBushYoung(state);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        int age = state.getValue(AGE);

        if(!this.needsLightToGrow() || random.nextInt(5) == 0 && this.hasLight(world, pos)) {
            BlockState blockState = state.setValue(AGE, age + 1);
            world.setBlock(pos, blockState, 2);
            world.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(blockState));
        }
    }

    @Override
    public void entityInside(@NonNull BlockState state, @NonNull Level world, @NonNull BlockPos pos, Entity entity,
                             @NonNull InsideBlockEffectApplier handler, boolean bl) {
        if (entity.is(this.mobsImmune())) {
            return;
        }

        entity.makeStuckInBlock(state, new Vec3(0.8D, 0.75D, 0.8D));

        if (this.bushDamages() && world instanceof ServerLevel serverWorld && state.getValue(AGE) > 0) {
            Vec3 vec3d = entity.isClientAuthoritative() ? entity.getKnownMovement() : entity.oldPosition().subtract(entity.position());

            if (vec3d.horizontalDistanceSqr() > 0.0) {
                double minMovementForDamage = 0.003D;
                double d = Math.abs(vec3d.x());
                double e = Math.abs(vec3d.z());

                if (d >= minMovementForDamage || e >= minMovementForDamage) {
                    DamageSource sweet_berry_damage_source = world.damageSources().sweetBerryBush();

                    entity.hurtServer(serverWorld, sweet_berry_damage_source, 1.0F);
                }
            }
        }
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit) {
        InteractionHand hand = player.getUsedItemHand();
        boolean isHoldingBoneMeal = player.getItemInHand(hand).is(Items.BONE_MEAL);
        int age = state.getValue(AGE);

        if (this.isMaxAge(age) || age > 1 && !isHoldingBoneMeal) {
            ItemStack berryStack = new ItemStack(this.berryItem(), this.getBushBerryAmount(world, age));

            popResource(world, pos, berryStack);
            world.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS,
                    1.0F, 0.8F + world.getRandom().nextFloat() * 0.4F);
            world.setBlock(pos, state.setValue(AGE, 1), 2);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    public boolean isValidBonemealTarget(@NonNull LevelReader world, @NonNull BlockPos pos, @NonNull BlockState state) {
        return this.isBushYoung(state);
    }

    @Override
    public boolean isBonemealSuccess(@NonNull Level world, @NonNull RandomSource random,
                                     @NonNull BlockPos pos, @NonNull BlockState state) {
        return true;
    }

    public void performBonemeal(ServerLevel world, @NonNull RandomSource random,
                                @NonNull BlockPos pos, BlockState state) {
        int i = Math.min(this.getMaxAge(), state.getValue(AGE) + 1);
        world.setBlock(pos, state.setValue(AGE, i), 2);
    }

    private boolean isMaxAge(int age) {
        return Objects.equals(age, this.getMaxAge());
    }

    private boolean isBushYoung(BlockState state) {
        return state.getValue(AGE) < this.getMaxAge();
    }

    private boolean hasLight(Level world, BlockPos pos) {
        return world.getRawBrightness(pos.above(), 0) >= 9;
    }

    private int getMaxAge() {
        return 3;
    }

    private int getBushBerryAmount(Level world, int age) {
        int amount = 1 + world.getRandom().nextInt(2);

        if(this.isMaxAge(age)) {
            ++amount;
        }
        return amount;
    }

    @Override
    protected boolean isPathfindable(@NonNull BlockState state, @NonNull PathComputationType type) {
        return !this.bushDamages();
    }
}
