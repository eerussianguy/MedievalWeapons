package net.medievalweapons.item.renderer;

import net.fabricmc.api.Environment;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.fabricmc.api.EnvType;
import net.medievalweapons.entity.model.Big_Axe_Entity_Model;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

@Environment(EnvType.CLIENT)
public enum Big_Axe_Item_Renderer {
    INSTANCE;

    private final Big_Axe_Entity_Model big_Axe_Entity_Model = new Big_Axe_Entity_Model(Big_Axe_Entity_Model.getTexturedModelData().bakeRoot());

    public boolean render(LivingEntity entity, ItemStack stack, ItemTransforms.TransformType renderMode, boolean leftHanded, PoseStack matrices, MultiBufferSource vertexConsumers, int light,
            int overlay, BakedModel model) {
        if (renderMode == ItemTransforms.TransformType.GUI || renderMode == ItemTransforms.TransformType.GROUND || renderMode == ItemTransforms.TransformType.FIXED) {
            return false;
        }

        matrices.pushPose();

        model.getTransforms().getTransform(renderMode).apply(leftHanded, matrices);
        if (entity != null && !entity.getOffhandItem().isEmpty()) {
            matrices.mulPose(Vector3f.ZP.rotationDegrees(-90.0F));
            matrices.mulPose(Vector3f.YP.rotationDegrees(-90.0F));
            matrices.translate(-0.25D, 0.75D, 0.0D);
        } else {
            if (renderMode == ItemTransforms.TransformType.FIRST_PERSON_LEFT_HAND || renderMode == ItemTransforms.TransformType.FIRST_PERSON_RIGHT_HAND) {
                matrices.mulPose(Vector3f.ZP.rotationDegrees(-110.0F));
                matrices.mulPose(Vector3f.YP.rotationDegrees(-20.0F));
                matrices.mulPose(Vector3f.XP.rotationDegrees(leftHanded ? 30.0F : 50.0F));
                matrices.translate(leftHanded ? -0.2D : -0.1D, leftHanded ? 0.0D : 1.6D, leftHanded ? 0.0D : 0.3D);
                matrices.mulPose(Vector3f.ZP.rotationDegrees(10));
                if (entity != null && entity.isBlocking()) {
                    matrices.mulPose(Vector3f.YP.rotationDegrees(-15F));
                }
            } else {
                matrices.mulPose(Vector3f.ZP.rotationDegrees(leftHanded ? 200.0F : 180.0F));
                matrices.mulPose(Vector3f.YP.rotationDegrees(-30.0F));
                matrices.mulPose(Vector3f.XP.rotationDegrees(leftHanded ? 60.0F : 50.0F));
                matrices.translate(0.0D, leftHanded ? 0.5D : 1.3D, leftHanded ? -0.3D : 0.3D);
            }
        }
        matrices.scale(1.0F, -1.0F, -1.0F);
        VertexConsumer vertexConsumer = ItemRenderer.getFoilBuffer(vertexConsumers,
                this.big_Axe_Entity_Model.renderType(new ResourceLocation("medievalweapons", "textures/entity/" + stack.getItem() + ".png")), false, stack.hasFoil());
        this.big_Axe_Entity_Model.renderToBuffer(matrices, vertexConsumer, light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);
        matrices.popPose();
        return true;
    }
}