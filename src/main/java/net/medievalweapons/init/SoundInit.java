package net.medievalweapons.init;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class SoundInit
{

    public static final ResourceLocation MAGIC_HEAL_AURA = new ResourceLocation("medievalweapons:magic_heal_aura");
    public static final ResourceLocation MAGIC_SHOT = new ResourceLocation("medievalweapons:magic_shot");
    public static final ResourceLocation PARRYING = new ResourceLocation("medievalweapons:parrying");
    public static final ResourceLocation SWORD_PARRYING = new ResourceLocation("medievalweapons:sword_parrying");
    public static SoundEvent SWORD_PARRYING_EVENT = new SoundEvent(SWORD_PARRYING);
    public static SoundEvent MAGIC_HEAL_AURA_EVENT = new SoundEvent(MAGIC_HEAL_AURA);
    public static SoundEvent MAGIC_SHOT_EVENT = new SoundEvent(MAGIC_SHOT);
    public static SoundEvent PARRYING_EVENT = new SoundEvent(PARRYING);

    public static void init()
    {
        Registry.register(Registry.SOUND_EVENT, SoundInit.MAGIC_HEAL_AURA, MAGIC_HEAL_AURA_EVENT);
        Registry.register(Registry.SOUND_EVENT, SoundInit.MAGIC_SHOT, MAGIC_SHOT_EVENT);
        Registry.register(Registry.SOUND_EVENT, SoundInit.PARRYING, PARRYING_EVENT);
        Registry.register(Registry.SOUND_EVENT, SoundInit.SWORD_PARRYING, SWORD_PARRYING_EVENT);
    }

}
