package net.devoev.vanilla_cubed.item

import net.devoev.vanilla_cubed.mixin.EnchantmentHelperMixin
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.BookItem
import net.minecraft.item.ItemStack

class GildedBook : BookItem(FabricItemSettings()) {

    override fun getEnchantability(): Int = 25
}

/**
 * Allows gilded books to be enchanted, by setting the local [bl][isBook] variable to `true`.
 * @see EnchantmentHelperMixin.addGildedBookEntry
 */
fun addGildedBookEntry(isBook: Boolean, stack: ItemStack): Boolean {
    return stack.isOf(ModItems.GILDED_BOOK) || isBook;
}