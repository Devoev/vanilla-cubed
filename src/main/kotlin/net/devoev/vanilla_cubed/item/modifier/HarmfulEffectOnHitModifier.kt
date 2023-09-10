package net.devoev.vanilla_cubed.item.modifier

import net.devoev.vanilla_cubed.entity.effect.StatusEffectHelper
import net.devoev.vanilla_cubed.item.tool.ModTools
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.item.Item
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import kotlin.random.Random

/**
 * Applies a random harmful [StatusEffectInstance] to a target with a given [probability].
 * @see ModTools.ANCIENT_GOLD
 */
fun harmfulEffectOnHitModifierOf(probability: Double, durationRange: IntRange, amplifierRange: IntRange)
    = PostHitModifier<Item> { _, target, attacker ->

    if (attacker.world.isClient) return@PostHitModifier
    val effect = StatusEffectHelper.randomHarmful(durationRange, amplifierRange)

    if (Random.nextDouble() < 1 - probability || effect.effectType.isInstant) return@PostHitModifier
    target.addStatusEffect(effect, attacker)
    target.world.playSound(
        null,
        target.blockPos,
        SoundEvents.ENTITY_SPLASH_POTION_BREAK,
        SoundCategory.PLAYERS,
        5f,
        0f
    )
}