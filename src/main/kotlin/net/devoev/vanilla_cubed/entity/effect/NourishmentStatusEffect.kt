package net.devoev.vanilla_cubed.entity.effect

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffectCategory
import net.minecraft.entity.player.PlayerEntity

/**
 * Passively regenerates hunger of players.
 */
class NourishmentStatusEffect : ModStatusEffect(StatusEffectCategory.BENEFICIAL, 0xaf5220) {

    override fun applyUpdateEffect(entity: LivingEntity?, amplifier: Int) {
        // TODO: Update implementation
        if (entity !is PlayerEntity) return
        entity.hungerManager.add(1, 1f)
    }

    override fun canApplyUpdateEffect(duration: Int, amplifier: Int): Boolean {
        // TODO: Update implementation
        val i = 25 shr amplifier
        return if (i > 0) {
            duration % i == 0
        } else true
    }

}