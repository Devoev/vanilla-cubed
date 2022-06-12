package net.devoev.vanilla_cubed.item.behavior

import net.devoev.vanilla_cubed.entity.effect.StatusEffectHelper
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.ToolItem
import kotlin.random.Random

fun interface PostHitBehavior<in T : Item> {

    operator fun invoke(item: T, stack: ItemStack?, target: LivingEntity?, attacker: LivingEntity?): Boolean

    companion object {

        val DEFAULT = PostHitBehavior<Item> { _,_,_,_ -> false }

        /**
         * Applies a random harmful [StatusEffectInstance] to the given target with a 10% probability.
         */
        val APPLY_HARMFUL_EFFECT = PostHitBehavior<ToolItem> { _, _, target, _ ->
            val effect = StatusEffectHelper.randomHarmful(100..200, 0..2)
            if (Random.nextDouble() < 0.9 || effect.effectType.isInstant) return@PostHitBehavior false

            target?.addStatusEffect(effect)
            return@PostHitBehavior true
        }
    }
}