package net.medievalweapons.init;

import net.minecraft.core.Registry;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

import net.medievalweapons.effect.StunEffect;

public class EffectInit
{

    public static final MobEffect STUN_EFFECT = new StunEffect(MobEffectCategory.HARMFUL, 12221440);

    public static void init()
    {
        Registry.register(Registry.MOB_EFFECT, "medievalweapons:stun", STUN_EFFECT);
    }

}
