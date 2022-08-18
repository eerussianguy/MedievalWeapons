package net.medievalweapons.item;

import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class SmallAxeItem extends SwordItem
{

    public SmallAxeItem(Tier toolMaterial, int attackDamage, float attackSpeed, Properties settings)
    {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    // Built in knockback is done by mixin

}