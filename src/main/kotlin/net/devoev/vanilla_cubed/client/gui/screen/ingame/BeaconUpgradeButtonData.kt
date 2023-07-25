package net.devoev.vanilla_cubed.client.gui.screen.ingame

import net.devoev.vanilla_cubed.VanillaCubed
import net.devoev.vanilla_cubed.block.entity.behavior.BeaconUpgrade
import net.minecraft.text.Text
import net.minecraft.util.Identifier

/**
 * Data to create a [ModBeaconScreen.UpgradeButtonWidget].
 */
typealias BeaconUpgradeButtonData = Triple<BeaconUpgrade, Text, Identifier>

/**
 * [BeaconUpgrade] of the button.
 */
val BeaconUpgradeButtonData.upgrade: BeaconUpgrade
    get() = first

/**
 * Tooltip [Text] of the button.
 */
val BeaconUpgradeButtonData.tooltip: Text
    get() = second

/**
 * [Identifier] of the texture of the button.
 */
val BeaconUpgradeButtonData.texture: Identifier
    get() = third

/**
 * Empty [BeaconUpgradeButtonData].
 */
val BEACON_UPGRADE_BUTTON_DATA_EMPTY: BeaconUpgradeButtonData = Triple(BeaconUpgrade.EMPTY, Text.empty(), VanillaCubed.id(""))