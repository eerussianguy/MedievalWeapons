package net.medievalweapons.client.renderer;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;

import net.minecraftforge.registries.ForgeRegistries;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.medievalweapons.MUtil;
import net.medievalweapons.client.ClientEvents;
import net.medievalweapons.client.model.FranciscaLTModel;
import net.medievalweapons.entity.FranciscaHTEntity;
import net.medievalweapons.client.model.FranciscaHTModel;
import net.medievalweapons.entity.ThrownFrancisca;

public class FranciscaRenderer extends EntityRenderer<ThrownFrancisca>
{
    private static final Map<EntityType<?>, ResourceLocation> TEXTURES = new HashMap<>();
    private final Model model;

    public FranciscaRenderer(EntityRendererProvider.Context context, boolean ht)
    {
        super(context);
        this.model = ht ? new FranciscaHTModel(context.bakeLayer(ClientEvents.modelIdentifier("francisca_ht"))) : new FranciscaLTModel(context.bakeLayer(ClientEvents.modelIdentifier("francisca_lt")));
    }

    @Override
    public void render(FranciscaHTEntity entity, float yaw, float partialTicks, PoseStack matrixStack, MultiBufferSource vertexConsumerProvider, int i)
    {
        matrixStack.pushPose();
        matrixStack.mulPose(Vector3f.YP.rotationDegrees(Mth.lerp(partialTicks, entity.yRotO, entity.getYRot()) - 90.0F));
        matrixStack.mulPose(Vector3f.ZP.rotationDegrees(Mth.lerp(partialTicks, entity.xRotO, entity.getXRot()) + 90.0F));
        VertexConsumer vertexConsumer = ItemRenderer.getFoilBuffer(vertexConsumerProvider, model.renderType(this.getTextureLocation(entity)), false, entity.enchantingGlint());

        matrixStack.translate(0.0D, -0.75D, 0.0D);
        model.renderToBuffer(matrixStack, vertexConsumer, i, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        matrixStack.scale(1.0F, -1.0F, 1.0F);
        matrixStack.popPose();
        super.render(entity, yaw, partialTicks, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public ResourceLocation getTextureLocation(FranciscaHTEntity entity)
    {
        return entity.getst
        return getTexture(entity.getType());
    }

    public static ResourceLocation getTexture(EntityType<?> type)
    {
        if (!TEXTURES.containsKey(type))
        {
            TEXTURES.put(type, MUtil.identifier("textures/entity/" + ForgeRegistries.ENTITIES.getKey(type).getPath() + ".png"));
        }
        return TEXTURES.get(type);
    }
}