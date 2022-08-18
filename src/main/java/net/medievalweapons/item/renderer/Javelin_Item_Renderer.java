package net.medievalweapons.item.renderer;

import net.fabricmc.api.Environment;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.fabricmc.api.EnvType;
import net.medievalweapons.entity.model.Javelin_Entity_Model;
import net.medievalweapons.entity.renderer.Javelin_Entity_Renderer;
import net.medievalweapons.item.Javelin_Item;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

@Environment(EnvType.CLIENT)
public enum Javelin_Item_Renderer {
    INSTANCE;

    private final Javelin_Entity_Model javelinEntityModel = new Javelin_Entity_Model(Javelin_Entity_Model.getTexturedModelData().bakeRoot());

    public boolean render(LivingEntity entity, ItemStack stack, ItemTransforms.TransformType renderMode, boolean leftHanded, PoseStack matrices, MultiBufferSource vertexConsumers, int light,
            int overlay, BakedModel model) {
        if (renderMode == ItemTransforms.TransformType.GUI || renderMode == ItemTransforms.TransformType.GROUND || renderMode == ItemTransforms.TransformType.FIXED) {
            return false;
        }

        matrices.pushPose();

        model.getTransforms().getTransform(renderMode).apply(leftHanded, matrices);

        if (entity != null && entity.isUsingItem() && entity.getUseItem() == stack
                && (renderMode == ItemTransforms.TransformType.THIRD_PERSON_LEFT_HAND || renderMode == ItemTransforms.TransformType.THIRD_PERSON_RIGHT_HAND)) {
            matrices.mulPose(Vector3f.ZP.rotationDegrees(120F));
            matrices.translate(0.0D, 0.7D, 0.0D);
        } else if (renderMode != ItemTransforms.TransformType.FIRST_PERSON_LEFT_HAND && renderMode != ItemTransforms.TransformType.FIRST_PERSON_RIGHT_HAND) {
            matrices.mulPose(Vector3f.ZP.rotationDegrees(-60F));
            matrices.translate(0.0D, 0.85D, 0.0D);
        } else {
            matrices.translate(0.0D, 0.85D, 0.0D);
        }

        matrices.scale(1.0F, -1.0F, -1.0F);
        VertexConsumer vertexConsumer = ItemRenderer.getFoilBuffer(vertexConsumers,
                this.javelinEntityModel.renderType(Javelin_Entity_Renderer.getTexture(((Javelin_Item) stack.getItem()).getType())), false, stack.hasFoil());
        this.javelinEntityModel.renderToBuffer(matrices, vertexConsumer, light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);
        matrices.popPose();
        return true;
    }
}