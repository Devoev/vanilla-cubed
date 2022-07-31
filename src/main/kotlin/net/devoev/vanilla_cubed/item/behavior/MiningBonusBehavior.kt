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
object MiningBonusBehavior : InventoryTickBehavior<ArmorItem>, DelegateBehavior<ArmorItem, InventoryTickBehavior.Params, Unit>(
    ConditionalBehavior.build(
        CompositionalBehavior.build(
            ApplyEffectBehavior(StatusEffects.HASTE, 300),
            ApplyEffectBehavior(StatusEffects.NIGHT_VISION, 300),
            ApplyEffectBehavior(StatusEffects.SATURATION, 300)
        )
    ) { params -> params.entity?.isInCave() ?: false }
)