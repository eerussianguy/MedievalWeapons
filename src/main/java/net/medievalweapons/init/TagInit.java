package net.medievalweapons.init;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class TagInit
{

    public static final TagKey<Item> DOUBLE_HANDED_ITEMS = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("medievalweapons", "double_handed_items"));
    public static final TagKey<Item> ACCROSS_DOUBLE_HANDED_ITEMS = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("medievalweapons", "accross_double_handed_items"));

    public static void init()
    {
    }

}