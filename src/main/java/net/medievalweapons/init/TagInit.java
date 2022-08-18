package net.medievalweapons.init;

import net.minecraft.core.Registry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import net.medievalweapons.MedievalWeapons;

public class TagInit
{
    public static final TagKey<Item> DOUBLE_HANDED_ITEMS = item("double_handed_items");
    public static final TagKey<Item> ACCROSS_DOUBLE_HANDED_ITEMS = item("accross_double_handed_items");

    public static TagKey<Item> item(String name)
    {
        return TagKey.create(Registry.ITEM_REGISTRY, MedievalWeapons.identifier(name));
    }

    public static void init() { }

}