package net.medievalweapons.item;

import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class NinjatoItem extends SwordItem
{

    // todo: i guess this is the offhand attacking thing
    public NinjatoItem(Tier toolMaterial, int attackDamage, float attackSpeed, Properties settings)
    {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

}
