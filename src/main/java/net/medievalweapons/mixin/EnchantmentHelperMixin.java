package net.medievalweapons.mixin;

import java.util.ArrayList;
import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import org.spongepowered.asm.mixin.injection.At;

import net.medievalweapons.init.TagInit;
import net.medievalweapons.item.Big_Axe_Item;
import net.medievalweapons.item.Javelin_Item;
import net.medievalweapons.item.Lance_Item;
import net.medievalweapons.item.Small_Axe_Item;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.SweepingEdgeEnchantment;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {

    @Inject(method = "getSweepingMultiplier", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/enchantment/EnchantmentHelper;getEquipmentLevel(Lnet/minecraft/enchantment/Enchantment;Lnet/minecraft/entity/LivingEntity;)I"), locals = LocalCapture.CAPTURE_FAILSOFT, cancellable = true)
    private static void getSweepingMultiplierMixin(LivingEntity entity, CallbackInfoReturnable<Float> info, int i) {
        ItemStack itemStack = entity.getMainHandItem();

        if (itemStack.is(TagInit.ACCROSS_DOUBLE_HANDED_ITEMS) || itemStack.getItem() instanceof Big_Axe_Item)
            info.setReturnValue(SweepingEdgeEnchantment.getSweepingDamageRatio(i + 1));

        if (i > 0 && itemStack.getItem() instanceof Lance_Item)
            info.setReturnValue(SweepingEdgeEnchantment.getSweepingDamageRatio(i > 1 ? i - 1 : 0));
    }

    @Inject(method = "getPossibleEntries", at = @At("HEAD"), cancellable = true)
    private static void getPossibleEntriesMixin(int i, ItemStack stack, boolean treasureAllowed, CallbackInfoReturnable<List<EnchantmentInstance>> info) {
        if (stack.getItem() instanceof Javelin_Item) {
            List<EnchantmentInstance> currentEnchantments = info.getReturnValue();
            List<EnchantmentInstance> enchantments = new ArrayList<>();
            currentEnchantments.forEach(enchantment -> {
                if (!(enchantment.enchantment.category == EnchantmentCategory.TRIDENT) || enchantment.enchantment == Enchantments.IMPALING) {
                    enchantments.add(enchantment);
                }
            });
            Enchantment piercing = Enchantments.PIERCING;
            for (int level = piercing.getMaxLevel(); level > piercing.getMinLevel() - 1; --level) {
                if (i >= piercing.getMinCost(level) && i <= piercing.getMaxCost(level)) {
                    enchantments.add(new EnchantmentInstance(piercing, level));
                    break;
                }
            }
            info.setReturnValue(enchantments);
        }
    }

    @Inject(method = "getKnockback", at = @At("HEAD"), cancellable = true)
    private static void getKnockbackMixin(LivingEntity entity, CallbackInfoReturnable<Integer> info) {
        ItemStack itemStack = entity.getItemInHand(InteractionHand.MAIN_HAND);
        if (itemStack.getItem() instanceof Small_Axe_Item) {
            info.setReturnValue(1 + getEquipmentLevel(Enchantments.KNOCKBACK, entity));
        }
    }

    @Shadow
    public static int getEquipmentLevel(Enchantment enchantment, LivingEntity entity) {
        return 1;
    }

}
