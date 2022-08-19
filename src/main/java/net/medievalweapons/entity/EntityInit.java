package net.medievalweapons.entity;

import java.util.Locale;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import net.medievalweapons.MedievalWeapons;
import net.medievalweapons.entity.FranciscaHTEntity;
import net.medievalweapons.entity.FranciscaLTEntity;
import net.medievalweapons.init.ItemInit;

import static net.dries007.tfc.TerraFirmaCraft.MOD_ID;

public class EntityInit
{
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, MedievalWeapons.MOD_ID);

    public static final RegistryObject<EntityType<FranciscaLTEntity>> WOODEN_FRANCISCA_LT = register("wooden_francisca", EntityType.Builder.<FranciscaLTEntity>of((type, level) -> new FranciscaLTEntity(type, level, ItemInit.WOODEN_FRANCISCA_LT_ITEM), MobCategory.MISC).sized(0.5f, 0.5f));
    public static final RegistryObject<EntityType<FranciscaHTEntity>> WOODEN_FRANCISCA_HT = register("wooden_francisca_ht", EntityType.Builder.<FranciscaHTEntity>of((type, level) -> new FranciscaHTEntity(type, level, ItemInit.WOODEN_FRANCISCA_HT_ITEM), MobCategory.MISC).sized(0.5f, 0.5f));

    public static <E extends Entity> RegistryObject<EntityType<E>> register(String name, EntityType.Builder<E> builder)
    {
        return register(name, builder, true);
    }

    public static <E extends Entity> RegistryObject<EntityType<E>> register(String name, EntityType.Builder<E> builder, boolean serialize)
    {
        final String id = name.toLowerCase(Locale.ROOT);
        return ENTITIES.register(id, () -> {
            if (!serialize) builder.noSave();
            return builder.build(MOD_ID + ":" + id);
        });
    }
}