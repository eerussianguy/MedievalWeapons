package net.medievalweapons;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.PlaySoundAtEntityEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.IEventBus;

import net.medievalweapons.effect.EffectInit;
import net.medievalweapons.init.SoundInit;
import net.medievalweapons.init.TagInit;
import net.medievalweapons.item.BigAxeItem;
import net.medievalweapons.item.LongBowItem;
import net.medievalweapons.item.LongSwordItem;
import net.medievalweapons.item.RecurveBowItem;

public class ForgeEvents
{
    public static void init()
    {
        IEventBus bus = MinecraftForge.EVENT_BUS;

        bus.addListener(ForgeEvents::onPlayerAttack);
        bus.addListener(ForgeEvents::onLivingHurt);
        bus.addListener(ForgeEvents::onPlaySoundAt);
        bus.addListener(ForgeEvents::onEntityJoin);
    }

    public static void onEntityJoin(EntityJoinWorldEvent event)
    {
        if (event.loadedFromDisk()) return;

        if (event.getEntity() instanceof AbstractArrow arrow && arrow.getOwner() instanceof Player player)
        {
            Item bow = player.getMainHandItem().getItem();
            if (!(bow instanceof BowItem))
            {
                bow = player.getOffhandItem().getItem();
            }
            if (bow instanceof RecurveBowItem)
            {
                arrow.setDeltaMovement(arrow.getDeltaMovement().scale(1.23));
            }
            else if (bow instanceof LongBowItem)
            {
                arrow.setDeltaMovement(arrow.getDeltaMovement().scale(0.87));
            }
        }
    }

    public static void onPlaySoundAt(PlaySoundAtEntityEvent event)
    {
        if (event.getSound() == SoundEvents.SHIELD_BLOCK)
        {
            Entity entity = event.getEntity();
            if (entity instanceof LivingEntity livingEntity)
            {
                ItemStack itemStack = livingEntity.getMainHandItem();
                if (itemStack.is(TagInit.ACROSS_DOUBLE_HANDED_ITEMS) || itemStack.getItem() instanceof BigAxeItem)
                {
                    livingEntity.playSound(SoundInit.PARRYING.get(), 1.0F, 0.9F + livingEntity.level.random.nextFloat() * 0.2F);
                    event.setCanceled(true);
                }
                else if (itemStack.is(TagInit.DOUBLE_HANDED_ITEMS) || itemStack.getItem() instanceof LongSwordItem)
                {
                    livingEntity.playSound(SoundInit.SWORD_PARRYING.get(), 1.0F, 0.9F + livingEntity.level.random.nextFloat() * 0.2F);
                    event.setCanceled(true);
                }
            }
        }
    }

    public static void onPlayerAttack(AttackEntityEvent event)
    {
        if (event.getPlayer().hasEffect(EffectInit.STUN_EFFECT.get()))
        {
            event.setCanceled(true);
        }
    }

    public static void onLivingHurt(LivingHurtEvent event)
    {
        if (event.getSource() instanceof EntityDamageSource source && source.getEntity() instanceof Player player && !player.getLevel().isClientSide)
        {
            ItemStack weapon = player.getMainHandItem();
            if (weapon.is(TagInit.MACES) && weapon.getItem() instanceof SwordItem sword)
            {
                if (player.getRandom().nextInt(20) < sword.getDamage())
                {
                    event.getEntityLiving().addEffect(new MobEffectInstance(EffectInit.STUN_EFFECT.get(), ((int) sword.getDamage()) * 2 + 60));
                }
            }
        }
    }
}
