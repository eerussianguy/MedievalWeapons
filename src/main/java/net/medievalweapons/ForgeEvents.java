package net.medievalweapons;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ItemAttributeModifierEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.PlaySoundAtEntityEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.ShieldBlockEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.IEventBus;

import net.medievalweapons.config.MedievalConfig;
import net.medievalweapons.effect.EffectInit;
import net.medievalweapons.init.SoundInit;
import net.medievalweapons.item.*;

public class ForgeEvents
{
    public static void init()
    {
        IEventBus bus = MinecraftForge.EVENT_BUS;

        bus.addListener(ForgeEvents::onPlayerAttack);
        bus.addListener(ForgeEvents::onLivingHurt);
        bus.addListener(ForgeEvents::onPlaySoundAt);
        bus.addListener(ForgeEvents::onEntityJoin);
        bus.addListener(ForgeEvents::onShieldHit);
        bus.addListener(ForgeEvents::onAttributes);
    }

    public static void onShieldHit(ShieldBlockEvent event)
    {
        DamageSource src = event.getDamageSource();
        if (src.getDirectEntity() instanceof LivingEntity living && !src.isProjectile() && event.getEntityLiving() instanceof Player target)
        {
            ItemStack weapon = living.getMainHandItem();
            if (weapon.canDisableShield(target.getUseItem(), target, living))
            {
                int extraCool = MedievalConfig.SERVER.extraWeaponShieldBlockingCooldown.get();
                if (extraCool != 0 && MUtil.hasExtraShieldCooldown(weapon))
                {
                    if (finishBlocking(target, extraCool))
                    {
                        event.setCanceled(true);
                    }
                }
                else
                {
                    extraCool = MedievalConfig.SERVER.shieldBlockingCooldown.get();
                    if (extraCool != 0)
                    {
                        if (finishBlocking(target, extraCool))
                        {
                            event.setCanceled(true);
                        }
                    }
                }

            }
        }
    }

    private static boolean finishBlocking(Player target, int extraCooldown)
    {
        float efficiency = 1F + (float) EnchantmentHelper.getBlockEfficiency(target) * 0.05F;
        if (target.getRandom().nextFloat() < efficiency)
        {
            target.getCooldowns().addCooldown(target.getUseItem().getItem(), 100 + extraCooldown);
            target.stopUsingItem();
            target.level.broadcastEntityEvent(target, (byte)30);
            return true;
        }
        return false;
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
                ItemStack stack = livingEntity.getMainHandItem();
                if (MUtil.makesParrySound(stack))
                {
                    livingEntity.playSound(SoundInit.PARRYING.get(), 1.0F, 0.9F + livingEntity.level.random.nextFloat() * 0.2F);
                    event.setCanceled(true);
                }
                else if (MUtil.makesSwordParrySound(stack))
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
            if (MUtil.shouldStun(weapon) && weapon.getItem() instanceof SwordItem sword)
            {
                if (player.getRandom().nextInt(20) < sword.getDamage())
                {
                    event.getEntityLiving().addEffect(new MobEffectInstance(EffectInit.STUN_EFFECT.get(), ((int) sword.getDamage()) * 2 + 60));
                }
            }
        }
    }

    public static void onAttributes(ItemAttributeModifierEvent event)
    {
        final float reach = MUtil.getExtraReach(event.getItemStack());
        if (reach > 0)
        {
            event.addModifier(ForgeMod.ATTACK_RANGE.get(), new AttributeModifier("Attack range", reach, AttributeModifier.Operation.ADDITION));
        }
    }
}
