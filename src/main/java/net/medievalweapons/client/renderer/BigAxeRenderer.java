package net.medievalweapons.client.renderer;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.dries007.tfc.client.ClientHelpers;
import net.medievalweapons.client.model.BigAxeModel;

public class BigAxeRenderer extends MedievalBEWLR<BigAxeModel>
{
    public BigAxeRenderer()
    {
        super("big_axe", BigAxeModel::new);
    }

    @Override
    public void renderByItem(ItemStack stack, ItemTransforms.TransformType transforms, PoseStack poseStack, MultiBufferSource buffers, int packedLight, int packedOverlay)
    {
        Player entity = ClientHelpers.getPlayer();
        poseStack.pushPose();
        if (entity != null && !entity.getOffhandItem().isEmpty())
        {
            poseStack.mulPose(Vector3f.ZP.rotationDegrees(-90.0F));
            poseStack.mulPose(Vector3f.YP.rotationDegrees(-90.0F));
            poseStack.translate(-0.25D, 0.75D, 0.0D);
        }
        else
        {
            if (transforms == ItemTransforms.TransformType.FIRST_PERSON_LEFT_HAND || transforms == ItemTransforms.TransformType.FIRST_PERSON_RIGHT_HAND)
            {
                poseStack.mulPose(Vector3f.ZP.rotationDegrees(-110.0F));
                poseStack.mulPose(Vector3f.YP.rotationDegrees(-20.0F));
                poseStack.mulPose(Vector3f.XP.rotationDegrees( 50.0F));
                poseStack.translate(-0.1D, 1.6D,  0.3D);
                poseStack.mulPose(Vector3f.ZP.rotationDegrees(10));
                if (entity != null && entity.isBlocking())
                {
                    poseStack.mulPose(Vector3f.YP.rotationDegrees(-15F));
                }
            }
            else
            {
                poseStack.mulPose(Vector3f.ZP.rotationDegrees(180.0F));
                poseStack.mulPose(Vector3f.YP.rotationDegrees(-30.0F));
                poseStack.mulPose(Vector3f.XP.rotationDegrees(50.0F));
                poseStack.translate(0.0D, 1.3D, 0.3D);
            }
        }

        super.renderByItem(stack, transforms, poseStack, buffers, packedLight, packedOverlay);
        poseStack.popPose();
    }
}