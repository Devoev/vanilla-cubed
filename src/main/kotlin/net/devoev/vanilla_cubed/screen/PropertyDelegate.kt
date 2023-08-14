package net.devoev.vanilla_cubed.screen

import net.devoev.vanilla_cubed.block.entity.ModBeaconBlockEntity
import net.devoev.vanilla_cubed.block.entity.beacon_upgrade.BeaconUpgrade
import net.devoev.vanilla_cubed.block.entity.beacon_upgrade.BeaconUpgrades
import net.minecraft.screen.PropertyDelegate

/**
 * [ModBeaconBlockEntity.totalLevels] stored at indices 0-3.
 */
var PropertyDelegate.totalLevels: IntArray
    get() = ModBeaconBlockEntity.TOTAL_LEVELS_RANGE.map { get(it) }.toIntArray()
    set(value) { value.forEachIndexed { i, v -> set(i,v) } }

/**
 * [ModBeaconBlockEntity.remainingLevels] stored at indices 4-7.
 */
var PropertyDelegate.remainingLevels: IntArray
    get() = ModBeaconBlockEntity.REMAINING_LEVELS_RANGE.map { get(it) }.toIntArray()
    set(value) { value.forEachIndexed { i, v -> set(i,v) } }

/**
 * [ModBeaconBlockEntity.upgrade] stored at index 4.
 */
var PropertyDelegate.upgrade: BeaconUpgrade?
    get() = BeaconUpgrades[get(ModBeaconBlockEntity.UPGRADE_IDX)]
    set(value) { set(ModBeaconBlockEntity.UPGRADE_IDX, BeaconUpgrades.indexOf(value)) }