package net.devoev.vanilla_cubed.item.tool

import net.devoev.vanilla_cubed.util.isNetherite
import net.minecraft.item.ItemStack

/**
 * The key for the NBT data, that indices whether a netherite tool is demagnetized.
 */
const val NETHERITE_DEMAGNETIZED_KEY = "demagnetized"

/**
 * Returns true, if the netherite [stack] is magnetic.
 */
fun isMagnetic(stack: ItemStack): Boolean {
    if (!stack.item.isNetherite()) error("${stack.item} must be a netherite tool")
    return stack.nbt?.getBoolean(NETHERITE_DEMAGNETIZED_KEY) == false
}

/**
 * Sets the [NETHERITE_DEMAGNETIZED_KEY] to the negation of the [magnetic] value for the given [stack].
 */
fun setMagnetic(stack: ItemStack, magnetic: Boolean) {
    if (!stack.item.isNetherite()) error("${stack.item} must be a netherite tool")
    stack.nbt?.putBoolean(NETHERITE_DEMAGNETIZED_KEY, !magnetic)
}