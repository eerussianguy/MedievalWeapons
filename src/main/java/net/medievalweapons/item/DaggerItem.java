package net.medievalweapons.item;

import java.util.UUID;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;

import net.minecraftforge.common.ForgeMod;

public class DaggerItem extends SwordItem
{
    private static final UUID ATTACK_BONUS_MODIFIER_ID = UUID.fromString("fbd4e4e4-62f7-4108-9be3-eb6781231298");
    private static final AttributeModifier ATTACK_BONUS_MODIFIER = new AttributeModifier(ATTACK_BONUS_MODIFIER_ID, "Sneaking attack bonus", 2.0D, AttributeModifier.Operation.ADDITION);
    private final Tier material;
    private final float attackDamage;
    public final Multimap<Attribute, AttributeModifier> attributeModifiers;

    public DaggerItem(Tier material, int attackDamage, float attackSpeed, Properties settings)
    {
        super(material, attackDamage, attackSpeed, settings);
        this.material = material;
        this.attackDamage = attackDamage + material.getAttackDamageBonus();
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Tool modifier", this.attackDamage, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Tool modifier", attackSpeed, AttributeModifier.Operation.ADDITION));
        builder.put(ForgeMod.ATTACK_RANGE.get(), new AttributeModifier("Attack range", -1.0D, AttributeModifier.Operation.ADDITION));
        this.attributeModifiers = builder.build();
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot equipmentSlot)
    {
        return equipmentSlot == EquipmentSlot.MAINHAND ? attributeModifiers : super.getDefaultAttributeModifiers(equipmentSlot);
    }

    @Override
    public Tier getTier()
    {
        return this.material;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected)
    {
        if (entity instanceof Player player && !world.isClientSide)
        {
            boolean extra = player.isShiftKeyDown() || player.hasEffect(MobEffects.INVISIBILITY);
            if (selected && extra && !player.getAttributes().hasModifier(Attributes.ATTACK_DAMAGE, ATTACK_BONUS_MODIFIER_ID))
            {
                AttributeInstance entityAttributeInstance = player.getAttribute(Attributes.ATTACK_DAMAGE);
                entityAttributeInstance.addTransientModifier(ATTACK_BONUS_MODIFIER);
            }
            else if (player.getAttributes().hasModifier(Attributes.ATTACK_DAMAGE, ATTACK_BONUS_MODIFIER_ID) && !extra)
            {
                player.getAttribute(Attributes.ATTACK_DAMAGE).removeModifier(ATTACK_BONUS_MODIFIER_ID);
            }
        }
    }

}