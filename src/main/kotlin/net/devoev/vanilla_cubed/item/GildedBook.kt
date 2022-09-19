package net.devoev.vanilla_cubed.item

import net.minecraft.item.BookItem

class GildedBook : BookItem(ModItemGroup.VANILLA_CUBED.toSettings()) {

    override fun getEnchantability(): Int = 25
}