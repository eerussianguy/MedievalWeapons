package net.medievalweapons;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;

import net.medievalweapons.init.TagInit;
import net.medievalweapons.item.BigAxeItem;
import net.medievalweapons.item.LanceItem;
import net.medievalweapons.item.LongSwordItem;
import net.medievalweapons.item.SmallAxeItem;

public final class MUtil
{
    public static boolean hasExtraShieldCooldown(ItemStack stack)
    {
        return stack.getItem() instanceof SmallAxeItem || stack.getItem() instanceof BigAxeItem;
    }

    public static boolean makesParrySound(ItemStack stack)
    {
        return stack.is(TagInit.ACROSS_DOUBLE_HANDED_ITEMS) || stack.getItem() instanceof BigAxeItem;
    }

    public static boolean makesSwordParrySound(ItemStack stack)
    {
        return stack.is(TagInit.DOUBLE_HANDED_ITEMS) || stack.getItem() instanceof LongSwordItem;
    }

    public static boolean shouldStun(ItemStack weapon)
    {
        return weapon.is(TagInit.MACES) && weapon.getItem() instanceof SwordItem;
    }

    public static boolean hasSpecialBlockBehavior(ItemStack stack)
    {
        return stack.is(TagInit.ACROSS_DOUBLE_HANDED_ITEMS) || stack.is(TagInit.DOUBLE_HANDED_ITEMS) || stack.getItem() instanceof LongSwordItem || stack.getItem() instanceof BigAxeItem;
    }

    public static boolean reducesSwingTime(ItemStack stack)
    {
        return hasSpecialBlockBehavior(stack) || stack.getItem() instanceof LanceItem;
    }

    public static ResourceLocation identifier(String path)
    {
        return new ResourceLocation(MedievalWeapons.MOD_ID, path);
    }
}
