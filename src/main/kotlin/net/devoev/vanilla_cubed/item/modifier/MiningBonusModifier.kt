package net.devoev.vanilla_cubed.item.modifier

import net.devoev.vanilla_cubed.item.armor.ModArmor
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects

/**
 * Applies useful [effects][StatusEffectInstance] when underground.
 * @see ModArmor.AMETHYST
 */
val MiningBonusModifier = armorStatusEffectModifierOf(StatusEffects.HASTE, 300)
    .andThen(armorStatusEffectModifierOf(StatusEffects.NIGHT_VISION, 300))
    .andThen(armorStatusEffectModifierOf(StatusEffects.SPEED, 300))
//    .runIf { params -> params.entity?.inCave ?: false } // TODO: FIX runIf