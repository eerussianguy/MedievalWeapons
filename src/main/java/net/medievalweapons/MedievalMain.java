package net.medievalweapons;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.medievalweapons.init.*;
import net.medievalweapons.network.PlayerPacket;

public final class MedievalMain implements ModInitializer
{

    // All credits go to Plasma Studios: https://youtu.be/Uc7YMW3AKpg?t=1152
    // Thanks for the awesome series
    // Check it out here: https://youtu.be/yCNUP2NAt-A

    public static final CreativeModeTab GROUP = FabricItemGroupBuilder.build(new ResourceLocation("medievalweapons", "group"), () -> new ItemStack(ItemInit.DIAMOND_FRANCISCA_HT_ITEM));

    public static final ResourceLocation ID(String path)
    {
        return new ResourceLocation("medievalweapons", path);
    }

    @Override
    public void onInitialize()
    {
        ConfigInit.init();
        ItemInit.init();
        EffectInit.init();
        EntityInit.init();
        ParticleInit.init();
        PlayerPacket.init();
        SoundInit.init();
        TagInit.init();
    }

}

// You are LOVED!!!
// Jesus loves you unconditionally!