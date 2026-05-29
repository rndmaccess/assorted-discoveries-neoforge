package rndm_access.assorteddiscoveries.client.block_entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.blockentity.state.CampfireRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;
import rndm_access.assorteddiscoveries.block_entity.DyedCampfireBlockEntity;

import java.util.ArrayList;
import java.util.List;

public class DyedCampfireBlockEntityRenderer implements BlockEntityRenderer<DyedCampfireBlockEntity, CampfireRenderState> {
    private static final float SCALE = 0.375F;
    private final ItemModelResolver itemModelResolver;

    public DyedCampfireBlockEntityRenderer(BlockEntityRendererProvider.Context ctx) {
        this.itemModelResolver = ctx.itemModelResolver();
    }

    @Override
    public @NonNull CampfireRenderState createRenderState() {
        return new CampfireRenderState();
    }

    @Override
    public void extractRenderState(@NonNull DyedCampfireBlockEntity dyedCampfireBlockEntity,
                                   @NonNull CampfireRenderState renderState, float f, @NonNull Vec3 vec3,
                                   @Nullable ModelFeatureRenderer.CrumblingOverlay crumblingOverlay) {
        BlockEntityRenderState.extractBase(dyedCampfireBlockEntity, renderState, crumblingOverlay);
        renderState.facing = dyedCampfireBlockEntity.getBlockState().getValue(CampfireBlock.FACING);
        int i = (int) dyedCampfireBlockEntity.getBlockPos().asLong();
        renderState.items = new ArrayList<>();

        for(int j = 0; j < dyedCampfireBlockEntity.getItems().size(); ++j) {
            ItemStackRenderState itemRenderState = new ItemStackRenderState();
            this.itemModelResolver.updateForTopItem(itemRenderState, dyedCampfireBlockEntity.getItems().get(j),
                    ItemDisplayContext.FIXED, dyedCampfireBlockEntity.getLevel(), null, i + j);
            renderState.items.add(itemRenderState);
        }

    }

    @Override
    public void submit(CampfireRenderState renderState, final PoseStack poseStack, final SubmitNodeCollector submitNodeCollector,
                       final CameraRenderState camera) {
        Direction direction = renderState.facing;
        List<ItemStackRenderState> cookedItems = renderState.items;

        for (int i = 0; i < cookedItems.size(); ++i) {
            ItemStackRenderState itemRenderState = cookedItems.get(i);
            if (!itemRenderState.isEmpty()) {
                poseStack.pushPose();
                poseStack.translate(0.5F, 0.44921875F, 0.5F);
                Direction direction2 = Direction.from2DDataValue((i + direction.get2DDataValue()) % 4);
                float f = -direction2.toYRot();
                poseStack.mulPose(Axis.YP.rotationDegrees(f));
                poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
                poseStack.translate(-0.3125F, -0.3125F, 0.0F);
                poseStack.scale(SCALE, SCALE, SCALE);
                itemRenderState.submit(poseStack, submitNodeCollector, renderState.lightCoords,
                        OverlayTexture.NO_OVERLAY, 0);
                poseStack.popPose();
            }
        }
    }
}
