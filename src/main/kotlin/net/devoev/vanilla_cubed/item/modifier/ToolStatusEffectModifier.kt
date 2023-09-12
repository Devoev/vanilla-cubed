package net.devoev.vanilla_cubed.item.modifier

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.item.Item
import net.minecraft.text.Text
import net.minecraft.util.Formatting

/**
 * A [ItemModifier] that applies the given [effect] to the player holding the tool.
 */
fun toolStatusEffectModifierOf(effect: StatusEffect, duration: Int = 0, amplifier: Int = 0)
    = InventoryTickModifier<Item> { stack, world, entity, _, selected ->

    if (!world.isClient && selected && entity is LivingEntity && entity.offHandStack != stack) {
        entity.addStatusEffect(StatusEffectInstance(effect, duration, amplifier, false, false))
    }
}

/**
 * Creates the tooltip text for the given [effect].
 * // TODO Fix translation key
 */
fun toolStatusEffectTextOf(effect: StatusEffect): Text = Text.translatable(effect.translationKey).formatted(Formatting.DARK_PURPLE)