package net.medievalweapons.item;

import java.util.function.Consumer;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.*;

import net.minecraftforge.client.IItemRenderProperties;
import net.minecraftforge.common.util.NonNullLazy;

import net.medievalweapons.client.renderer.ThalleousSwordRenderer;

public class ThalleousSwordItem extends TwoHandedSwordItem
{
    public ThalleousSwordItem(Tier toolMaterial, int attackDamage, float attackSpeed, Properties settings)
    {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public void initializeClient(Consumer<IItemRenderProperties> consumer)
    {
        consumer.accept(new IItemRenderProperties() {
            private final NonNullLazy<ThalleousSwordRenderer> renderer = NonNullLazy.of(ThalleousSwordRenderer::new);
            @Override
            public BlockEntityWithoutLevelRenderer getItemStackRenderer()
            {
                return renderer.get();
            }
        });
    }

}
