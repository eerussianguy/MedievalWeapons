package net.medievalweapons.init;

import java.util.function.Supplier;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import net.medievalweapons.effect.StunEffect;

import static net.medievalweapons.MedievalWeapons.MOD_ID;

public class EffectInit
{
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, MOD_ID);

    public static final RegistryObject<MobEffect> STUN_EFFECT = register("stun", () -> new StunEffect(MobEffectCategory.HARMFUL, 12221440));

    public static <T extends MobEffect> RegistryObject<T> register(String name, Supplier<T>supplier)
    {
        return EFFECTS.register(name, supplier);
    }

}
