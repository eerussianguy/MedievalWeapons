package net.medievalweapons.mixin;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import net.medievalweapons.config.MedievalConfig;
import net.medievalweapons.init.TagInit;
import net.medievalweapons.item.BigAxeItem;
import net.medievalweapons.item.LanceItem;
import net.medievalweapons.item.LongSwordItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity
{
    public LivingEntityMixin(EntityType<?> type, Level world)
    {
        super(type, world);
    }

    @Inject(method = "getCurrentSwingDuration", at = @At("HEAD"), cancellable = true)
    private void getHandSwingDuration(CallbackInfoReturnable<Integer> info)
    {
        LivingEntity livingEntity = (LivingEntity) (Object) this;
        ItemStack itemStack = livingEntity.getMainHandItem();
        if (itemStack.is(TagInit.ACROSS_DOUBLE_HANDED_ITEMS) || itemStack.is(TagInit.DOUBLE_HANDED_ITEMS) || itemStack.getItem() instanceof LongSwordItem || itemStack.getItem() instanceof BigAxeItem || itemStack.getItem() instanceof LanceItem)
        {
            info.setReturnValue(10);
        }
    }

    @Inject(method = "isDamageSourceBlocked", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/projectile/AbstractArrow;getPierceLevel()B"), cancellable = true)
    private void inject$isDamageSourceBlocked(DamageSource source, CallbackInfoReturnable<Boolean> info)
    {
        LivingEntity livingEntity = (LivingEntity) (Object) this;
        ItemStack held = livingEntity.getMainHandItem();
        if (held.is(TagInit.ACROSS_DOUBLE_HANDED_ITEMS) || held.is(TagInit.DOUBLE_HANDED_ITEMS) || held.getItem() instanceof LongSwordItem || held.getItem() instanceof BigAxeItem)
        {
            info.setReturnValue(false);
        }
    }

    @Inject(method = "isDamageSourceBlocked", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/phys/Vec3;dot(Lnet/minecraft/world/phys/Vec3;)D", shift = Shift.AFTER))
    private void blockedByShieldDamageWeaponMixin(DamageSource source, CallbackInfoReturnable<Boolean> info)
    {
        LivingEntity livingEntity = (LivingEntity) (Object) this;
        ItemStack held = livingEntity.getMainHandItem();
        if (held.is(TagInit.ACROSS_DOUBLE_HANDED_ITEMS) || held.is(TagInit.DOUBLE_HANDED_ITEMS) || held.getItem() instanceof LongSwordItem || held.getItem() instanceof BigAxeItem)
        {
            if (livingEntity instanceof Player player)
            {
                player.getCooldowns().addCooldown(held.getItem(), MedievalConfig.SERVER.weaponBlockingCooldown.get());
            }
            if (!level.isClientSide)
            {
                livingEntity.getMainHandItem().hurtAndBreak(1, livingEntity, (p) -> p.broadcastBreakEvent(p.getUsedItemHand()));
            }
        }
    }

}