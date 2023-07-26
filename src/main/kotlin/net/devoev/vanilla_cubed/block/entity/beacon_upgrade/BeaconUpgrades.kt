package net.devoev.vanilla_cubed.block.entity.beacon_upgrade

import net.devoev.vanilla_cubed.VanillaCubed
import net.devoev.vanilla_cubed.block.entity.beacon_upgrade.BeaconUpgrades.DIAMOND
import net.devoev.vanilla_cubed.block.entity.beacon_upgrade.BeaconUpgrades.EMERALD
import net.devoev.vanilla_cubed.block.entity.beacon_upgrade.BeaconUpgrades.GOLD
import net.devoev.vanilla_cubed.block.entity.beacon_upgrade.BeaconUpgrades.IRON
import net.devoev.vanilla_cubed.client.gui.screen.ingame.BeaconUpgradeButtonData
import net.devoev.vanilla_cubed.client.gui.screen.ingame.diamondTierOf
import net.devoev.vanilla_cubed.client.gui.screen.ingame.goldTierOf
import net.devoev.vanilla_cubed.client.gui.screen.ingame.ironTierOf
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

    private val IRON = listOf(
        BeaconUpgradeButtonData(
            StatusEffectUpgrade(StatusEffects.RESISTANCE),
            tooltipOf("iron1"),
            textureOf("iron1.png"),
            ironTierOf(1)
        ),
        BeaconUpgradeButtonData.EMPTY,
        BeaconUpgradeButtonData.EMPTY,
        BeaconUpgradeButtonData(
            StatusEffectUpgrade(StatusEffects.HASTE),
            tooltipOf("iron4"),
            textureOf("iron4.png"),
            ironTierOf(4)
        )
    )

    private val GOLD = listOf(
        BeaconUpgradeButtonData(
            StatusEffectUpgrade(StatusEffects.SPEED),
            tooltipOf("gold1"),
            textureOf("iron1.png"),
            goldTierOf(1)
        ),
        BeaconUpgradeButtonData.EMPTY,
        BeaconUpgradeButtonData.EMPTY,
        BeaconUpgradeButtonData.EMPTY
    )
    private val EMERALD = listOf(
        BeaconUpgradeButtonData.EMPTY,
        BeaconUpgradeButtonData.EMPTY,
        BeaconUpgradeButtonData.EMPTY,
        BeaconUpgradeButtonData.EMPTY
    )

    private val DIAMOND = listOf(
        BeaconUpgradeButtonData(StatusEffectUpgrade(StatusEffects.STRENGTH),
            tooltipOf("diamond1"),
            textureOf("iron1.png"),
            diamondTierOf(1)
        ),
        BeaconUpgradeButtonData.EMPTY,
        BeaconUpgradeButtonData.EMPTY,
        BeaconUpgradeButtonData.EMPTY
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
    fun dataAt(i: Int): BeaconUpgradeButtonData = ALL[i]

    /**
     * Returns the canonical index of the given [upgrade].
     */
    fun indexOf(upgrade: BeaconUpgrade?) = UPGRADES.indexOf(upgrade)

    /**
     * Creates the [Identifier] of the texture [name] located at the path `textures/gui/container/beacon_icons/`.
     */
    private fun textureOf(name: String): Identifier = VanillaCubed.id("textures/gui/container/beacon_icons/$name")

    /**
     * Creates the translatable tooltip with the text [name] located at the path `block.vanilla_cubed.beacon`.
     */
    private fun tooltipOf(name: String): Text = Text.translatable("block.vanilla_cubed.beacon.$name")
}