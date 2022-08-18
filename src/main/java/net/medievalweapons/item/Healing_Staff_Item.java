package net.medievalweapons.item;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;

import net.medievalweapons.entity.Healing_Ball_Entity;
import net.medievalweapons.init.ConfigInit;
import net.medievalweapons.init.ParticleInit;
import net.medievalweapons.init.SoundInit;

public class Healing_Staff_Item extends SwordItem
{
    private final int addition;

    public Healing_Staff_Item(Tier toolMaterial, int attackDamage, float attackSpeed, int addition, Properties settings)
    {
        super(toolMaterial, attackDamage, attackSpeed, settings);
        this.addition = addition;
    }

    @Override
    public void releaseUsing(ItemStack stack, Level world, LivingEntity user, int remainingUseTicks)
    {
        if (user instanceof Player)
        {
            Player playerEntity = (Player) user;
            int i = this.getUseDuration(stack) - remainingUseTicks;
            if (i >= 30)
            {
                if (!world.isClientSide)
                {
                    stack.hurtAndBreak(3, playerEntity, entity -> entity.broadcastBreakEvent(user.getUsedItemHand()));
                    world.playSound(null, playerEntity.blockPosition(), SoundInit.MAGIC_SHOT_EVENT, SoundSource.PLAYERS, 0.9F, 1.0F);
                    if (ConfigInit.CONFIG.old_healing_staff_behavior)
                    {
                        Healing_Ball_Entity healing_Ball_Entity = new Healing_Ball_Entity(user, world, this.addition);
                        healing_Ball_Entity.shootFromRotation(playerEntity, playerEntity.getXRot(), playerEntity.getYRot(), 0.0F, 0.5F, 1.0F);
                        healing_Ball_Entity.setPosRaw(playerEntity.getX(), playerEntity.getY() + 1.6D, playerEntity.getZ());
                        world.addFreshEntity(healing_Ball_Entity);
                    }
                    else
                    {
                        HitResult hitResult = playerEntity.pick(8.0D + addition, 1.0F, false);
                        if (hitResult.getType().equals(HitResult.Type.BLOCK))
                        {
                            AreaEffectCloud areaEffectCloudEntity = new AreaEffectCloud(world, hitResult.getLocation().x, hitResult.getLocation().y + 0.1D, hitResult.getLocation().z);
                            areaEffectCloudEntity.setOwner(user);
                            areaEffectCloudEntity.setParticle(ParticleInit.HEALING_AURA_PARTICLE);
                            areaEffectCloudEntity.setRadius((float) this.addition);
                            areaEffectCloudEntity.setDuration(this.addition * 100);
                            areaEffectCloudEntity.setRadiusPerTick(-(12.0F - areaEffectCloudEntity.getRadius()) / 500.0F);
                            areaEffectCloudEntity.addEffect(new MobEffectInstance(MobEffects.HEAL, 1, 0));
                            world.playSound(null, hitResult.getLocation().x, hitResult.getLocation().y + 0.1D, hitResult.getLocation().z, SoundInit.MAGIC_HEAL_AURA_EVENT, SoundSource.NEUTRAL, 0.9F, 1.0F);
                            world.addFreshEntity(areaEffectCloudEntity);
                        }
                    }
                }
                playerEntity.getCooldowns().addCooldown(this, 100 + (this.addition * 20));
            }
        }
    }

    @Override
    public void onUseTick(Level world, LivingEntity user, ItemStack stack, int remainingUseTicks)
    {
        int i = this.getUseDuration(stack) - remainingUseTicks;
        if (user instanceof Player)
        {
            Player playerEntity = (Player) user;
            if (i >= 30)
            {
                if (world.isClientSide)
                {
                    if (!ConfigInit.CONFIG.old_healing_staff_behavior)
                    {
                        HitResult hitResult = playerEntity.pick(8.0D + addition, 0.0F, false);
                        if (hitResult.getType().equals(HitResult.Type.BLOCK))
                        {
                            playerEntity.level.addParticle(ParticleInit.HEALING_AURA_PARTICLE, hitResult.getLocation().x - 0.2D + world.random.nextDouble() * 0.4D,
                                hitResult.getLocation().y - 0.1D + world.random.nextDouble() * 0.2D, hitResult.getLocation().z - 0.2D + world.random.nextDouble() * 0.4D, 0.0D, 0.0D, 0.0D);
                        }
                    }
                    for (int u = 0; u < 3; u++)
                    {
                        playerEntity.level.addParticle(ParticleInit.HEALING_AURA_PARTICLE, playerEntity.getRandomX(0.5D), playerEntity.getRandomY(), playerEntity.getRandomZ(0.5D), 0.0D, 0.0D,
                            0.0D);
                    }
                }
                else if (i % 80 == 0 && i < 241)
                {
                    stack.hurtAndBreak(1, playerEntity, entity -> entity.broadcastBreakEvent(user.getUsedItemHand()));
                    playerEntity.addEffect((new MobEffectInstance(MobEffects.HEAL, 1, 0)));
                }
            }
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player user, InteractionHand hand)
    {
        ItemStack itemStack = user.getItemInHand(hand);
        if (itemStack.getDamageValue() >= itemStack.getMaxDamage() - 1)
        {
            return InteractionResultHolder.fail(itemStack);
        }
        else
        {
            user.startUsingItem(hand);
            return InteractionResultHolder.consume(itemStack);
        }
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack)
    {
        return UseAnim.BLOCK;
    }

    @Override
    public int getUseDuration(ItemStack stack)
    {
        return 72000;
    }

}