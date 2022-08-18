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
import net.medievalweapons.entity.FranciscaLTEntity;
import net.medievalweapons.client.model.FranciscaLtModel;

@Environment(EnvType.CLIENT)
public class FranciscaLTRenderer extends EntityRenderer<FranciscaLTEntity>
{
    private static final Map<EntityType<?>, ResourceLocation> TEXTURES = new HashMap<>();
    private final FranciscaLtModel model = new FranciscaLtModel(FranciscaLtModel.getTexturedModelData().bakeRoot());

    public FranciscaLTRenderer(EntityRendererProvider.Context context)
    {
        super(context);
    }

    @Override
    public void render(FranciscaLTEntity francisca_LT_Entity, float f, float g, PoseStack matrixStack, MultiBufferSource vertexConsumerProvider, int i)
    {
        matrixStack.pushPose();
        VertexConsumer vertexConsumer = ItemRenderer.getFoilBuffer(vertexConsumerProvider, model.renderType(this.getTexture(francisca_LT_Entity)), false, francisca_LT_Entity.enchantingGlint());

        matrixStack.mulPose(Vector3f.YP.rotationDegrees(Mth.lerp(g, francisca_LT_Entity.yRotO, francisca_LT_Entity.getYRot()) - 90.0F));
        matrixStack.mulPose(Vector3f.ZP.rotationDegrees(Mth.lerp(g, francisca_LT_Entity.xRotO, francisca_LT_Entity.getXRot()) + 90.0F));
        matrixStack.translate(0.0D, -0.75D, 0.0D);
        model.renderToBuffer(matrixStack, vertexConsumer, i, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        matrixStack.scale(1.0F, -1.0F, 1.0F);

        matrixStack.popPose();
        super.render(francisca_LT_Entity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public ResourceLocation getTexture(FranciscaLTEntity francisca_LT_Entity)
    {
        return getTexture(francisca_LT_Entity.getType());
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