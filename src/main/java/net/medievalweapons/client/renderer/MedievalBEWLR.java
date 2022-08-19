package net.medievalweapons.client.renderer;

import java.util.function.Function;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.item.ItemStack;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.medievalweapons.client.ClientEvents;

public abstract class MedievalBEWLR<T extends Model> extends BlockEntityWithoutLevelRenderer
{
    private final T model;

    public MedievalBEWLR(String name, Function<ModelPart, T> maker)
    {
        super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
        model = maker.apply((Minecraft.getInstance().getEntityModels().bakeLayer(ClientEvents.modelIdentifier(name))));
    }

    @Override
    public void renderByItem(ItemStack stack, ItemTransforms.TransformType transforms, PoseStack poseStack, MultiBufferSource buffers, int packedLight, int packedOverlay)
    {
        poseStack.scale(1.0F, -1.0F, -1.0F);
        VertexConsumer consumer = ItemRenderer.getFoilBuffer(buffers, this.model.renderType(ClientEvents.texture(stack)), false, stack.hasFoil());
        this.model.renderToBuffer(poseStack, consumer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
    }
}
