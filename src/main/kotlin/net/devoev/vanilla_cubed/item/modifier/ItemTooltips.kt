package net.devoev.vanilla_cubed.item.modifier

import net.devoev.vanilla_cubed.text.translatableTextOf
import net.minecraft.screen.ScreenTexts
import net.minecraft.text.MutableText
import net.minecraft.text.Text
import net.minecraft.util.Formatting

/**
 * Tooltip for modifiers, that give an effect for a full set of armor.
 */
private val WHEN_FULL_ARMOR_TEXT: MutableText = translatableTextOf("modifier", "when_full_armor_tooltip").formatted(Formatting.GRAY)

/**
 * Tooltip for modifiers that give passive effects for each item.
 */
private val MODIFIER_TEXT: MutableText = translatableTextOf("modifier", "modifier_tooltip").formatted(Formatting.GRAY)

/**
 * Creates a list of a [text] and the [subtexts].
 */
fun subTextOf(text: Text, vararg subtexts: Text): List<Text> {
    return listOf(text.copy().append(":")) +
    subtexts.map { ScreenTexts.space().append(it) }
}

/**
 * Creates the "when full armor" tooltip text for the given [text].
 */
fun whenFullArmorTextOf(text: Text) = subTextOf(WHEN_FULL_ARMOR_TEXT, text)

/**
 * Creates the "modifier" tooltip text for the given [text].
 */
fun modifierTextOf(text: Text) = subTextOf(MODIFIER_TEXT, text)