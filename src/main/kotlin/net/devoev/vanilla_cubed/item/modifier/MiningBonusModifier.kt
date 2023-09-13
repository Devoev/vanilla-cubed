package net.devoev.vanilla_cubed.item.modifier

import net.devoev.vanilla_cubed.entity.inCave
import net.devoev.vanilla_cubed.item.armor.ModArmor
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.text.Text

/**
 * Applies useful [effects][StatusEffectInstance] when underground.
 * @see ModArmor.AMETHYST
 */
val MiningBonusModifier = armorStatusEffectModifierOf(StatusEffects.HASTE, 300) andThen
        armorStatusEffectModifierOf(StatusEffects.NIGHT_VISION, 300) runIf
        { _, _, _, entity, _, _ -> entity.inCave }

//val MINING_BONUS_TEXT: Text = translatableTextOf("modifier", "mining_bonus").formatted(Formatting.BLUE)
val MINING_BONUS_TEXTS: Array<Text> = arrayOf(
    toolStatusEffectTextOf(StatusEffects.HASTE),
    toolStatusEffectTextOf(StatusEffects.NIGHT_VISION),
)