package net.medievalweapons.client.renderer;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.world.item.ItemStack;

import com.mojang.blaze3d.vertex.PoseStack;
import net.medievalweapons.client.model.MaceModel;

public class MaceRenderer extends MedievalBEWLR<MaceModel>
{
    public MaceRenderer()
    {
        super("mace", MaceModel::new);
    }

    @Override
    public void renderByItem(ItemStack stack, ItemTransforms.TransformType transforms, PoseStack poseStack, MultiBufferSource buffers, int packedLight, int packedOverlay)
    {
        poseStack.pushPose();
        poseStack.translate(-0.1F, 0.86F, 0.0F);
        super.renderByItem(stack, transforms, poseStack, buffers, packedLight, packedOverlay);
        poseStack.popPose();
    }
}