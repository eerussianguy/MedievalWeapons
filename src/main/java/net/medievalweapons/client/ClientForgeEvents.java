package net.medievalweapons.client;

import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.event.FOVModifierEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;

import net.medievalweapons.init.ItemInit;

public class ClientForgeEvents
{
    public static void init()
    {
        IEventBus bus = MinecraftForge.EVENT_BUS;

        bus.addListener(ClientForgeEvents::onFOV);
    }

    public static void onFOV(FOVModifierEvent event)
    {
        Player player = event.getEntity();
        Item item = player.getUseItem().getItem();
        if (player.isUsingItem() && (item == ItemInit.LONG_BOW_ITEM.get() || item == ItemInit.RECURVE_BOW_ITEM.get()))
        {
            int ticks = player.getTicksUsingItem();
            float mod;
            mod = item == ItemInit.LONG_BOW_ITEM.get() ? ticks / 50f : ticks / 16f;

            mod = mod > 1 ? 1 : mod * mod;

            float fov = event.getFov() * (1f - mod * 0.15F);
            event.setNewfov(Mth.lerp(Minecraft.getInstance().options.fovEffectScale, 1f, fov));
        }
    }
}
