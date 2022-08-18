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
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.medievalweapons.entity.FranciscaHTEntity;
import net.medievalweapons.client.model.FranciscaHTModel;

@Environment(EnvType.CLIENT)
public class FranciscaHTRenderer extends EntityRenderer<FranciscaHTEntity>
{
    private static final Map<EntityType<?>, ResourceLocation> TEXTURES = new HashMap<>();
    private final FranciscaHTModel model = new FranciscaHTModel(FranciscaHTModel.getTexturedModelData().bakeRoot());

    public FranciscaHTRenderer(EntityRendererProvider.Context context)
    {
        super(context);
    }

    @Override
    public void render(FranciscaHTEntity francisca_HT_Entity, float f, float g, PoseStack matrixStack, MultiBufferSource vertexConsumerProvider, int i)
    {
        matrixStack.pushPose();
        matrixStack.mulPose(Vector3f.YP.rotationDegrees(Mth.lerp(g, francisca_HT_Entity.yRotO, francisca_HT_Entity.getYRot()) - 90.0F));
        matrixStack.mulPose(Vector3f.ZP.rotationDegrees(Mth.lerp(g, francisca_HT_Entity.xRotO, francisca_HT_Entity.getXRot()) + 90.0F));
        VertexConsumer vertexConsumer = ItemRenderer.getFoilBuffer(vertexConsumerProvider, model.renderType(this.getTexture(francisca_HT_Entity)), false, francisca_HT_Entity.enchantingGlint());

        matrixStack.translate(0.0D, -0.75D, 0.0D);
        model.renderToBuffer(matrixStack, vertexConsumer, i, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        matrixStack.scale(1.0F, -1.0F, 1.0F);
        matrixStack.popPose();
        super.render(francisca_HT_Entity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public ResourceLocation getTexture(FranciscaHTEntity francisca_HT_Entity)
    {
        return getTexture(francisca_HT_Entity.getType());
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