package net.devoev.vanilla_cubed.client.gui.screen.ingame

import net.devoev.vanilla_cubed.VanillaCubed
import net.devoev.vanilla_cubed.block.entity.beacon_upgrade.BeaconUpgrade
import net.minecraft.text.Text
import net.minecraft.util.Identifier

/**
 * Data to create a [ModBeaconScreen.UpgradeButtonWidget].
 * @property upgrade [BeaconUpgrade] of the button.
 * @property tooltip Tooltip [Text] of the button.
 * @property texture [Identifier] of the texture of the button.
 */
data class BeaconUpgradeButtonData(
    val upgrade: BeaconUpgrade,
    val tooltip: Text,
    val texture: Identifier,
    val tier: BeaconUpgradeTier
) {
    companion object {
        /**
         * Empty [BeaconUpgradeButtonData].
         */
        val EMPTY = BeaconUpgradeButtonData(BeaconUpgrade.EMPTY, Text.empty(), VanillaCubed.id(""), BeaconUpgradeTier.EMPTY)
    }
}