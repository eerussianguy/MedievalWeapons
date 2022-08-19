package net.medievalweapons.client.renderer;

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
import net.dries007.tfc.common.entities.ThrownJavelin;
import net.medievalweapons.client.ClientEvents;
import net.medievalweapons.client.model.MedievalJavelinModel;

public class MedievalJavelinRenderer extends EntityRenderer<ThrownJavelin>
{
    private static final Map<EntityType<?>, ResourceLocation> TEXTURES = new HashMap<>();
    private final MedievalJavelinModel model;

    public MedievalJavelinRenderer(EntityRendererProvider.Context context)
    {
        super(context);
        this.model = new MedievalJavelinModel(context.bakeLayer(ClientEvents.modelIdentifier("javelin")));
    }

    @Override
    public void render(ThrownJavelin javelin, float f, float g, PoseStack poseStack, MultiBufferSource vertexConsumerProvider, int i)
    {
        poseStack.pushPose();
        VertexConsumer vertexConsumer = ItemRenderer.getFoilBuffer(vertexConsumerProvider, model.renderType(this.getTextureLocation(javelin)), false, javelin.isEnchantGlowing());

        poseStack.mulPose(Vector3f.YP.rotationDegrees(Mth.lerp(g, javelin.yRotO, javelin.getYRot()) - 90.0F));
        poseStack.mulPose(Vector3f.ZP.rotationDegrees(Mth.lerp(g, javelin.xRotO, javelin.getXRot()) + 90.0F));

        model.renderToBuffer(poseStack, vertexConsumer, i, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        poseStack.scale(1.0F, -1.0F, 1.0F);
        poseStack.translate(0.0D, -4.0D, 0.0D);
        poseStack.popPose();
        super.render(javelin, f, g, poseStack, vertexConsumerProvider, i);
    }

    @Override
    public ResourceLocation getTextureLocation(ThrownJavelin javelin_Entity)
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