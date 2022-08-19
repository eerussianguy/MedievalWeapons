package net.medievalweapons.mixin.client;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.medievalweapons.init.TagInit;
import net.medievalweapons.item.DaggerItem;
import net.medievalweapons.item.LongSwordItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemInHandLayer.class)
public abstract class ItemInHandLayerMixin
{

    @Inject(method = "renderArmWithItem", at = @At(value = "HEAD"))
    protected void renderItemMixin(LivingEntity entity, ItemStack stack, ItemTransforms.TransformType transformationMode, HumanoidArm arm, PoseStack matrices, MultiBufferSource vertexConsumers, int light, CallbackInfo info)
    {
        if ((stack.is(TagInit.DOUBLE_HANDED_ITEMS) || stack.getItem() instanceof LongSwordItem) && entity.getOffhandItem().isEmpty() && !entity.isSwimming() && !entity.isPassenger())
        {
            matrices.mulPose(Vector3f.YP.rotationDegrees(arm == HumanoidArm.LEFT ? -50.0F : 30.0F));
            matrices.translate(arm == HumanoidArm.LEFT ? -0.4D : 0.24D, arm == HumanoidArm.LEFT ? 0.1D : 0.0D, arm == HumanoidArm.LEFT ? 0.1D : 0.0D);
            if (arm == HumanoidArm.LEFT && entity.isBlocking())
            {
                matrices.mulPose(Vector3f.XP.rotationDegrees(30.0F));
            }
        }
    }

    @Inject(method = "renderArmWithItem", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;mulPose(Lcom/mojang/math/Quaternion;)V"))
    protected void renderItemMixinTwo(LivingEntity entity, ItemStack stack, ItemTransforms.TransformType transformationMode, HumanoidArm arm, PoseStack matrices, MultiBufferSource vertexConsumers, int light, CallbackInfo info)
    {
        if (stack.getItem() instanceof DaggerItem)
            matrices.mulPose(Vector3f.YN.rotationDegrees(90.0F)); // 180
    }

    @Inject(method = "renderArmWithItem", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;translate(DDD)V"))
    protected void renderItemMixinThree(LivingEntity entity, ItemStack stack, ItemTransforms.TransformType transformationMode, HumanoidArm arm, PoseStack matrices, MultiBufferSource vertexConsumers, int light, CallbackInfo info)
    {
        if (stack.getItem() instanceof DaggerItem)
        {
            matrices.translate(0.0D, 0.1D, 1.0D);
        }
    }

}