package net.devoev.vanilla_cubed.screen

import net.devoev.vanilla_cubed.block.entity.ModBeaconBlockEntity
import net.devoev.vanilla_cubed.block.entity.beacon_upgrade.BeaconUpgrade
import net.devoev.vanilla_cubed.block.entity.beacon_upgrade.BeaconUpgrades
import net.minecraft.screen.PropertyDelegate

/**
 * [ModBeaconBlockEntity.totalLevels] stored at indices [sTOTAL_LEVELS_RANGE].
 */
var PropertyDelegate.totalLevels: IntArray
    get() = TOTAL_LEVELS_RANGE.map { i -> get(i) }.toIntArray()
    set(value) { TOTAL_LEVELS_RANGE.forEach { i -> set(i,value[i]) } }

/**
 * [ModBeaconBlockEntity.remainingLevels] stored at indices [REMAINING_LEVELS_RANGE].
 */
var PropertyDelegate.remainingLevels: IntArray
    get() = REMAINING_LEVELS_RANGE.map { i -> get(i) }.toIntArray()
    set(value) { REMAINING_LEVELS_RANGE.forEach { i -> set(i,value[i - REMAINING_LEVELS_RANGE.first]) } }

/**
 * [ModBeaconBlockEntity.upgrades] stored at indices [UPGRADE_RANGE].
 */
var PropertyDelegate.upgrades: List<BeaconUpgrade?>
    get() = UPGRADE_RANGE.map { i -> BeaconUpgrades[get(i)] }
    set(value) { UPGRADE_RANGE.forEach { i -> set(i, BeaconUpgrades.indexOf(value[i - UPGRADE_RANGE.first])) } }

/**
 * Index range for the [totalLevels] property.
 */
val TOTAL_LEVELS_RANGE = 0..3

/**
 * Index range for the [remainingLevels] property.
 */
val REMAINING_LEVELS_RANGE = 4..7

/**
 * The range for the [upgrades] property.
 */
val UPGRADE_RANGE = 8..23

/**
 * The empty [upgrades] list of `null` values.
 */
val UPGRADES_EMPTY: List<BeaconUpgrade?> = List(UPGRADE_RANGE.count()) { null }