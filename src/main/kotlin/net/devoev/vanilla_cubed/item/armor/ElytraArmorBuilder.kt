package net.devoev.vanilla_cubed.item.armor

import net.devoev.vanilla_cubed.item.modifier.ItemModifiers
import net.fabricmc.fabric.api.entity.event.v1.FabricElytraItem
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.ArmorItem
import net.minecraft.item.ArmorMaterial
import net.minecraft.text.Text

/**
 * Collection of all 4 armor items and an elytra.
 */
data class ElytraArmorItems(
    val helmet: ArmorItem,
    val chestplate: ArmorItem,
    val leggings: ArmorItem,
    val boots: ArmorItem,
    val elytra: ArmorItem
)

/**
 * Builds all 4 armor items and an elytra for the given [data].
 */
fun buildElytraArmor(data: ArmorData): ElytraArmorItems {
    val armor = buildArmor(data)
    val elytra = object : ModArmorItem(data, Type.CHESTPLATE), FabricElytraItem {}
    return ElytraArmorItems(armor.helmet, armor.chestplate, armor.leggings, armor.boots, elytra)
}

/**
 * Builds all 4 armor items and an elytra for the given [material] and [modifiers].
 */
fun buildElytraArmor(material: ArmorMaterial, modifiers: ItemModifiers<ArmorItem> = emptyList(), tooltips: List<Text> = emptyList())
    = buildElytraArmor(ArmorData(material, FabricItemSettings(), modifiers, tooltips))