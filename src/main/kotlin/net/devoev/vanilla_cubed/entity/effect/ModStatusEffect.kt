package net.devoev.vanilla_cubed.entity.effect

import net.minecraft.entity.attribute.EntityAttribute
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectCategory
import net.minecraft.util.math.MathHelper

/**
 * A modded [StatusEffect].
 */
class ModStatusEffect(category: StatusEffectCategory, color: Int) : StatusEffect(category, color)

/**
 * Creates a [StatusEffect] that modifies the given entity [attribute].
 * @param category Category of the effect.
 * @param color Hexadecimal color.
 * @param attribute Entity attribute that gets modified by this status effect.
 * @param amount By how much the status effect changes the [attribute].
 * @param operation The operation by which the [attribute] is changed.
 * @param uuid An optional uuid of the attribute modifier. By default, a random ID is generated.
 */
fun attributeStatusEffectOf(
    category: StatusEffectCategory,
    color: Int,
    attribute: EntityAttribute,
    amount: Double,
    operation: EntityAttributeModifier.Operation,
    uuid: String = MathHelper.randomUuid().toString()
): StatusEffect {
    return ModStatusEffect(category, color).addAttributeModifier(attribute, uuid, amount, operation)
}