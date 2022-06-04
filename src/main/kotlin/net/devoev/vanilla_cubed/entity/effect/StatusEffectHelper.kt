package net.devoev.vanilla_cubed.entity.effect

import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectCategory
import net.minecraft.entity.effect.StatusEffects
import kotlin.reflect.full.staticProperties

/**
 * An object with helper functions for [StatusEffects][StatusEffect].
 */
object StatusEffectHelper {

    /**
     * A list of all [StatusEffects][StatusEffect].
     */
    val all: List<StatusEffect> =
        StatusEffects::class.staticProperties.map { it.get() }.filterIsInstance<StatusEffect>()

    val harmful = all.filter { it.category == StatusEffectCategory.HARMFUL }
    val beneficial = all.filter { it.category == StatusEffectCategory.BENEFICIAL }
    val neutral = all.filter { it.category == StatusEffectCategory.NEUTRAL }
}