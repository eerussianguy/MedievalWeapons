package net.medievalweapons.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import org.spongepowered.asm.mixin.injection.At;

import net.medievalweapons.item.Long_Bow_Item;
import net.medievalweapons.item.Recurve_Bow_Item;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.level.Level;

@Mixin(BowItem.class)
public abstract class BowItemMixin extends ProjectileWeaponItem {

    public BowItemMixin(Properties settings) {
        super(settings);
    }

    @Inject(method = "onStoppedUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/projectile/PersistentProjectileEntity;setVelocity(Lnet/minecraft/entity/Entity;FFFFF)V", shift = Shift.AFTER), locals = LocalCapture.CAPTURE_FAILSOFT)
    private void onStoppedUsingMixin(ItemStack stack, Level world, LivingEntity user, int remainingUseTicks, CallbackInfo info, Player playerEntity, boolean bl, ItemStack itemStack, int i,
            float f, boolean bl2, ArrowItem arrowItem, AbstractArrow persistentProjectileEntity) {
        if ((Object) this instanceof Long_Bow_Item) {
            persistentProjectileEntity.shootFromRotation(playerEntity, playerEntity.getXRot(), playerEntity.getYRot(), 0.0F, f * 3.7F, 1.0F);
        } else if ((Object) this instanceof Recurve_Bow_Item) {
            persistentProjectileEntity.shootFromRotation(playerEntity, playerEntity.getXRot(), playerEntity.getYRot(), 0.0F, f * 2.6F, 1.0F);
        }
    }

    @ModifyVariable(method = "onStoppedUsing", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/item/BowItem;getPullProgress(I)F"), ordinal = 0)
    private float onStoppedUsingPullProgressMixin(float original, ItemStack stack, Level world, LivingEntity user, int remainingUseTicks) {
        if ((Object) this instanceof Long_Bow_Item) {
            float f = (float) (this.getUseDuration(stack) - remainingUseTicks) / 50.0F;
            f = (f * f + f * 2.0F) / 3.0F;
            if (f > 1.0F) {
                f = 1.0F;
            }
            return f;
        } else if ((Object) this instanceof Recurve_Bow_Item) {
            float f = (float) (this.getUseDuration(stack) - remainingUseTicks) / 16.0F;
            f = (f * f + f * 2.0F / 3.0F);
            if (f > 1.0F) {
                f = 1.0F;
            }
            return f;
        }
        return original;
    }

}
