package net.devoev.vanilla_cubed.item.armor

import net.devoev.vanilla_cubed.item.modifier.ItemModifiers
import net.minecraft.item.ArmorItem
import net.minecraft.item.ArmorMaterial
import net.minecraft.item.Item.Settings
import net.minecraft.text.Text

/**
 * Data needed for the creation of a set of [armor][ArmorItem].
 */
data class ArmorData (
    val material: ArmorMaterial,
    val settings: Settings,
    val modifiers: ItemModifiers<ArmorItem>,
    val tooltips: List<Text>
) {
    /**
     * Returns this armor data with an empty [modifiers] list.
     */
    fun withoutModifiers() = ArmorData(material, settings, listOf(), tooltips)

    /**
     * Appends the [tooltips] to the given [tooltip].
     */
    fun appendTooltips(tooltip: MutableList<Text>) {
        tooltip.addAll(tooltips)
    }
}
