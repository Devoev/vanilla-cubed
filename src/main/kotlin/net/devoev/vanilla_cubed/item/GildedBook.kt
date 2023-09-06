package net.devoev.vanilla_cubed.item

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.BookItem

class GildedBook : BookItem(FabricItemSettings()) {

    override fun getEnchantability(): Int = 25
}