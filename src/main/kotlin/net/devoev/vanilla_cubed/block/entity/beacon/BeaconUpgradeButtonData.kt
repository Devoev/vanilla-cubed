package net.devoev.vanilla_cubed.block.entity.beacon

import net.devoev.vanilla_cubed.block.entity.beacon.upgrades.BeaconUpgrade
import net.devoev.vanilla_cubed.client.gui.screen.ingame.ModBeaconScreen
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
)