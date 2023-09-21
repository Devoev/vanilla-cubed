package net.devoev.vanilla_cubed.item.armor

import net.devoev.vanilla_cubed.item.copy
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
     * Creates a copy of this armor data with an empty [modifiers] list.
     */
    fun copyWithoutModifiers() = copyWithSettings(tooltips = listOf())

    /**
     * Creates a [copy] of this data class.
     * The default value of [settings] is a copy of the original one.
     */
    fun copyWithSettings(
        material: ArmorMaterial = this.material,
        settings: Settings = this.settings.copy(),
        modifiers: ItemModifiers<ArmorItem> = this.modifiers,
        tooltips: List<Text> = this.tooltips
    ): ArmorData {
        return ArmorData(material, settings, modifiers, tooltips)
    }

    /**
     * Appends the [tooltips] to the given [tooltip].
     */
    fun appendTooltips(tooltip: MutableList<Text>) {
        tooltip.addAll(tooltips)
    }
}
