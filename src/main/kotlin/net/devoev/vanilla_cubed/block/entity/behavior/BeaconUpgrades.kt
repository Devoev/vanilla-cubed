package net.devoev.vanilla_cubed.block.entity.behavior

import net.devoev.vanilla_cubed.VanillaCubed
import net.devoev.vanilla_cubed.block.entity.behavior.BeaconUpgrades.DIAMOND
import net.devoev.vanilla_cubed.block.entity.behavior.BeaconUpgrades.EMERALD
import net.devoev.vanilla_cubed.block.entity.behavior.BeaconUpgrades.GOLD
import net.devoev.vanilla_cubed.block.entity.behavior.BeaconUpgrades.IRON
import net.devoev.vanilla_cubed.client.gui.screen.ingame.BEACON_UPGRADE_BUTTON_DATA_EMPTY
import net.devoev.vanilla_cubed.client.gui.screen.ingame.BeaconUpgradeButtonData
import net.devoev.vanilla_cubed.client.gui.screen.ingame.upgrade
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.text.Text
import net.minecraft.util.Identifier

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

    private val IRON = listOf<BeaconUpgradeButtonData>(
        Triple(StatusEffectUpgrade(StatusEffects.RESISTANCE), Text.empty(), textureId("iron_1.png")),
        BEACON_UPGRADE_BUTTON_DATA_EMPTY,
        BEACON_UPGRADE_BUTTON_DATA_EMPTY,
        Triple(StatusEffectUpgrade(StatusEffects.HASTE), Text.empty(), textureId("iron_4.png"))
    )

    private val GOLD = listOf<BeaconUpgradeButtonData>(
        Triple(StatusEffectUpgrade(StatusEffects.SPEED), Text.empty(), textureId("iron_1.png")),
        BEACON_UPGRADE_BUTTON_DATA_EMPTY,
        BEACON_UPGRADE_BUTTON_DATA_EMPTY,
        BEACON_UPGRADE_BUTTON_DATA_EMPTY
    )
    private val EMERALD = listOf<BeaconUpgradeButtonData>(
        BEACON_UPGRADE_BUTTON_DATA_EMPTY,
        BEACON_UPGRADE_BUTTON_DATA_EMPTY,
        BEACON_UPGRADE_BUTTON_DATA_EMPTY,
        BEACON_UPGRADE_BUTTON_DATA_EMPTY
    )

    private val DIAMOND = listOf<BeaconUpgradeButtonData>(
        Triple(StatusEffectUpgrade(StatusEffects.STRENGTH), Text.empty(), textureId("iron_1.png")),
        BEACON_UPGRADE_BUTTON_DATA_EMPTY,
        BEACON_UPGRADE_BUTTON_DATA_EMPTY,
        BEACON_UPGRADE_BUTTON_DATA_EMPTY
    )

    private val ALL: List<BeaconUpgradeButtonData> = IRON + GOLD + EMERALD + DIAMOND

    private val UPGRADES = ALL.map { it.upgrade }

    /**
     * Returns the [i]th beacon upgrade.
     */
    operator fun get(i: Int): BeaconUpgrade? = UPGRADES.getOrNull(i)

    /**
     * Returns the [BeaconUpgradeButtonData] of the [i]th upgrade.
     */
    fun getData(i: Int): BeaconUpgradeButtonData = ALL[i]

    /**
     * Returns the canonical index of the given [upgrade].
     */
    fun indexOf(upgrade: BeaconUpgrade?) = UPGRADES.indexOf(upgrade)

    /**
     * Creates the [Identifier] of the texture [name] located at the path `textures/gui/container/beacon_icons/`.
     */
    private fun textureId(name: String): Identifier = VanillaCubed.id("textures/gui/container/beacon_icons/$name")
}