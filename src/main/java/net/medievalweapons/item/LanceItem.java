package net.medievalweapons.item;

import java.util.UUID;
import java.util.function.Consumer;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;

import net.minecraftforge.client.IItemRenderProperties;
import net.minecraftforge.common.util.NonNullLazy;

import net.medievalweapons.client.renderer.LanceRenderer;

public class LanceItem extends SwordItem
{
    private static final UUID ATTACK_BONUS_MODIFIER_ID = UUID.fromString("fbd4e4e4-62f7-4108-9be3-eb6781231298");
    private static final AttributeModifier ATTACK_BONUS_MODIFIER = new AttributeModifier(ATTACK_BONUS_MODIFIER_ID, "Sneaking attack bonus", 2.0D, AttributeModifier.Operation.ADDITION);

    public LanceItem(Tier material, int attackDamage, float attackSpeed, Properties settings)
    {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected)
    {
        if (entity instanceof Player player && !world.isClientSide)
        {
            if (selected && player.isPassenger() && !player.getAttributes().hasModifier(Attributes.ATTACK_DAMAGE, ATTACK_BONUS_MODIFIER_ID))
            {
                AttributeInstance instance = player.getAttribute(Attributes.ATTACK_DAMAGE);
                instance.addTransientModifier(ATTACK_BONUS_MODIFIER);
            }
            else if (player.getAttributes().hasModifier(Attributes.ATTACK_DAMAGE, ATTACK_BONUS_MODIFIER_ID) && !player.isPassenger())
            {
                player.getAttribute(Attributes.ATTACK_DAMAGE).removeModifier(ATTACK_BONUS_MODIFIER_ID);
            }
        }
    }

    @Override
    public void initializeClient(Consumer<IItemRenderProperties> consumer)
    {
        consumer.accept(new IItemRenderProperties() {
            private final NonNullLazy<LanceRenderer> renderer = NonNullLazy.of(LanceRenderer::new);
            @Override
            public BlockEntityWithoutLevelRenderer getItemStackRenderer()
            {
                return renderer.get();
            }
        });
    }

}
