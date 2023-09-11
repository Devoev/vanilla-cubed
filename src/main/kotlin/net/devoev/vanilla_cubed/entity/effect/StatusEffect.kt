package net.devoev.vanilla_cubed.entity.effect

import net.devoev.vanilla_cubed.mixin.StatusEffectMixin
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffects

/**
 * Grants the player full health from the [StatusEffects.HEALTH_BOOST] effect instead of empty ones.
 *
 * @see StatusEffectMixin.applyHealthBoost
 */
fun applyHealthBoost(effect: StatusEffect, entity: LivingEntity) {
    if (effect == StatusEffects.HEALTH_BOOST) {
        entity.health += entity.maxHealth - 20
    }
}