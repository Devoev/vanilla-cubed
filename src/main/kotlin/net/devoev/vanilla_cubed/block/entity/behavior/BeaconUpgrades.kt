package net.devoev.vanilla_cubed.block.entity.behavior

import net.minecraft.entity.effect.StatusEffects

/**
 * All 16 beacon upgrades.
 */
object BeaconUpgrades {

    private val IRON = listOf<BeaconUpgrade>(
        StatusEffectUpgrade(StatusEffects.RESISTANCE)
    )

    private val GOLD = listOf<BeaconUpgrade>(
        StatusEffectUpgrade(StatusEffects.SPEED)
    )
    private val EMERALD = listOf<BeaconUpgrade>()

    private val DIAMOND = listOf<BeaconUpgrade>(
        StatusEffectUpgrade(StatusEffects.STRENGTH)
    )

    /**
     * Returns the [i]th beacon upgrade.
     *
     * Upgrades are sorted canonically:
     *
     * [IRON] -> [GOLD] -> [EMERALD] -> [DIAMOND].
     */
    operator fun get(i: Int): BeaconUpgrade {
        return (IRON + GOLD + EMERALD + DIAMOND)[i]
    }
}