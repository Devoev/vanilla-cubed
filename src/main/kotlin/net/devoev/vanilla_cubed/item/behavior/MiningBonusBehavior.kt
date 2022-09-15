package net.devoev.vanilla_cubed.item.behavior

import net.devoev.vanilla_cubed.item.armor.ModArmor
import net.devoev.vanilla_cubed.entity.inCave
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.item.ArmorItem

/**
 * Applies useful [effects][StatusEffectInstance] when underground.
 * @see ModArmor.AMETHYST
 */
val MiningBonusBehavior: InventoryTickBehavior<ArmorItem> = ApplyEffectBehavior(StatusEffects.HASTE, 300)
    .andThen(ApplyEffectBehavior(StatusEffects.NIGHT_VISION, 300))
    .andThen(ApplyEffectBehavior(StatusEffects.SPEED, 300))
    .runIf { params -> params.entity?.inCave ?: false }