package net.devoev.vanilla_cubed.item

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
 * Tooltip for modifiers, that give an effect if held in the hand.
 */
private val WHEN_IN_HAND_TEXT: MutableText = translatableTextOf("modifier", "when_in_hand_tooltip").formatted(Formatting.GRAY)

/**
 * Tooltip for modifiers, that give an effect if player is on fire.
 */
private val WHEN_ON_FIRE_TEXT: MutableText = translatableTextOf("modifier", "when_on_fire_tooltip").formatted(Formatting.GRAY)

/**
 * Tooltip for modifiers, that give an effect if player is underground.
 */
private val WHEN_UNDERGROUND_TEXT: MutableText = translatableTextOf("modifier", "when_underground_tooltip").formatted(Formatting.GRAY)

/**
 * Tooltip for modifiers that give passive effects for each item.
 */
private val MODIFIER_TEXT: MutableText = translatableTextOf("modifier", "modifier_tooltip").formatted(Formatting.GRAY)

/**
 * Creates a list of a [text] and the [subtexts].
 * @param addSeparator Whether to add a [ScreenTexts.EMPTY] above.
 */
fun subTextOf(text: Text, addSeparator: Boolean = false, vararg subtexts: Text): List<Text> {
    return (if (addSeparator) listOf(ScreenTexts.EMPTY) else emptyList<Text>()) +
            text.copy().append(":") +
            subtexts.map { ScreenTexts.space().append(it) }
}

/**
 * Creates the "when full armor" tooltip text for the given [texts].
 */
fun whenFullArmorTextOf(addSeparator: Boolean, vararg texts: Text) = subTextOf(WHEN_FULL_ARMOR_TEXT, addSeparator, *texts)

/**
 * Creates the "when in hand" tooltip text for the given [texts].
 */
fun whenInHandTextOf(addSeparator: Boolean, vararg texts: Text) = subTextOf(WHEN_IN_HAND_TEXT, addSeparator, *texts)

/**
 * Creates the "when on fire" tooltip text for the given [texts].
 */
fun whenOnFireTextOf(addSeparator: Boolean, vararg texts: Text) = subTextOf(WHEN_ON_FIRE_TEXT, addSeparator, *texts)

/**
 * Creates the "when underground" tooltip text for the given [texts].
 */
fun whenUndergroundTextOf(addSeparator: Boolean, vararg texts: Text) = subTextOf(WHEN_UNDERGROUND_TEXT, addSeparator, *texts)

/**
 * Creates the "modifier" tooltip text for the given [texts].
 */
fun modifierTextOf(addSeparator: Boolean, vararg texts: Text) = subTextOf(MODIFIER_TEXT, addSeparator, *texts)