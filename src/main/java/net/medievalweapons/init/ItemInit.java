package net.medievalweapons.init;

import java.util.function.Supplier;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import net.medievalweapons.MedievalWeapons;
import net.medievalweapons.entity.EntityInit;
import net.medievalweapons.item.*;

import static net.medievalweapons.MedievalWeapons.MOD_ID;

public class ItemInit
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

    public static final RegistryObject<SmallAxeItem> WOODEN_SMALL_AXE_ITEM = register("wooden_small_axe", () -> new SmallAxeItem(Tiers.WOOD, 5, -2.9F, properties()));
    public static final RegistryObject<LongSwordItem> WOOD_LONG_SWORD_ITEM = register("wooden_long_sword", () -> new LongSwordItem(Tiers.WOOD, 6, -3.0F, properties()));
    public static final RegistryObject<DaggerItem> WOOD_DAGGER_ITEM = register("wooden_dagger", () -> new DaggerItem(Tiers.WOOD, 2, -2.0F, properties()));
    public static final RegistryObject<FranciscaLTItem> WOODEN_FRANCISCA_LT_ITEM = register("wooden_francisca", () -> new FranciscaLTItem(Tiers.WOOD, 1.0F, -2.6F, EntityInit.WOODEN_FRANCISCA_LT, properties()));
    public static final RegistryObject<FrancisaHTItem> WOODEN_FRANCISCA_HT_ITEM = register("wooden_francisca_ht", () -> new FrancisaHTItem(Tiers.WOOD, 1.0F, -2.6F, EntityInit.WOODEN_FRANCISCA_HT, properties()));
    public static final RegistryObject<BigAxeItem> WOODEN_BIG_AXE_ITEM = register("wooden_big_axe", () -> new BigAxeItem(Tiers.WOOD, 6, -3.4F, properties()));

    public static final RegistryObject<LanceItem> WOODEN_LANCE_ITEM = register("wooden_lance", () -> new LanceItem(Tiers.WOOD, 3, -3.2F, properties()));
    public static final RegistryObject<NinjatoItem> WOODEN_NINJATO_ITEM = register("wooden_ninjato", () -> new NinjatoItem(Tiers.WOOD, 2, -2.1F, properties()));
    public static final RegistryObject<LongBowItem> LONG_BOW_ITEM = register("long_bow", () -> new LongBowItem(new Item.Properties().durability(443).tab(MedievalWeapons.GROUP)));
    public static final RegistryObject<RecurveBowItem> RECURVE_BOW_ITEM = register("recurve_bow", () -> new RecurveBowItem(new Item.Properties().durability(361).tab(MedievalWeapons.GROUP)));
    public static final RegistryObject<ThalleousSwordItem> THALLEOUS_SWORD = register("thalleous_sword", () -> new ThalleousSwordItem(Tiers.DIAMOND, 10, -3.0F, properties()));

    public static Item.Properties properties()
    {
        return new Item.Properties().tab(MedievalWeapons.GROUP);
    }

    public static <I extends Item> RegistryObject<I> register(String name, Supplier<I> item)
    {
        return ITEMS.register(name, item);
    }

}