package net.medievalweapons.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.fabricmc.api.Environment;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.fabricmc.api.EnvType;
import net.medievalweapons.init.TagInit;
import net.medievalweapons.item.Dagger_Item;
import net.medievalweapons.item.Long_Sword_Item;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

@Environment(EnvType.CLIENT)
@Mixin(ItemInHandLayer.class)
public abstract class HeldItemFeatureRendererMixin {

    @Inject(method = "Lnet/minecraft/client/render/entity/feature/HeldItemFeatureRenderer;renderItem(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformation$Mode;Lnet/minecraft/util/Arm;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", at = @At(value = "HEAD"))
    protected void renderItemMixin(LivingEntity entity, ItemStack stack, ItemTransforms.TransformType transformationMode, HumanoidArm arm, PoseStack matrices, MultiBufferSource vertexConsumers, int light,
            CallbackInfo info) {
        if ((stack.is(TagInit.DOUBLE_HANDED_ITEMS) || stack.getItem() instanceof Long_Sword_Item) && entity.getOffhandItem().isEmpty() && !entity.isSwimming() && !entity.isPassenger()) {
            matrices.mulPose(Vector3f.YP.rotationDegrees(arm == HumanoidArm.LEFT ? -50.0F : 30.0F));
            matrices.translate(arm == HumanoidArm.LEFT ? -0.4D : 0.24D, arm == HumanoidArm.LEFT ? 0.1D : 0.0D, arm == HumanoidArm.LEFT ? 0.1D : 0.0D);
            if (arm == HumanoidArm.LEFT && entity.isBlocking()) {
                matrices.mulPose(Vector3f.XP.rotationDegrees(30.0F));
            }
        }
    }

    @Inject(method = "Lnet/minecraft/client/render/entity/feature/HeldItemFeatureRenderer;renderItem(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformation$Mode;Lnet/minecraft/util/Arm;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;multiply(Lnet/minecraft/util/math/Quaternion;)V"))
    protected void renderItemMixinTwo(LivingEntity entity, ItemStack stack, ItemTransforms.TransformType transformationMode, HumanoidArm arm, PoseStack matrices, MultiBufferSource vertexConsumers,
            int light, CallbackInfo info) {
        if (stack.getItem() instanceof Dagger_Item)
            matrices.mulPose(Vector3f.YN.rotationDegrees(180.0F));
    }

    @Inject(method = "Lnet/minecraft/client/render/entity/feature/HeldItemFeatureRenderer;renderItem(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformation$Mode;Lnet/minecraft/util/Arm;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;translate(DDD)V"))
    protected void renderItemMixinThree(LivingEntity entity, ItemStack stack, ItemTransforms.TransformType transformationMode, HumanoidArm arm, PoseStack matrices, MultiBufferSource vertexConsumers,
            int light, CallbackInfo info) {
        if (stack.getItem() instanceof Dagger_Item)
            matrices.translate(0.0D, 0.1D, 1.0D);
    }

}