package net.devoev.vanilla_cubed.item

import net.devoev.vanilla_cubed.VanillaCubed
import net.minecraft.item.Items
import net.minecraft.item.SmithingTemplateItem
import net.minecraft.text.Text
import net.minecraft.util.Formatting
import net.minecraft.util.Identifier
import net.minecraft.util.Util


private val EMPTY_ARMOR_SLOT_HELMET_TEXTURE = Identifier("item/empty_armor_slot_helmet")
private val EMPTY_ARMOR_SLOT_CHESTPLATE_TEXTURE = Identifier("item/empty_armor_slot_chestplate")
private val EMPTY_ARMOR_SLOT_LEGGINGS_TEXTURE = Identifier("item/empty_armor_slot_leggings")
private val EMPTY_ARMOR_SLOT_BOOTS_TEXTURE = Identifier("item/empty_armor_slot_boots")
private val EMPTY_SLOT_HOE_TEXTURE = Identifier("item/empty_slot_hoe")
private val EMPTY_SLOT_AXE_TEXTURE = Identifier("item/empty_slot_axe")
private val EMPTY_SLOT_SWORD_TEXTURE = Identifier("item/empty_slot_sword")
private val EMPTY_SLOT_SHOVEL_TEXTURE = Identifier("item/empty_slot_shovel")
private val EMPTY_SLOT_PICKAXE_TEXTURE = Identifier("item/empty_slot_pickaxe")
private val EMPTY_SLOT_INGOT_TEXTURE = Identifier("item/empty_slot_ingot")
private val EMPTY_SLOT_AMETHYST_CRYSTAL_TEXTURE = VanillaCubed.id("item/empty_slot_amethyst_crystal")
private val EMPTY_SLOT_DRAGON_SCALE_TEXTURE = VanillaCubed.id("item/empty_slot_dragon_scale")
private val EMPTY_SLOT_ELYTRA_TEXTURE = VanillaCubed.id("item/empty_slot_elytra")

/**
 * List of all empty textures for the base slot.
 */
private val emptyBaseSlotTextures = listOf(
    EMPTY_ARMOR_SLOT_HELMET_TEXTURE,
    EMPTY_ARMOR_SLOT_CHESTPLATE_TEXTURE,
    EMPTY_ARMOR_SLOT_LEGGINGS_TEXTURE,
    EMPTY_ARMOR_SLOT_BOOTS_TEXTURE,
    EMPTY_SLOT_HOE_TEXTURE,
    EMPTY_SLOT_AXE_TEXTURE,
    EMPTY_SLOT_SWORD_TEXTURE,
    EMPTY_SLOT_SHOVEL_TEXTURE,
    EMPTY_SLOT_PICKAXE_TEXTURE,
)

/**
 * Creates a translatable text of the given [type] and [name] under the [VanillaCubed.id] namespace.
 */
private fun translatableTextOf(type: String, name: String)
    = Text.translatable(Util.createTranslationKey(type, VanillaCubed.id(name)))

/**
 * Creates a [SmithingTemplateItem] for the upgrade of the given [name].
 */
private fun upgradeOf(name: String, emptySlotTexture: Identifier) = SmithingTemplateItem(
    translatableTextOf("item", "smithing_template.${name}_upgrade.applies_to").formatted(Formatting.BLUE),
    translatableTextOf("item", "smithing_template.${name}_upgrade.ingredients").formatted(Formatting.BLUE),
    translatableTextOf("upgrade", "${name}_upgrade").formatted(Formatting.GRAY),
    translatableTextOf("item", "smithing_template.${name}_upgrade.base_slot_description"),
    translatableTextOf("item", "smithing_template.${name}_upgrade.additions_slot_description"),
    emptyBaseSlotTextures,
    listOf(emptySlotTexture),
)

/**
 * Smithing template upgrade for [amethyst crystals][ModItems.CHARGED_AMETHYST_CRYSTAL].
 */
val amethystUpgrade: SmithingTemplateItem = upgradeOf("amethyst", EMPTY_SLOT_AMETHYST_CRYSTAL_TEXTURE)

/**
 * Smithing template upgrade for [ancient gold ingots][ModItems.ANCIENT_GOLD_INGOT].
 */
val ancientGoldUpgrade: SmithingTemplateItem = upgradeOf("ancient_gold", EMPTY_SLOT_INGOT_TEXTURE)

/**
 * Smithing template upgrade for [enderite ingots][ModItems.ENDERITE_INGOT].
 */
val enderiteUpgrade: SmithingTemplateItem = upgradeOf("enderite", EMPTY_SLOT_INGOT_TEXTURE)

/**
 * Smithing template upgrade for [dragon scales][ModItems.INFUSED_DRAGON_SCALE].
 */
val dragonScaleUpgrade: SmithingTemplateItem = upgradeOf("dragon_scale", EMPTY_SLOT_DRAGON_SCALE_TEXTURE)

/**
 * Smithing template upgrade for combining the [ModItems.WINGED_DRAGON_SCALE_CHESTPLATE] with an [Items.ELYTRA].
 */
val elytraUpgrade: SmithingTemplateItem = upgradeOf("elytra", EMPTY_SLOT_ELYTRA_TEXTURE)