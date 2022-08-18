package net.medievalweapons.init;

import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.resources.ResourceLocation;

public class ModelProviderInit {

    public static void init() {
        FabricModelPredicateProviderRegistry.register(ItemInit.LONG_BOW_ITEM, new ResourceLocation("pull"), (stack, world, entity, seed) -> {
            if (entity == null) {
                return 0.0F;
            } else {
                return entity.getUseItem() != stack ? 0.0F : (float) (stack.getUseDuration() - entity.getUseItemRemainingTicks()) / 50.0F;
            }
        });
        FabricModelPredicateProviderRegistry.register(ItemInit.LONG_BOW_ITEM, new ResourceLocation("pulling"), (stack, world, entity, seed) -> {
            return entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F;
        });
        FabricModelPredicateProviderRegistry.register(ItemInit.RECURVE_BOW_ITEM, new ResourceLocation("pull"), (stack, world, entity, seed) -> {
            if (entity == null) {
                return 0.0F;
            } else {
                return entity.getUseItem() != stack ? 0.0F : (float) (stack.getUseDuration() - entity.getUseItemRemainingTicks()) / 16.0F;
            }
        });
        FabricModelPredicateProviderRegistry.register(ItemInit.RECURVE_BOW_ITEM, new ResourceLocation("pulling"), (stack, world, entity, seed) -> {
            return entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F;
        });

    }

}