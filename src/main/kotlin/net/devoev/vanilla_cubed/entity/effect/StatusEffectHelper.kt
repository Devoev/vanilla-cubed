package net.devoev.vanilla_cubed.entity.effect

import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectCategory
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import kotlin.random.Random
import kotlin.random.nextInt
import kotlin.reflect.KVisibility
import kotlin.reflect.full.staticProperties

/**
 * An object with helper functions for [StatusEffects][StatusEffect].
 */
object StatusEffectHelper {

    /**
     * A list of all [StatusEffects][StatusEffect].
     */
    val all: List<StatusEffect> = StatusEffects::class.staticProperties
        .filter { it.visibility == KVisibility.PUBLIC }
        .map { it.get() }
        .filterIsInstance<StatusEffect>()

    private val harmful = all.filter { it.category == StatusEffectCategory.HARMFUL }
    private val beneficial = all.filter { it.category == StatusEffectCategory.BENEFICIAL }
    private val neutral = all.filter { it.category == StatusEffectCategory.NEUTRAL }

    /**
     * Returns a random [StatusEffectInstance] from the given [list].
     * @param durationRange The range of values for the duration of the effect instance.
     * @param amplifierRange The range of values for the amplifier of the effect instance.
     */
    private fun randomInstance(list: List<StatusEffect>, durationRange: IntRange, amplifierRange: IntRange): StatusEffectInstance {
        val duration = Random.nextInt(durationRange)
        val amplifier = Random.nextInt(amplifierRange)
        return StatusEffectInstance(list.random(), duration, amplifier)
    }

    /**
     * Returns a random [StatusEffectInstance].
     */
    fun random(durationRange: IntRange, amplifierRange: IntRange) = randomInstance(all, durationRange, amplifierRange)

    /**
     * Returns a random [harmful][StatusEffectCategory.HARMFUL] [StatusEffectInstance].
     */
    fun randomHarmful(durationRange: IntRange, amplifierRange: IntRange) = randomInstance(harmful, durationRange, amplifierRange)

    /**
     * Returns a random [beneficial][StatusEffectCategory.BENEFICIAL] [StatusEffectInstance].
     */
    fun randomBeneficial(durationRange: IntRange, amplifierRange: IntRange) = randomInstance(beneficial, durationRange, amplifierRange)

    /**
     * Returns a random [neutral][StatusEffectCategory.NEUTRAL] [StatusEffectInstance].
     */
    fun randomNeutral(durationRange: IntRange, amplifierRange: IntRange) = randomInstance(neutral, durationRange, amplifierRange)
}