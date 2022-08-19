package net.medievalweapons.client.renderer;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.world.item.ItemStack;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.dries007.tfc.client.ClientHelpers;
import net.medievalweapons.client.model.ThalleousSwordModel;

public class ThalleousSwordRenderer extends MedievalBEWLR<ThalleousSwordModel>
{
    public ThalleousSwordRenderer()
    {
        super("thalleous_sword", ThalleousSwordModel::new);
    }

    @Override
    public void renderByItem(ItemStack stack, ItemTransforms.TransformType transforms, PoseStack poseStack, MultiBufferSource buffers, int packedLight, int packedOverlay)
    {
        poseStack.pushPose();
        if (ClientHelpers.getPlayer() != null)
        {
            if ((transforms == ItemTransforms.TransformType.FIRST_PERSON_LEFT_HAND || transforms == ItemTransforms.TransformType.FIRST_PERSON_RIGHT_HAND) && ClientHelpers.getPlayer().isBlocking())
            {
                poseStack.mulPose(Vector3f.ZP.rotationDegrees(30.0F));
                poseStack.mulPose(Vector3f.YP.rotationDegrees(-20.0F));
            }
        }
        poseStack.translate(-0.05D, 0.84D, 0.0D);
        super.renderByItem(stack, transforms, poseStack, buffers, packedLight, packedOverlay);
        poseStack.popPose();
    }
}