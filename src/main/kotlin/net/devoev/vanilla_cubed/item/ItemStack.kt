package net.devoev.vanilla_cubed.item

import net.devoev.vanilla_cubed.util.isNetherite
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.GlobalPos

/**
 * The key for the NBT data, that indices whether an [Items.ENCHANTED_BOOK] is gilded.
 */
const val ENCHANTED_BOOK_GILDED_KEY = "gilded"

/**
 * Whether this [Items.ENCHANTED_BOOK] item is gilded. Value stored in the [ENCHANTED_BOOK_GILDED_KEY] nbt tag.
 */
var ItemStack.gilded: Boolean

    get() = isOf(Items.ENCHANTED_BOOK) && nbt?.getBoolean(ENCHANTED_BOOK_GILDED_KEY) == true

    set(value) {
        if (!isOf(Items.ENCHANTED_BOOK)) error("$item must be of type ${Items.ENCHANTED_BOOK}")
        orCreateNbt.putBoolean(ENCHANTED_BOOK_GILDED_KEY, value)
    }

/**
 * The key for the NBT data, that indices whether a netherite tool is demagnetized.
 */
const val NETHERITE_DEMAGNETIZED_KEY = "demagnetized"

/**
 * Whether this netherite item is magnetic. Value stored in the [NETHERITE_DEMAGNETIZED_KEY] nbt tag.
 */
var ItemStack.magnetic: Boolean

    get() {
        if (!item.isNetherite()) error("$item must be a netherite tool")
        return nbt?.getBoolean(NETHERITE_DEMAGNETIZED_KEY) == false
    }

    set(value) {
        if (!item.isNetherite()) error("$item must be a netherite tool")
        nbt?.putBoolean(NETHERITE_DEMAGNETIZED_KEY, !value)
    }

/**
 * Whether this [ModItems.AMETHYST_COMPASS] is charged. A compass is charged, if it has at least 2 damage points left.
 */
val ItemStack.charged: Boolean get() {
    if (item !is AmethystCompass) error("$item must be of type $AmethystCompass")
    return maxDamage - damage > 1
}

/**
 * The key for the NBT data, that stores the coordinates of the target position.
 */
const val TARGET_POS_KEY = "target_pos"

/**
 * The targeted position of this [ModItems.AMETHYST_COMPASS].
 */
var ItemStack.targetPos: BlockPos?

    get() {
        if (item !is AmethystCompass) error("$item must be of type $AmethystCompass")
        val list = nbt?.getIntArray(TARGET_POS_KEY) ?: return null
        if (list.size != 3) return null
        return BlockPos(list[0],list[1],list[2])
    }

    set(value) {
        if (item !is AmethystCompass) error("$item must be of type $AmethystCompass")
        nbt?.putIntArray(TARGET_POS_KEY, if (value != null) listOf(value.x, value.y, value.z) else null)
    }