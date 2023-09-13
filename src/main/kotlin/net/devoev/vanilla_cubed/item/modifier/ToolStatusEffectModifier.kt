package net.devoev.vanilla_cubed.item.modifier

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.item.Item
import net.minecraft.screen.ScreenTexts
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
 * @param amplifierText The amplifier as a roman numeral.
 */
fun toolStatusEffectTextOf(effect: StatusEffect, amplifierText: String = ""): Text {
    return Text.translatable(effect.translationKey)
        .append(ScreenTexts.space())
        .append(amplifierText)
        .formatted(Formatting.DARK_PURPLE)
}