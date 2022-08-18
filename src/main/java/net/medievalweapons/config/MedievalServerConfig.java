package net.medievalweapons.config;

import java.util.function.Function;

import net.minecraftforge.common.ForgeConfigSpec;

import static net.medievalweapons.MedievalWeapons.MOD_ID;

public class MedievalServerConfig
{
    public final ForgeConfigSpec.IntValue weaponBlockingCooldown;
    public final ForgeConfigSpec.IntValue shieldBlockingCooldown;
    public final ForgeConfigSpec.IntValue extraWeaponShieldBlockingCooldown;
    public final ForgeConfigSpec.BooleanValue oldHealingStaffBehavior;

    MedievalServerConfig(ForgeConfigSpec.Builder innerBuilder)
    {
        Function<String, ForgeConfigSpec.Builder> builder = name -> innerBuilder.translation(MOD_ID + ".config.server." + name);

        innerBuilder.push("general");

        weaponBlockingCooldown = builder.apply("weaponBlockingCooldown").comment("In ticks, 20 ticks = 1 second").defineInRange("weaponBlockingCooldown", 60, 0, Integer.MAX_VALUE);
        shieldBlockingCooldown = builder.apply("shieldBlockingCooldown").comment("In ticks, 20 ticks = 1 second").defineInRange("shieldBlockingCooldown", 40, 0, Integer.MAX_VALUE);
        extraWeaponShieldBlockingCooldown = builder.apply("extraWeaponShieldBlockingCooldown").comment("In ticks, 20 ticks = 1 second").defineInRange("extraWeaponShieldBlockingCooldown", 60, 0, Integer.MAX_VALUE);
        oldHealingStaffBehavior = builder.apply("oldHealingStaffBehavior").comment("Enables legacy healing staff behavior.").define("oldHealingStaffBehavior", false);

        innerBuilder.pop();
    }
}
