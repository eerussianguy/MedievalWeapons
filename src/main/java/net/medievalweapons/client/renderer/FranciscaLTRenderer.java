package net.medievalweapons.client.renderer;

import java.util.HashMap;
import java.util.Map;

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
import net.medievalweapons.client.ClientEvents;
import net.medievalweapons.entity.FranciscaLTEntity;
import net.medievalweapons.client.model.FranciscaLtModel;

public class FranciscaLTRenderer extends EntityRenderer<FranciscaLTEntity>
{
    private static final Map<EntityType<?>, ResourceLocation> TEXTURES = new HashMap<>();
    private final FranciscaLtModel model;

    public FranciscaLTRenderer(EntityRendererProvider.Context context)
    {
        super(context);
        this.model = new FranciscaLtModel(context.bakeLayer(ClientEvents.modelIdentifier("francisca_lt")));
    }

    @Override
    public void render(FranciscaLTEntity entity, float f, float g, PoseStack matrixStack, MultiBufferSource vertexConsumerProvider, int i)
    {
        matrixStack.pushPose();
        VertexConsumer vertexConsumer = ItemRenderer.getFoilBuffer(vertexConsumerProvider, model.renderType(this.getTextureLocation(entity)), false, entity.enchantingGlint());

        matrixStack.mulPose(Vector3f.YP.rotationDegrees(Mth.lerp(g, entity.yRotO, entity.getYRot()) - 90.0F));
        matrixStack.mulPose(Vector3f.ZP.rotationDegrees(Mth.lerp(g, entity.xRotO, entity.getXRot()) + 90.0F));
        matrixStack.translate(0.0D, -0.75D, 0.0D);
        model.renderToBuffer(matrixStack, vertexConsumer, i, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        matrixStack.scale(1.0F, -1.0F, 1.0F);

        matrixStack.popPose();
        super.render(entity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public ResourceLocation getTextureLocation(FranciscaLTEntity francisca_LT_Entity)
    {
        return getTexture(francisca_LT_Entity.getType());
    }

    public static ResourceLocation getTexture(EntityType<?> type)
    {
        if (!TEXTURES.containsKey(type))
        {
            TEXTURES.put(type, new ResourceLocation("medievalweapons", "textures/entity/" + ForgeRegistries.ENTITIES.getKey(type).getPath() + ".png"));
        }
        return TEXTURES.get(type);
    }
}