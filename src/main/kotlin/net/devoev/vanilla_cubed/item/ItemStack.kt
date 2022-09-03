package net.devoev.vanilla_cubed.item

import net.minecraft.item.EnchantedBookItem
import net.minecraft.item.ItemStack
import net.minecraft.item.Items

/**
 * Returns true, if the item is an [Items.ENCHANTED_BOOK] and has the NBT "gilded" equal true.
 */
fun ItemStack.isGilded() = isOf(Items.ENCHANTED_BOOK) && nbt?.getBoolean("gilded") == true

/**
 * Sets the "gilded" NBT value of this stack to true.
 * @throws error If this item is not an [Items.ENCHANTED_BOOK].
 */
fun ItemStack.setGilded() {
    if (!isOf(Items.ENCHANTED_BOOK)) error("$this must be of type ${Items.ENCHANTED_BOOK}")
    orCreateNbt.putBoolean("gilded", true);
}