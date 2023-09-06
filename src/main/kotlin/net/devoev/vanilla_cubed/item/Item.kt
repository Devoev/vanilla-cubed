package net.devoev.vanilla_cubed.item

import net.devoev.vanilla_cubed.item.armor.ModArmorMaterials
import net.devoev.vanilla_cubed.item.tool.ModToolMaterials
import net.devoev.vanilla_cubed.item.tool.ToolMaterialItem
import net.minecraft.client.util.ModelIdentifier
import net.minecraft.item.*
import net.minecraft.registry.Registries
import net.minecraft.util.Identifier

/**
 * The [id][Identifier] of the item.
 */
val Item.id: Identifier get() = Registries.ITEM.getId(this)

/**
 * Creates a [ModelIdentifier] from [id]. Appends the [append] string to [id.path][Identifier.path].
 * The string [variant] is the variant model.
 */
fun Item.model(append: String, variant: String) = ModelIdentifier(id.namespace, id.path + append, variant)

/**
 * Creates a [ModelIdentifier] from [id]. The string [variant] is the variant model.
 */
fun Item.model(variant: String) = model("", variant)

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
fun Item.isMadeOf(toolMaterial: ToolMaterial): Boolean
    = ((this is ToolItem) && (material == toolMaterial)) || ((this is ToolMaterialItem) && (material == toolMaterial))

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