package net.medievalweapons.item;

import net.minecraft.world.item.BowItem;

public class Recurve_Bow_Item extends BowItem {

    public Recurve_Bow_Item(Properties settings) {
        super(settings);
    }

    @Override
    public int getDefaultProjectileRange() {
        return 12;
    }

}
