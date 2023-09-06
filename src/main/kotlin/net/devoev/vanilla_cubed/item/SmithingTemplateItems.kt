package net.devoev.vanilla_cubed.item

import net.devoev.vanilla_cubed.VanillaCubed
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
 * Creates the `appliesTo` text for an upgrade of the given [name].
 */
private fun appliesToTextOf(name: String) = Text.translatable(
    Util.createTranslationKey(
        "item",
        VanillaCubed.id("smithing_template.${name}_upgrade.applies_to")
    )
).formatted(Formatting.BLUE)

/**
 * Creates the `ingredients` text for an upgrade of the given [name].
 */
private fun ingredientsTextOf(name: String) = Text.translatable(
    Util.createTranslationKey(
        "item",
        VanillaCubed.id("smithing_template.${name}_upgrade.ingredients")
    )
).formatted(Formatting.BLUE)

/**
 * Creates the `title` text for an upgrade of the given [name].
 */
private fun titleTextOf(name: String) = Text.translatable(
    Util.createTranslationKey(
        "upgrade",
        VanillaCubed.id("netherite_upgrade")
    )
).formatted(Formatting.GRAY)

/**
 * Creates the `baseSlotDescription` text for an upgrade of the given [name].
 */
private fun baseSlotDescriptionTextOf(name: String) = Text.translatable(
    Util.createTranslationKey(
        "item",
        VanillaCubed.id("smithing_template.${name}_upgrade.base_slot_description")
    )
)

/**
 * Creates the `additionsSlotDescription` text for an upgrade of the given [name].
 */
private fun additionsSlotDescriptionTextOf(name: String) = Text.translatable(
    Util.createTranslationKey(
        "item",
        VanillaCubed.id("smithing_template.${name}_upgrade.additions_slot_description")
    )
)

/**
 * Creates a [SmithingTemplateItem] for the upgrade of the given [name].
 */
private fun upgradeOf(name: String) = SmithingTemplateItem(
    appliesToTextOf(name),
    ingredientsTextOf(name),
    titleTextOf(name),
    baseSlotDescriptionTextOf(name),
    additionsSlotDescriptionTextOf(name),
    emptyBaseSlotTextures,
    listOf(EMPTY_SLOT_INGOT_TEXTURE), // TODO: Choose different textures
)

val amethystUpgrade: SmithingTemplateItem = upgradeOf("amethyst")
val ancientGoldUpgrade: SmithingTemplateItem = upgradeOf("ancient_gold")
val enderiteUpgrade: SmithingTemplateItem = upgradeOf("enderite")
val dragonScaleUpgrade: SmithingTemplateItem = upgradeOf("dragon_scale")