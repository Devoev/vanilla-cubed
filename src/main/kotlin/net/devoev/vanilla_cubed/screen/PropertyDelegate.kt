package net.devoev.vanilla_cubed.screen

import net.devoev.vanilla_cubed.block.entity.ModBeaconBlockEntity
import net.devoev.vanilla_cubed.block.entity.beacon_upgrade.BeaconUpgrade
import net.devoev.vanilla_cubed.block.entity.beacon_upgrade.BeaconUpgrades
import net.minecraft.screen.PropertyDelegate

/**
 * [ModBeaconBlockEntity.levels] stored at indices 0-3.
 */
var PropertyDelegate.levels: IntArray
    get() = intArrayOf(get(0), get(1), get(2), get(4))
    set(value) { value.forEachIndexed { i, v -> set(i,v) } }

/**
 * [ModBeaconBlockEntity.upgrade] stored at index 4.
 */
var PropertyDelegate.upgrade: BeaconUpgrade?
    get() = BeaconUpgrades[get(4)]
    set(value) { set(4, BeaconUpgrades.indexOf(value)) }