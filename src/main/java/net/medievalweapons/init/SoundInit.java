package net.medievalweapons.init;

import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import net.medievalweapons.MUtil;
import net.medievalweapons.MedievalWeapons;

public class SoundInit
{
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MedievalWeapons.MOD_ID);

    public static final RegistryObject<SoundEvent> SWORD_PARRYING = register("parrying");
    public static final RegistryObject<SoundEvent> PARRYING = register("sword_parrying");

    public static RegistryObject<SoundEvent> register(String name)
    {
        return SOUNDS.register(name, () -> new SoundEvent(MUtil.identifier(name)));
    }

}
