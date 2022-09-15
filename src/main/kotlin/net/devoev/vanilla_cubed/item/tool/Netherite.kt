package net.devoev.vanilla_cubed.item.tool

import net.devoev.vanilla_cubed.util.isNetherite
import net.minecraft.item.ItemStack

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