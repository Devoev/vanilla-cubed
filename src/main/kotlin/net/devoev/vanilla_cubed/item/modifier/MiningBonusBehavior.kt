package net.devoev.vanilla_cubed.item.modifier

import net.devoev.vanilla_cubed.entity.inCave
import net.devoev.vanilla_cubed.item.armor.ModArmor
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.item.ArmorItem

/**
 * Applies useful [effects][StatusEffectInstance] when underground.
 * @see ModArmor.AMETHYST
 */
val MiningBonusBehavior: InventoryTickModifier<ArmorItem> = ArmorStatusEffectModifier(StatusEffects.HASTE, 300)
    .andThen(ArmorStatusEffectModifier(StatusEffects.NIGHT_VISION, 300))
    .andThen(ArmorStatusEffectModifier(StatusEffects.SPEED, 300))
    .runIf { params -> params.entity?.inCave ?: false }