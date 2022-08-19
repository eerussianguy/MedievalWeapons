package net.medievalweapons.client.renderer;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.world.item.ItemStack;

import com.mojang.blaze3d.vertex.PoseStack;
import net.medievalweapons.client.model.LanceModel;

public class LanceRenderer extends MedievalBEWLR<LanceModel>
{
    public LanceRenderer()
    {
        super("lance", LanceModel::new);
    }

    @Override
    public void renderByItem(ItemStack stack, ItemTransforms.TransformType transforms, PoseStack poseStack, MultiBufferSource buffers, int packedLight, int packedOverlay)
    {
        poseStack.pushPose();
        poseStack.translate(-0.7D, 0.27D, 0.0D);
        super.renderByItem(stack, transforms, poseStack, buffers, packedLight, packedOverlay);
        poseStack.popPose();
    }
}