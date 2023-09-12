package net.devoev.vanilla_cubed.item.tool

import net.devoev.vanilla_cubed.item.modifier.ItemModifiers
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.Item
import net.minecraft.item.ToolItem
import net.minecraft.item.ToolMaterial
import net.minecraft.text.Text

/**
 * Data needed for the creation of a [ToolItem].
 */
data class ToolData(
    val material: ToolMaterial,
    val attackDamage: Float,
    val attackSpeed: Float,
    val settings: Item.Settings,
    val modifiers: ItemModifiers<Item>,
    val tooltips: List<Text>
) {

    /**
     * Appends the [tooltips] to the given [tooltip].
     */
    fun appendTooltips(tooltip: MutableList<Text>) {
        tooltip.addAll(tooltips)
    }
}

/**
 * The base attack damage values for sword, shovel, pickaxe, axe and hoe.
 */
val BASE_ATTACK_DAMAGE: List<Float> = listOf(3F, 1.5F, 1F, 5F, -3F)

/**
 * The base attack speed values for sword, shovel, pickaxe, axe and hoe.
 */
val BASE_ATTACK_SPEED: List<Float> = listOf(-2.4F, -3F, -2.8F, -3F, 0F)

/**
 * Creates a list of [ToolData] for the given parameters.
 */
fun toolDataOf(
    material: ToolMaterial,
    attackDamageAmounts: List<Float> = BASE_ATTACK_DAMAGE,
    attackSpeedAmounts: List<Float> = BASE_ATTACK_SPEED,
    settings: Item.Settings = FabricItemSettings(),
    modifiers: ItemModifiers<Item>,
    tooltips: List<Text>
): List<ToolData> {
    return (0..4).map { ToolData(material, attackDamageAmounts[it], attackSpeedAmounts[it], settings, modifiers, tooltips) }
}