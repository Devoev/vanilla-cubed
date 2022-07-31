package net.devoev.vanilla_cubed.item.behavior

import net.devoev.vanilla_cubed.item.armor.ModArmor
import net.devoev.vanilla_cubed.util.isInCave
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.item.ArmorItem

/**
 * Applies useful [effects][StatusEffectInstance] when underground.
 * @see ModArmor.AMETHYST
 */
val MiningBonusBehavior = InventoryTickBehavior.from(
    ApplyEffectBehavior(StatusEffects.HASTE, 300)
        .andThen(ApplyEffectBehavior(StatusEffects.NIGHT_VISION, 300))
        .andThen(ApplyEffectBehavior(StatusEffects.SATURATION, 300))
        .runIf { params -> params.entity?.isInCave() ?: false }
)