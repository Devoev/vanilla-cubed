package net.devoev.vanilla_cubed.item

import net.devoev.vanilla_cubed.mixin.ItemStackMixin
import net.minecraft.item.ArmorItem
import net.minecraft.item.FireworkRocketItem.FIREWORKS_KEY
import net.minecraft.item.FireworkRocketItem.FLIGHT_KEY
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.item.ToolMaterials
import net.minecraft.nbt.NbtList
import net.minecraft.screen.ScreenTexts
import net.minecraft.text.Text
import net.minecraft.util.math.BlockPos

/**
 * The key for the NBT data, that indices whether an [Items.ENCHANTED_BOOK] is gilded.
 */
private const val ENCHANTED_BOOK_GILDED_KEY = "gilded"

/**
 * Whether this [Items.ENCHANTED_BOOK] item is gilded. Value stored in the [ENCHANTED_BOOK_GILDED_KEY] nbt tag.
 */
var ItemStack.gilded: Boolean

    get() = isOf(Items.ENCHANTED_BOOK) && nbt?.getBoolean(ENCHANTED_BOOK_GILDED_KEY) == true

    set(value) {
        if (!isOf(Items.ENCHANTED_BOOK)) error("$item must be an ${Items.ENCHANTED_BOOK}")
        orCreateNbt.putBoolean(ENCHANTED_BOOK_GILDED_KEY, value)
    }

/**
 * The key for the NBT data, that indices whether a netherite tool is demagnetized.
 */
private const val NETHERITE_DEMAGNETIZED_KEY = "demagnetized"

/**
 * Whether this netherite item is magnetic. Value stored in the [NETHERITE_DEMAGNETIZED_KEY] nbt tag.
 */
var ItemStack.magnetic: Boolean

    get() {
        if (!item.isNetherite() && item !is ArmorItem) error("$item must be of material ${ToolMaterials.NETHERITE}")
        return nbt?.getBoolean(NETHERITE_DEMAGNETIZED_KEY) == false
    }

    set(value) {
        if (!item.isNetherite() && item !is ArmorItem) error("$item must be of material ${ToolMaterials.NETHERITE}")
        nbt?.putBoolean(NETHERITE_DEMAGNETIZED_KEY, !value)
    }

/**
 * Whether this [ModItems.AMETHYST_COMPASS] is charged. A compass is charged, if it has at least 2 damage points left.
 */
val ItemStack.charged: Boolean get() {
    if (!isOf(ModItems.AMETHYST_COMPASS)) error("$item must be an ${ModItems.AMETHYST_COMPASS}")
    return maxDamage - damage > 1
}

/**
 * The key for the NBT data, that stores the coordinates of the target position.
 */
private const val AMETHYST_COMPASS_TARGET_POS_KEY = "target_pos"

/**
 * The targeted position of this [ModItems.AMETHYST_COMPASS].
 */
var ItemStack.targetPos: BlockPos?

    get() {
        if (!isOf(ModItems.AMETHYST_COMPASS)) error("$item must be an ${ModItems.AMETHYST_COMPASS}")
        val list = nbt?.getIntArray(AMETHYST_COMPASS_TARGET_POS_KEY) ?: return null
        if (list.size != 3) return null
        return BlockPos(list[0],list[1],list[2])
    }

    set(value) {
        if (!isOf(ModItems.AMETHYST_COMPASS)) error("$item must be an ${ModItems.AMETHYST_COMPASS}")
        nbt?.putIntArray(AMETHYST_COMPASS_TARGET_POS_KEY, if (value != null) listOf(value.x, value.y, value.z) else null)
    }

/**
 * The key for the NBT data, that indicates how many ticks have passed since the powder has been used.
 */
private const val ENDERITE_POWDER_TICK_KEY = "enderite_powder_ticks"

/**
 * The remaining ticks until the teleportation can be performed.
 * Value stored in the [ENDERITE_POWDER_TICK_KEY] nbt tag.
 */
var ItemStack.teleportTicks: Int

    get() {
        if (!isOf(ModItems.ENDERITE_POWDER)) error("$item must be an ${ModItems.ENDERITE_POWDER}")
        return nbt?.getInt(ENDERITE_POWDER_TICK_KEY) ?: 0
    }

    set(value) {
        if (!isOf(ModItems.ENDERITE_POWDER)) error("$item must be an ${ModItems.ENDERITE_POWDER}")
        orCreateNbt.putInt(ENDERITE_POWDER_TICK_KEY, value)
    }

/**
 * The key for the NBT data to indicate, whether the stack is a block drop, who's block got mined with enderite tools.
 */
private const val MINED_BY_ENDERITE_KEY = "mined_by_enderite"

/**
 * Whether this stack is a block drop, that got mined by enderite tools.
 */
var ItemStack.minedByEnderite: Boolean
    get() = nbt?.getBoolean(MINED_BY_ENDERITE_KEY) ?: false
    set(value) {
        orCreateNbt.putBoolean(MINED_BY_ENDERITE_KEY, value)
        if (!value) nbt?.remove(MINED_BY_ENDERITE_KEY)
    }

/**
 * The key for the NBT data that stores the infusion level of infused firework rockets.
 */
private const val INFUSION_LVL_KEY = "infusion_lvl"

/**
 * The infusion level.
 */
var ItemStack.infusionLvl: Int
    get() {
        if (!isOf(ModItems.INFUSED_FIREWORK_ROCKET)) error("$item must be a ${ModItems.INFUSED_FIREWORK_ROCKET}")
        return if (orCreateNbt.contains(INFUSION_LVL_KEY)) nbt!!.getInt(INFUSION_LVL_KEY) else 3
    }
    set(value) {
        if (!isOf(ModItems.INFUSED_FIREWORK_ROCKET)) error("$item must be a ${ModItems.INFUSED_FIREWORK_ROCKET}")
        orCreateNbt.putInt(INFUSION_LVL_KEY, value)
    }

/**
 * The flight level of a [Items.FIREWORK_ROCKET].
 */
var ItemStack.fireworksFlight: Byte
    get() {
        return getSubNbt(FIREWORKS_KEY)?.getByte(FLIGHT_KEY)
            ?: error("$item must be a ${ModItems.INFUSED_FIREWORK_ROCKET} or ${Items.FIREWORK_ROCKET}")
    }
    set(value) {
        if (!isOf(ModItems.INFUSED_FIREWORK_ROCKET) && !isOf(Items.FIREWORK_ROCKET))
            error("$item must be a ${ModItems.INFUSED_FIREWORK_ROCKET} or ${Items.FIREWORK_ROCKET}")
        getOrCreateSubNbt(FIREWORKS_KEY).putByte(FLIGHT_KEY, value)
    }

/**
 * Appends a [ScreenTexts.EMPTY] tooltip at the top of the enchantments tooltip in [ItemStack.appendEnchantments].
 * @see ItemStackMixin.appendEnchantmentsTooltipSeparator
 */
fun appendEnchantmentsTooltipSeparator(tooltip: MutableList<Text>, enchantments: NbtList) {
    if (enchantments.isNotEmpty() && tooltip.size > 1) {
        tooltip += ScreenTexts.EMPTY
    }
}