package net.medievalweapons.client.renderer;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.medievalweapons.client.model.HealingStaffModel;

@Environment(EnvType.CLIENT)
public enum HealingStaffRenderer
{
    INSTANCE;

    private final HealingStaffModel healing_Staff_Entity_Model = new HealingStaffModel(HealingStaffModel.getTexturedModelData().bakeRoot());

    public boolean render(LivingEntity entity, ItemStack stack, ItemTransforms.TransformType renderMode, boolean leftHanded, PoseStack matrices, MultiBufferSource vertexConsumers, int light,
                          int overlay, BakedModel model)
    {
        if (renderMode == ItemTransforms.TransformType.GUI || renderMode == ItemTransforms.TransformType.GROUND || renderMode == ItemTransforms.TransformType.FIXED)
        {
            return false;
        }

        matrices.pushPose();
        model.getTransforms().getTransform(renderMode).apply(leftHanded, matrices);
        matrices.translate(-0.7D, 0.27D, 0.0D);
        matrices.scale(1.0F, -1.0F, -1.0F);
        VertexConsumer vertexConsumer = ItemRenderer.getFoilBuffer(vertexConsumers,
            this.healing_Staff_Entity_Model.renderType(new ResourceLocation("medievalweapons", "textures/entity/" + stack.getItem() + ".png")), false, stack.hasFoil());
        this.healing_Staff_Entity_Model.renderToBuffer(matrices, vertexConsumer, light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);
        matrices.popPose();
        return true;
    }
}