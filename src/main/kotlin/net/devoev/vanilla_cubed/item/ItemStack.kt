package net.devoev.vanilla_cubed.item

import net.devoev.vanilla_cubed.util.isNetherite
import net.minecraft.item.ItemStack
import net.minecraft.item.Items

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