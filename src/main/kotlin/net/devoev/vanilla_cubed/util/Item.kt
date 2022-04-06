package net.devoev.vanilla_cubed.util

import net.devoev.vanilla_cubed.materials.ModArmorMaterials
import net.devoev.vanilla_cubed.materials.ModToolMaterials
import net.minecraft.item.*

/**
 * Returns true, if this [Item] is made of the [armorMaterial] or [toolMaterial].
 */
fun Item.isMadeOf(armorMaterial: ArmorMaterial, toolMaterial: ToolMaterial): Boolean {
    return when (this) {
        is ToolItem -> material == toolMaterial
        is ArmorItem -> material == armorMaterial
        else -> false
    }
}

/**
 * Returns true, if this [Item] is made of enderite.
 */
fun Item.isEnderite(): Boolean = isMadeOf(ModArmorMaterials.ENDERITE, ModToolMaterials.ENDERITE)

/**
 * Returns true, if this [Item] is made of enderite.
 */
fun Item.isAncientGold(): Boolean = isMadeOf(ModArmorMaterials.ANCIENT_GOLD, ModToolMaterials.ANCIENT_GOLD)

/**
 * Returns true, if this [Item] is made of enderite.
 */
fun Item.isDragonScale(): Boolean = isMadeOf(ModArmorMaterials.DRAGON_SCALE, ModToolMaterials.DRAGON_SCALE)

/**
 * Returns true, if this [Item] is made of enderite.
 */
fun Item.isAmethyst(): Boolean = isMadeOf(ModArmorMaterials.AMETHYST, ModToolMaterials.AMETHYST)