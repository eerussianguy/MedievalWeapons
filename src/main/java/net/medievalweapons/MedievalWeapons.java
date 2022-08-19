package net.medievalweapons;

import java.util.function.Supplier;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;

import net.dries007.tfc.common.capabilities.food.FoodCapability;
import net.medievalweapons.client.ClientEvents;
import net.medievalweapons.client.ClientForgeEvents;
import net.medievalweapons.config.MedievalConfig;
import net.medievalweapons.effect.EffectInit;
import net.medievalweapons.entity.EntityInit;
import net.medievalweapons.init.*;
import net.medievalweapons.network.MedievalPacketHandler;

@Mod(MedievalWeapons.MOD_ID)
public final class MedievalWeapons
{
    public static final String MOD_ID = "medievalweapons";

    public static final CreativeModeTab GROUP = MedievalItemGroup.WEAPONS;

    public MedievalWeapons()
    {
        final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ItemInit.ITEMS.register(bus);
        EntityInit.ENTITIES.register(bus);
        EffectInit.EFFECTS.register(bus);
        SoundInit.SOUNDS.register(bus);

        if (FMLEnvironment.dist == Dist.CLIENT)
        {
            ClientEvents.init();
            ClientForgeEvents.init();
        }

        ForgeEvents.init();
        MedievalConfig.init();
        MedievalPacketHandler.init();
        TagInit.init();
    }

    private static class MedievalItemGroup extends CreativeModeTab
    {
        public static final MedievalItemGroup WEAPONS = new MedievalItemGroup(MOD_ID, () -> new ItemStack(ItemInit.WOODEN_FRANCISCA_LT_ITEM.get()));

        private final Lazy<ItemStack> iconStack;

        private MedievalItemGroup(String label, Supplier<ItemStack> iconSupplier)
        {
            super(MOD_ID + "." + label);
            this.iconStack = Lazy.of(() -> FoodCapability.setStackNonDecaying(iconSupplier.get()));
        }

        @Override
        public ItemStack makeIcon()
        {
            return iconStack.get();
        }
    }

}
