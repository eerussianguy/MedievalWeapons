package net.medievalweapons.item;

import java.util.List;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

import org.jetbrains.annotations.Nullable;

public class BigAxeItem extends SwordItem
{

    public BigAxeItem(Tier toolMaterial, int attackDamage, float attackSpeed, Properties settings)
    {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag context)
    {
        super.appendHoverText(stack, world, tooltip, context);
        tooltip.add(new TranslatableComponent("item.medievalweapons.double_handed.tooltip"));
    }

    @Override
    public InteractionResult onItemUseFirst(ItemStack stack, UseOnContext context)
    {
        Player player = context.getPlayer();
        if (player != null && (!player.getOffhandItem().isEmpty() || player.isSwimming() || player.isPassenger()))
        {
            return InteractionResult.FAIL;
        }
        return super.onItemUseFirst(stack, context);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack)
    {
        return UseAnim.BLOCK;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player user, InteractionHand hand)
    {
        ItemStack itemStack = user.getItemInHand(hand);
        user.startUsingItem(hand);
        return InteractionResultHolder.consume(itemStack);
    }

}