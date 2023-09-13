package net.devoev.vanilla_cubed.entity.effect

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffectCategory
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.player.PlayerEntity

/**
 * Passively regenerates hunger of players.
 *
 * @see StatusEffects.REGENERATION
 */
class NourishmentStatusEffect : ModStatusEffect(StatusEffectCategory.BENEFICIAL, 0xaf5220) {

    override fun applyUpdateEffect(entity: LivingEntity?, amplifier: Int) {
        if (entity !is PlayerEntity || entity.world.isClient) return
        entity.hungerManager.add(1, 0f)
    }

    override fun canApplyUpdateEffect(duration: Int, amplifier: Int): Boolean {
        val i = 150 shr amplifier
        return i <= 0 || duration % i == 0
    }

}