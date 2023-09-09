package net.devoev.vanilla_cubed.item.armor

import net.devoev.vanilla_cubed.item.modifier.ItemModifiers
import net.minecraft.item.ArmorItem
import net.minecraft.item.ArmorMaterial
import net.minecraft.item.Item
import net.minecraft.item.Item.Settings

/**
 * Data needed for the creation of a set of [armor][ArmorItem].
 */
data class ArmorData<T : Item> (
    val material: ArmorMaterial,
    val settings: Settings,
    val modifiers: ItemModifiers<T>
) {
    /**
     * Returns this armor data with an empty [modifiers] list.
     */
    fun withoutModifiers() = ArmorData<T>(material, settings, listOf())
}
