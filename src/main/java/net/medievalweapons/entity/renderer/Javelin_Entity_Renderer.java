package net.medievalweapons.entity.renderer;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.medievalweapons.entity.Javelin_Entity;
import net.medievalweapons.entity.model.Javelin_Entity_Model;

@Environment(EnvType.CLIENT)
public class Javelin_Entity_Renderer extends EntityRenderer<Javelin_Entity>
{
    private static final Map<EntityType<?>, ResourceLocation> TEXTURES = new HashMap<>();
    private final Javelin_Entity_Model model = new Javelin_Entity_Model(Javelin_Entity_Model.getTexturedModelData().bakeRoot());

    public Javelin_Entity_Renderer(EntityRendererProvider.Context context)
    {
        super(context);
    }

    @Override
    public void render(Javelin_Entity javelin_Entity, float f, float g, PoseStack matrixStack, MultiBufferSource vertexConsumerProvider, int i)
    {
        matrixStack.pushPose();
        VertexConsumer vertexConsumer = ItemRenderer.getFoilBuffer(vertexConsumerProvider, model.renderType(this.getTexture(javelin_Entity)), false, javelin_Entity.enchantingGlint());

        matrixStack.mulPose(Vector3f.YP.rotationDegrees(Mth.lerp(g, javelin_Entity.yRotO, javelin_Entity.getYRot()) - 90.0F));
        matrixStack.mulPose(Vector3f.ZP.rotationDegrees(Mth.lerp(g, javelin_Entity.xRotO, javelin_Entity.getXRot()) + 90.0F));

        model.renderToBuffer(matrixStack, vertexConsumer, i, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        matrixStack.scale(1.0F, -1.0F, 1.0F);
        matrixStack.translate(0.0D, -4.0D, 0.0D);
        matrixStack.popPose();
        super.render(javelin_Entity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public ResourceLocation getTexture(Javelin_Entity javelin_Entity)
    {
        return getTexture(javelin_Entity.getType());
    }

    public static ResourceLocation getTexture(EntityType<?> type)
    {
        if (!TEXTURES.containsKey(type))
        {
            TEXTURES.put(type, new ResourceLocation("medievalweapons", "textures/entity/" + Registry.ENTITY_TYPE.getKey(type).getPath() + ".png"));
        }
        return TEXTURES.get(type);
    }
}