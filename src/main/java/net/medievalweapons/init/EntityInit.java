package net.medievalweapons.init;

import java.util.Locale;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import net.medievalweapons.MedievalWeapons;
import net.medievalweapons.entity.FranciscaHTEntity;
import net.medievalweapons.entity.FranciscaLTEntity;

import static net.dries007.tfc.TerraFirmaCraft.MOD_ID;

public class EntityInit
{
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, MedievalWeapons.MOD_ID);

    public static final RegistryObject<EntityType<FranciscaLTEntity>> WOODEN_FRANCISCA_LT = register("wooden_francisca", EntityType.Builder.of(FranciscaLTEntity::new, MobCategory.MISC).dimensions(EntityDimensions.fixed(0.5F, 0.5F)));
    public static final RegistryObject<EntityType<FranciscaHTEntity>> WOODEN_FRANCISCA_HT = register("wooden_francisca", EntityType.Builder.of(FranciscaHTEntity::new, MobCategory.MISC).dimensions(EntityDimensions.fixed(0.5F, 0.5F)));

    public static final RegistryObject<EntityType<HealingBallEntity>> HEALING_BALL_ENTITY = register("healing_ball", EntityType.Builder.of(HealingBallEntity::new, MobCategory.MISC).dimensions(EntityDimensions.fixed(0.3F, 0.3F))));

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