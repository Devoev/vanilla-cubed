package net.devoev.vanilla_cubed.item.armor

import net.devoev.vanilla_cubed.item.modifier.ItemModifiers
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.ArmorItem
import net.minecraft.item.ArmorMaterial
import net.minecraft.text.Text

/**
 * Collection of all 4 armor items.
 */
data class ArmorItems(
    val helmet: ArmorItem,
    val chestplate: ArmorItem,
    val leggings: ArmorItem,
    val boots: ArmorItem
)

/**
 * Builds all 4 armor items for the given [data].
 */
fun buildArmor(data: ArmorData): ArmorItems {

    fun buildArmorItem(type: ArmorItem.Type)
        = ModArmorItem(if (type == ArmorItem.Type.HELMET) data.copyWithSettings() else data.copyWithoutModifiers(), type)

    return ArmorItems(
        buildArmorItem(ArmorItem.Type.HELMET),
        buildArmorItem(ArmorItem.Type.CHESTPLATE),
        buildArmorItem(ArmorItem.Type.LEGGINGS),
        buildArmorItem(ArmorItem.Type.BOOTS)
    )
}

/**
 * Builds all 4 armor items for the given [material] and [modifiers].
 */
fun buildArmor(material: ArmorMaterial, modifiers: ItemModifiers<ArmorItem> = emptyList(), vararg tooltips: List<Text>)
    = buildArmor(ArmorData(material, FabricItemSettings(), modifiers, tooltips.toList().flatten()))