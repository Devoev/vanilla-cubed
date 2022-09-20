package net.devoev.vanilla_cubed.item.tool

import net.minecraft.item.ToolMaterial
import net.minecraft.item.Item

/**
 * An [Item] that is made out of a [ToolMaterial].
 */
interface ToolMaterialItem {
    val material: ToolMaterial
}