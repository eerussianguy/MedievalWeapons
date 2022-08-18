package net.medievalweapons.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.At;

import net.medievalweapons.init.ConfigInit;
import net.medievalweapons.init.TagInit;
import net.medievalweapons.item.Big_Axe_Item;
import net.medievalweapons.item.Lance_Item;
import net.medievalweapons.item.Long_Sword_Item;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

    public LivingEntityMixin(EntityType<?> type, Level world) {
        super(type, world);
    }

    @Inject(method = "Lnet/minecraft/entity/LivingEntity;getHandSwingDuration()I", at = @At("HEAD"), cancellable = true)
    private void getHandSwingDuration(CallbackInfoReturnable<Integer> info) {
        LivingEntity livingEntity = (LivingEntity) (Object) this;
        ItemStack itemStack = livingEntity.getMainHandItem();
        if (itemStack.is(TagInit.ACCROSS_DOUBLE_HANDED_ITEMS) || itemStack.is(TagInit.DOUBLE_HANDED_ITEMS) || itemStack.getItem() instanceof Long_Sword_Item
                || itemStack.getItem() instanceof Big_Axe_Item || itemStack.getItem() instanceof Lance_Item) {
            info.setReturnValue(10);
        }
    }

    @Inject(method = "blockedByShield", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/projectile/PersistentProjectileEntity;getPierceLevel()B"), cancellable = true)
    private void blockedByShieldMixin(DamageSource source, CallbackInfoReturnable<Boolean> info) {
        LivingEntity livingEntity = (LivingEntity) (Object) this;
        ItemStack itemStack = livingEntity.getMainHandItem();
        if (itemStack.is(TagInit.ACCROSS_DOUBLE_HANDED_ITEMS) || itemStack.is(TagInit.DOUBLE_HANDED_ITEMS) || itemStack.getItem() instanceof Long_Sword_Item
                || itemStack.getItem() instanceof Big_Axe_Item) {
            info.setReturnValue(false);
        }
    }

    @Inject(method = "blockedByShield", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/Vec3d;dotProduct(Lnet/minecraft/util/math/Vec3d;)D", shift = Shift.AFTER), cancellable = true)
    private void blockedByShieldDamageWeaponMixin(DamageSource source, CallbackInfoReturnable<Boolean> info) {
        LivingEntity livingEntity = (LivingEntity) (Object) this;
        ItemStack itemStack = livingEntity.getMainHandItem();
        if (itemStack.is(TagInit.ACCROSS_DOUBLE_HANDED_ITEMS) || itemStack.is(TagInit.DOUBLE_HANDED_ITEMS) || itemStack.getItem() instanceof Long_Sword_Item
                || itemStack.getItem() instanceof Big_Axe_Item) {
            if (livingEntity instanceof Player) {
                ((Player) livingEntity).getCooldowns().addCooldown(itemStack.getItem(), ConfigInit.CONFIG.weapon_blocking_cooldown);
            }
            if (!level.isClientSide) {
                livingEntity.getMainHandItem().hurtAndBreak(1, livingEntity, (p) -> p.broadcastBreakEvent(p.getUsedItemHand()));
            }
        }
    }

}