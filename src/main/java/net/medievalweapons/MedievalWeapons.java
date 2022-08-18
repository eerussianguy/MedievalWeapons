package net.medievalweapons;

import java.util.function.Supplier;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import net.dries007.tfc.common.capabilities.food.FoodCapability;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.medievalweapons.config.MedievalConfig;
import net.medievalweapons.init.*;
import net.medievalweapons.network.PlayerPacket;

@Mod(MedievalWeapons.MOD_ID)
public final class MedievalWeapons
{
    public static final String MOD_ID = "medievalweapons";

    public static final CreativeModeTab GROUP = MedievalItemGroup.WEAPONS;

    public static final ResourceLocation identifier(String path)
    {
        return new ResourceLocation(MOD_ID, path);
    }

    public MedievalWeapons()
    {
        final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ItemInit.ITEMS.register(bus);

        MedievalConfig.init();
        EffectInit.init();
        EntityInit.init();
        ParticleInit.init();
        PlayerPacket.init();
        SoundInit.init();
        TagInit.init();
    }

    private static class MedievalItemGroup extends CreativeModeTab
    {
        public static final MedievalItemGroup WEAPONS = new MedievalItemGroup(MOD_ID, () -> new ItemStack(ItemInit.DIAMOND_FRANCISA_HT_ITEM));

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
