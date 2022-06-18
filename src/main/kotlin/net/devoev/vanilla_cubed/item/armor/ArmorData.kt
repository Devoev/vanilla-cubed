package net.devoev.vanilla_cubed.item.armor

import net.minecraft.item.ArmorMaterial
import net.minecraft.item.Item.Settings
import net.minecraft.item.ArmorItem

/**
 * Data needed for the creation of a set of [armor][ArmorItem].
 */
data class ArmorData(
    val material: ArmorMaterial,
    val settings: Settings
)
