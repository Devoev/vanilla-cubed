package net.devoev.vanilla_cubed.block.entity.beacon_upgrade

import net.devoev.vanilla_cubed.VanillaCubed
import net.devoev.vanilla_cubed.block.entity.beacon_upgrade.BeaconUpgrades.DIAMOND
import net.devoev.vanilla_cubed.block.entity.beacon_upgrade.BeaconUpgrades.EMERALD
import net.devoev.vanilla_cubed.block.entity.beacon_upgrade.BeaconUpgrades.GOLD
import net.devoev.vanilla_cubed.block.entity.beacon_upgrade.BeaconUpgrades.IRON
import net.devoev.vanilla_cubed.client.gui.screen.ingame.*
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
            StatusEffectUpgrade(StatusEffects.RESISTANCE, 0),
            tooltipOf("iron1"),
            textureOf("iron1.png"),
            ironTierOf(1)
        ),
        BeaconUpgradeButtonData(
            BeaconUpgrade.EMPTY,
            tooltipOf("iron2"),
            textureOf("iron2.png"),
            ironTierOf(2)
        ),
        BeaconUpgradeButtonData(
            BeaconUpgrade.EMPTY,
            tooltipOf("iron3"),
            textureOf("iron3.png"),
            ironTierOf(3)
        ),
        BeaconUpgradeButtonData(
            StatusEffectUpgrade(StatusEffects.HASTE, 1),
            tooltipOf("iron4"),
            textureOf("iron4.png"),
            ironTierOf(4)
        )
    )

    private val GOLD = listOf(
        BeaconUpgradeButtonData(
            StatusEffectUpgrade(StatusEffects.SPEED, 0) andThen StatusEffectUpgrade(StatusEffects.JUMP_BOOST, 0),
            tooltipOf("gold1"),
            textureOf("gold1.png"),
            goldTierOf(1)
        ),
        BeaconUpgradeButtonData(
            BeaconUpgrade.EMPTY,
            tooltipOf("gold2"),
            textureOf("gold2.png"),
            goldTierOf(2)
        ),
        BeaconUpgradeButtonData(
            BeaconUpgrade.EMPTY,
            tooltipOf("gold3"),
            textureOf("gold3.png"),
            goldTierOf(3)
        ),
        BeaconUpgradeButtonData(
            BeaconUpgrade.EMPTY,
            tooltipOf("gold4"),
            textureOf("gold4.png"),
            goldTierOf(4)
        ),
    )
    private val EMERALD = listOf(
        BeaconUpgradeButtonData(
            BeaconUpgrade.EMPTY,
            tooltipOf("emerald1"),
            textureOf("emerald1.png"),
            emeraldTierOf(1)
        ),
        BeaconUpgradeButtonData(
            BeaconUpgrade.EMPTY,
            tooltipOf("emerald2"),
            textureOf("emerald2.png"),
            emeraldTierOf(2)
        ),
        BeaconUpgradeButtonData(
            BeaconUpgrade.EMPTY,
            tooltipOf("emerald3"),
            textureOf("emerald3.png"),
            emeraldTierOf(3)
        ),
        BeaconUpgradeButtonData(
            DisableMonsterSpawningUpgrade(),
            tooltipOf("emerald4"),
            textureOf("emerald4.png"),
            emeraldTierOf(4)
        )
    )

    private val DIAMOND = listOf(
        BeaconUpgradeButtonData(StatusEffectUpgrade(StatusEffects.STRENGTH, 0) andThen StatusEffectUpgrade(StatusEffects.REGENERATION, 0),
            tooltipOf("diamond1"),
            textureOf("diamond1.png"),
            diamondTierOf(1)
        ),
        BeaconUpgradeButtonData(
            BeaconUpgrade.EMPTY,
            tooltipOf("diamond2"),
            textureOf("diamond2.png"),
            diamondTierOf(2)
        ),
        BeaconUpgradeButtonData(
            BeaconUpgrade.EMPTY,
            tooltipOf("diamond3"),
            textureOf("diamond3.png"),
            diamondTierOf(3)
        ),
        BeaconUpgradeButtonData(
            BeaconUpgrade.EMPTY,
            tooltipOf("diamond4"),
            textureOf("diamond4.png"),
            diamondTierOf(4)
        ),
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
    fun dataAt(i: Int): BeaconUpgradeButtonData? = ALL.getOrNull(i)

    /**
     * Returns the [BeaconUpgradeButtonData] of the given [upgrade].
     */
    fun dataOf(upgrade: BeaconUpgrade?) = dataAt(indexOf(upgrade))

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