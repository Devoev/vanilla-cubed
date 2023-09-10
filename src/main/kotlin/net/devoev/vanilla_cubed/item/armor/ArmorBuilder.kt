package net.devoev.vanilla_cubed.item.armor

import net.devoev.vanilla_cubed.item.modifier.ItemModifier
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.ArmorItem
import net.minecraft.item.ArmorMaterial

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
        = ModArmorItem(if (type == ArmorItem.Type.HELMET) data else data.withoutModifiers() , type)

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
fun buildArmor(material: ArmorMaterial, vararg modifiers: ItemModifier<ArmorItem>)
    = buildArmor(ArmorData(material, FabricItemSettings(), modifiers.toSet()))