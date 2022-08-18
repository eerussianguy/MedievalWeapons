package net.medievalweapons.item;

import java.util.function.Supplier;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

import net.medievalweapons.entity.FranciscaLTEntity;

public class FranciscaLTItem extends SwordItem
{

    private final Supplier<EntityType<FranciscaLTEntity>> typeSupplier;
    private EntityType<FranciscaLTEntity> cachedType = null;

    public FranciscaLTItem(Tier toolMaterial, float attackDamage, float attackSpeed, Supplier<EntityType<FranciscaLTEntity>> typeSupplier, Properties settings)
    {
        super(toolMaterial, (int) attackDamage, attackSpeed, settings);
        this.typeSupplier = typeSupplier;
    }

    public EntityType<FranciscaLTEntity> getType()
    {
        if (cachedType == null)
        {
            cachedType = typeSupplier.get();
        }
        return cachedType;
    }

    @Override
    public void releaseUsing(ItemStack stack, Level world, LivingEntity user, int remainingUseTicks)
    {
        if (user instanceof Player)
        {
            Player playerEntity = (Player) user;
            int i = this.getUseDuration(stack) - remainingUseTicks;
            if (i >= 10)
            {
                if (!world.isClientSide)
                {
                    stack.hurtAndBreak(1, playerEntity, entity -> entity.broadcastBreakEvent(user.getUsedItemHand()));
                    FranciscaLTEntity francisca_LT_Entity = new FranciscaLTEntity(world, playerEntity, this, stack);
                    francisca_LT_Entity.shootFromRotation(playerEntity, playerEntity.getXRot(), playerEntity.getYRot(), 0.0F, 1.2F, 1.0F);
                    if (playerEntity.isCreative())
                    {
                        francisca_LT_Entity.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                    }
                    world.addFreshEntity(francisca_LT_Entity);
                    world.playSound(null, francisca_LT_Entity, SoundEvents.TRIDENT_THROW, SoundSource.PLAYERS, 1.0F, 1.0F);
                    if (!playerEntity.isCreative())
                    {
                        playerEntity.getInventory().removeItem(stack);
                    }
                }

                playerEntity.awardStat(Stats.ITEM_USED.get(this));
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
        return UseAnim.SPEAR;
    }

    @Override
    public int getUseDuration(ItemStack stack)
    {
        return 72000;
    }

}