package net.devoev.vanilla_cubed.util

import net.devoev.vanilla_cubed.item.armor.ModArmorMaterials
import net.devoev.vanilla_cubed.item.tool.ModToolMaterials
import net.minecraft.item.*
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

/**
 * The [id][Identifier] of the item.
 */
val Item.id: Identifier get() = Registry.ITEM.getId(this)

/**
 * Returns true, if this [Item] is made of the [armorMaterial] or [toolMaterial].
 */
fun Item.isMadeOf(armorMaterial: ArmorMaterial, toolMaterial: ToolMaterial): Boolean = isMadeOf(armorMaterial) || isMadeOf(toolMaterial)

/**
 * Returns true, if this [Item] is made of the [armorMaterial].
 */
fun Item.isMadeOf(armorMaterial: ArmorMaterial): Boolean = this is ArmorItem && material == armorMaterial

/**
 * Returns true, if this [Item] is made of the [toolMaterial].
 */
fun Item.isMadeOf(toolMaterial: ToolMaterial): Boolean = this is ToolItem && material == toolMaterial

/**
 * Returns true, if this [Item] is made of enderite.
 */
fun Item.isEnderite(): Boolean = isMadeOf(ModArmorMaterials.ENDERITE, ModToolMaterials.ENDERITE)

/**
 * Returns true, if this [Item] is made of ancient gold.
 */
fun Item.isAncientGold(): Boolean = isMadeOf(ModArmorMaterials.ANCIENT_GOLD, ModToolMaterials.ANCIENT_GOLD)

/**
 * Returns true, if this [Item] is made of dragon scale.
 */
fun Item.isDragonScale(): Boolean = isMadeOf(ModArmorMaterials.DRAGON_SCALE, ModToolMaterials.DRAGON_SCALE)

/**
 * Returns true, if this [Item] is made of amethyst.
 */
fun Item.isAmethyst(): Boolean = isMadeOf(ModArmorMaterials.AMETHYST, ModToolMaterials.AMETHYST)

/**
 * Returns true, if this [Item] is made of netherite.
 */
fun Item.isNetherite(): Boolean = isMadeOf(ArmorMaterials.NETHERITE, ToolMaterials.NETHERITE)