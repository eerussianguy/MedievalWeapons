package net.medievalweapons.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import net.dries007.tfc.common.entities.TFCEntities;
import net.dries007.tfc.common.entities.ThrownJavelin;

public class ThrownFrancisca extends ThrownJavelin
{
    public ThrownFrancisca(EntityType<? extends ThrownFrancisca> type, Level level)
    {
        super(type, level);
    }

    public ThrownFrancisca(EntityType<? extends ThrownFrancisca> type, Level level, LivingEntity entity, ItemStack stack)
    {
        super(type, level);
        this.setItem(stack);
        this.setIsEnchantGlowing(stack.hasFoil());
    }
}
