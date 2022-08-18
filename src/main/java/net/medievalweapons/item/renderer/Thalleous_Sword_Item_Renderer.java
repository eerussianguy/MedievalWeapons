package net.medievalweapons.item.renderer;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.medievalweapons.entity.model.Thalleous_Sword_Entity_Model;

@Environment(EnvType.CLIENT)
public enum Thalleous_Sword_Item_Renderer
{
    INSTANCE;

    private final Thalleous_Sword_Entity_Model thalleous_Sword_Entity_Model = new Thalleous_Sword_Entity_Model(Thalleous_Sword_Entity_Model.getTexturedModelData().bakeRoot());

    public boolean render(LivingEntity entity, ItemStack stack, ItemTransforms.TransformType renderMode, boolean leftHanded, PoseStack matrices, MultiBufferSource vertexConsumers, int light,
                          int overlay, BakedModel model)
    {
        if (renderMode == ItemTransforms.TransformType.GUI || renderMode == ItemTransforms.TransformType.GROUND || renderMode == ItemTransforms.TransformType.FIXED)
        {
            return false;
        }

        matrices.pushPose();
        model.getTransforms().getTransform(renderMode).apply(leftHanded, matrices);
        if (entity != null)
        {
            if ((renderMode == ItemTransforms.TransformType.FIRST_PERSON_LEFT_HAND || renderMode == ItemTransforms.TransformType.FIRST_PERSON_RIGHT_HAND) && entity.isBlocking())
            {
                matrices.mulPose(Vector3f.ZP.rotationDegrees(30.0F));
                matrices.mulPose(Vector3f.YP.rotationDegrees(leftHanded ? 20.0F : -20.0F));
            }
        }
        matrices.translate(-0.05D, 0.84D, 0.0D);
        matrices.scale(1.0F, -1.0F, -1.0F);
        VertexConsumer vertexConsumer = ItemRenderer.getFoilBuffer(vertexConsumers,
            this.thalleous_Sword_Entity_Model.renderType(new ResourceLocation("medievalweapons", "textures/entity/thalleous_sword.png")), false, stack.hasFoil());
        this.thalleous_Sword_Entity_Model.renderToBuffer(matrices, vertexConsumer, light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);
        matrices.popPose();
        return true;
    }
}