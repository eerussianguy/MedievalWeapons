package net.medievalweapons.client;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import net.medievalweapons.MUtil;
import net.medievalweapons.client.model.*;
import net.medievalweapons.client.renderer.FranciscaRenderer;
import net.medievalweapons.entity.EntityInit;
import net.medievalweapons.init.ItemInit;

public final class ClientEvents
{
    public static void init()
    {
        final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        bus.addListener(ClientEvents::clientSetup);
        bus.addListener(ClientEvents::onEntityRenderers);
        bus.addListener(ClientEvents::onLayerRegister);

    }

    public static void clientSetup(FMLClientSetupEvent event)
    {
        event.enqueueWork(() -> {
            ItemProperties.register(ItemInit.LONG_BOW_ITEM.get(), new ResourceLocation("pull"), (stack, world, entity, seed) -> {
                if (entity == null)
                {
                    return 0.0F;
                }
                else
                {
                    return entity.getUseItem() != stack ? 0.0F : (float) (stack.getUseDuration() - entity.getUseItemRemainingTicks()) / 50.0F;
                }
            });
            ItemProperties.register(ItemInit.LONG_BOW_ITEM.get(), new ResourceLocation("pulling"), (stack, world, entity, seed) -> {
                return entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F;
            });
            ItemProperties.register(ItemInit.RECURVE_BOW_ITEM.get(), new ResourceLocation("pull"), (stack, world, entity, seed) -> {
                if (entity == null)
                {
                    return 0.0F;
                }
                else
                {
                    return entity.getUseItem() != stack ? 0.0F : (float) (stack.getUseDuration() - entity.getUseItemRemainingTicks()) / 16.0F;
                }
            });
            ItemProperties.register(ItemInit.RECURVE_BOW_ITEM.get(), new ResourceLocation("pulling"), (stack, world, entity, seed) -> {
                return entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F;
            });
        });
    }

    public static void onEntityRenderers(EntityRenderersEvent.RegisterRenderers event)
    {
        event.registerEntityRenderer(EntityInit.THROWN_FRANCISCA_LT.get(), ctx -> new FranciscaRenderer(ctx, false));
        event.registerEntityRenderer(EntityInit.THROWN_FRANCISCA_HT.get(), ctx -> new FranciscaRenderer(ctx, true));
        // jav would go here
    }

    public static void onLayerRegister(EntityRenderersEvent.RegisterLayerDefinitions event)
    {
        event.registerLayerDefinition(modelIdentifier("big_axe"), BigAxeModel::getTexturedModelData);
        event.registerLayerDefinition(modelIdentifier("francisca_ht"), FranciscaHTModel::getTexturedModelData);
        event.registerLayerDefinition(modelIdentifier("francisca_lit"), FranciscaLTModel::getTexturedModelData);
        event.registerLayerDefinition(modelIdentifier("javelin"), MedievalJavelinModel::getTexturedModelData);
        event.registerLayerDefinition(modelIdentifier("lance"), LanceModel::getTexturedModelData);
        event.registerLayerDefinition(modelIdentifier("mace"), MaceModel::getTexturedModelData);
        event.registerLayerDefinition(modelIdentifier("thalleous_sword"), ThalleousSwordModel::getTexturedModelData);
    }

    public static ModelLayerLocation modelIdentifier(String name, String part)
    {
        return new ModelLayerLocation(MUtil.identifier(name), part);
    }

    public static ModelLayerLocation modelIdentifier(String name)
    {
        return modelIdentifier(name, "main");
    }

    public static ResourceLocation texture(ItemStack stack)
    {
        return MUtil.identifier("textures/entity/" + stack.getItem() + ".png");
    }
}
