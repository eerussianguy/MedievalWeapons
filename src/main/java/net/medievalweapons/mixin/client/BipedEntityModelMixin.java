package net.medievalweapons.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import net.medievalweapons.init.TagInit;
import net.medievalweapons.item.Big_Axe_Item;
import net.medievalweapons.item.Long_Sword_Item;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;

@Environment(EnvType.CLIENT)
@Mixin(HumanoidModel.class)
public abstract class BipedEntityModelMixin<T extends LivingEntity> extends AgeableListModel<T> implements ArmedModel, HeadedModel {
    @Shadow
    public ModelPart rightArm;
    @Shadow
    public ModelPart leftArm;
    @Shadow
    public ModelPart head;

    @Inject(method = "setAngles", at = @At(value = "TAIL"))
    private void setAnglesMixin(T livingEntity, float f, float g, float h, float i, float j, CallbackInfo info) {
        if (livingEntity.getOffhandItem().isEmpty() && !livingEntity.isSwimming() && !livingEntity.isPassenger()) {
            if (livingEntity.getMainHandItem().is(TagInit.DOUBLE_HANDED_ITEMS) || livingEntity.getMainHandItem().getItem() instanceof Long_Sword_Item) {
                this.rightArm.xRot = -0.8727F + (Mth.cos(f * 0.6662F) * 2.0F * g * 0.5F / 15);
                this.rightArm.yRot = -0.5672F;
                this.rightArm.zRot = 0.0F;
                this.leftArm.xRot = -1.0472F + (Mth.cos(f * 0.6662F) * 2.0F * g * 0.5F / 15);
                this.leftArm.yRot = 0.829F;
                this.leftArm.zRot = -0.0436F;
                if (this.attackTime > 0) {
                    float gx = 1.0F - this.attackTime;
                    float hx = Mth.sin(gx * 3.1415927F);
                    float kx = this.head.xRot;
                    if (kx < 0) {
                        kx = 0.25F;
                    }
                    float ix = Mth.sin(this.attackTime * 3.1415927F) * -((kx) - 0.7F) * 0.75F * 0.6F;
                    this.rightArm.xRot = (float) ((double) this.rightArm.xRot - ((double) hx * 1.2D + (double) ix));
                    this.leftArm.xRot = (float) ((double) this.leftArm.xRot - ((double) hx * 1.2D + (double) ix) * 1.2D) * 0.75F;
                }

                if (livingEntity.isBlocking()) {
                    this.rightArm.xRot = -1.25F;
                    this.leftArm.xRot = -1.17F;
                    this.rightArm.zRot = 0.7F;
                }
            } else if (livingEntity.getMainHandItem().is(TagInit.ACCROSS_DOUBLE_HANDED_ITEMS) || livingEntity.getMainHandItem().getItem() instanceof Big_Axe_Item) {
                this.rightArm.xRot = -0.5236F + (Mth.cos(f * 0.6662F) * 2.0F * g * 0.5F / 15);
                this.rightArm.yRot = 0.0F;
                this.rightArm.zRot = 0.0F;
                this.leftArm.xRot = -1.2217F + (Mth.cos(f * 0.6662F) * 2.0F * g * 0.5F / 15);
                this.leftArm.yRot = 0.0F;
                this.leftArm.zRot = 0.0F;
                if (this.attackTime > 0) {
                    float gx = 1.0F - this.attackTime;
                    float hx = Mth.sin(gx * 3.1415927F);
                    float kx = this.head.xRot;
                    if (kx < 0) {
                        kx = 0.25F;
                    }
                    float ix = Mth.sin(this.attackTime * 3.1415927F) * -((kx) - 0.7F) * 0.75F * 0.6F;
                    this.rightArm.xRot = (float) ((double) this.rightArm.xRot - ((double) hx * 1.2D + (double) ix));
                    this.leftArm.xRot = (float) ((double) this.leftArm.xRot - ((double) hx * 1.2D + (double) ix) * 1.2D) * 0.75F * 1.2F;
                }
                if (livingEntity.isBlocking()) {
                    this.rightArm.xRot = -1.0234747F;
                    this.leftArm.xRot = -1.6393949F;
                }
            }
        }

    }

}