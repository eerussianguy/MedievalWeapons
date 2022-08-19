package net.medievalweapons.mixin;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.level.Level;

import net.medievalweapons.item.LongBowItem;
import net.medievalweapons.item.RecurveBowItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(BowItem.class)
public abstract class BowItemMixin extends ProjectileWeaponItem
{
    public BowItemMixin(Properties settings)
    {
        super(settings);
    }

    @Inject(method = "releaseUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/projectile/AbstractArrow;shootFromRotation(Lnet/minecraft/world/entity/Entity;FFFFF)V", shift = Shift.AFTER), locals = LocalCapture.CAPTURE_FAILSOFT)
    private void onStoppedUsingMixin(ItemStack stack, Level world, LivingEntity user, int remainingUseTicks, CallbackInfo info, Player playerEntity, boolean bl, ItemStack itemStack, int i, float f, boolean bl2, ArrowItem arrowItem, AbstractArrow persistentProjectileEntity)
    {
        if (stack.getItem() instanceof LongBowItem)
        {
            persistentProjectileEntity.shootFromRotation(playerEntity, playerEntity.getXRot(), playerEntity.getYRot(), 0.0F, f * 3.7F, 1.0F);
        }
        else if (stack.getItem() instanceof RecurveBowItem)
        {
            persistentProjectileEntity.shootFromRotation(playerEntity, playerEntity.getXRot(), playerEntity.getYRot(), 0.0F, f * 2.6F, 1.0F);
        }
    }

    // todo: pull progress change

}
