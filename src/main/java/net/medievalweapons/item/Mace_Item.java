package net.medievalweapons.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;

public class Mace_Item extends SwordItem
{

    private final Tier material;
    private final float attackDamage;
    public final Multimap<Attribute, AttributeModifier> attributeModifiers;
    private final int addition;

    public Mace_Item(Tier toolMaterial, int attackDamage, float attackSpeed, int addition, Properties settings)
    {
        super(toolMaterial, attackDamage, attackSpeed, settings);
        this.material = toolMaterial;
        this.attackDamage = attackDamage + material.getAttackDamageBonus();
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Tool modifier", this.attackDamage, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Tool modifier", attackSpeed, AttributeModifier.Operation.ADDITION));
        builder.put(ReachEntityAttributes.REACH, new AttributeModifier("Attack range", -0.5D, AttributeModifier.Operation.ADDITION));
        builder.put(ReachEntityAttributes.ATTACK_RANGE, new AttributeModifier("Attack range", -0.5D, AttributeModifier.Operation.ADDITION));
        this.attributeModifiers = builder.build();
        this.addition = addition;
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

    public int getAddition()
    {
        return this.addition;
    }

}
