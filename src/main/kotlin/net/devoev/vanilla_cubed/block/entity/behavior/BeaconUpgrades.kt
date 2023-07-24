package net.devoev.vanilla_cubed.block.entity.behavior

import net.devoev.vanilla_cubed.block.entity.behavior.BeaconUpgrades.DIAMOND
import net.devoev.vanilla_cubed.block.entity.behavior.BeaconUpgrades.EMERALD
import net.devoev.vanilla_cubed.block.entity.behavior.BeaconUpgrades.GOLD
import net.devoev.vanilla_cubed.block.entity.behavior.BeaconUpgrades.IRON
import net.minecraft.entity.effect.StatusEffects

/**
 * All 16 beacon upgrades sorted canonically as
 *
 * [IRON] -> [GOLD] -> [EMERALD] -> [DIAMOND].
 *
 * @property IRON Iron upgrades.
 * @property GOLD Gold upgrades.
 * @property EMERALD Emerald upgrades.
 * @property DIAMOND Diamond upgrades.
 */
object BeaconUpgrades {

    private val IRON = listOf(
        StatusEffectUpgrade(StatusEffects.RESISTANCE),
        BeaconUpgrade.EMPTY,
        BeaconUpgrade.EMPTY,
        StatusEffectUpgrade(StatusEffects.HASTE)
    )

    private val GOLD = listOf(
        StatusEffectUpgrade(StatusEffects.SPEED),
        BeaconUpgrade.EMPTY,
        BeaconUpgrade.EMPTY,
        BeaconUpgrade.EMPTY
    )
    private val EMERALD = listOf(
        BeaconUpgrade.EMPTY,
        BeaconUpgrade.EMPTY,
        BeaconUpgrade.EMPTY,
        BeaconUpgrade.EMPTY
    )

    private val DIAMOND = listOf(
        StatusEffectUpgrade(StatusEffects.STRENGTH),
        BeaconUpgrade.EMPTY,
        BeaconUpgrade.EMPTY,
        BeaconUpgrade.EMPTY
    )

    private val UPGRADES = IRON + GOLD + EMERALD + DIAMOND

    /**
     * Returns the [i]th beacon upgrade.
     */
    operator fun get(i: Int): BeaconUpgrade = UPGRADES[i]

    /**
     * Returns the canonical index of the given [upgrade].
     */
    fun indexOf(upgrade: BeaconUpgrade) = UPGRADES.indexOf(upgrade)
}