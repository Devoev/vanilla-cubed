package net.devoev.vanilla_cubed.item.modifier

import net.devoev.vanilla_cubed.entity.effect.StatusEffectHelper
import net.devoev.vanilla_cubed.item.tool.ModTools
import net.devoev.vanilla_cubed.text.translatableTextOf
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.item.Item
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.text.Text
import net.minecraft.util.Formatting
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
        SoundCategory.NEUTRAL,
        1f,
        Random.nextFloat() * 0.1f + 0.9f,
    )
}

val HARMFUL_EFFECT_ON_HIT_TEXT: Text = translatableTextOf("modifier", "harmful_effects_on_hit").formatted(Formatting.BLUE)