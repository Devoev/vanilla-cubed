package net.devoev.vanilla_cubed.item.behavior

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
class ApplyHarmfulEffectBehavior(private val probability: Double,
                                 private val durationRange: IntRange,
                                 private val amplifierRange: IntRange) : PostHitBehavior<Item> {

    override fun accept(item: Item, params: PostHitParams) {
        if (params.attacker!!.world.isClient) return
        val effect = StatusEffectHelper.randomHarmful(durationRange, amplifierRange)

        if (Random.nextDouble() < 1 - probability || effect.effectType.isInstant) return
        params.target?.addStatusEffect(effect, params.attacker)
        params.target?.world?.playSound(
            null,
            params.target.blockPos,
            SoundEvents.ENTITY_SPLASH_POTION_BREAK,
            SoundCategory.PLAYERS,
            5f,
            0f
        )
    }
}