package net.devoev.vanilla_cubed.item.tool.data

import net.minecraft.item.Item
import net.minecraft.item.ToolItem
import net.minecraft.item.ToolMaterial

/**
 * Data needed for the creation of a [ToolItem].
 */
data class ToolData<out T1 : Number?, out T2 : Number?>(
    val material: ToolMaterial,
    val attackDamage: T1,
    val attackSpeed: T2,
    val settings: Item.Settings
)