package net.devoev.vanilla_cubed.item

import net.devoev.vanilla_cubed.util.Identifiable
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.Item
import net.minecraft.util.registry.Registry

abstract class ModItem(settings: FabricItemSettings) : Item(settings), Identifiable<Item, ModItem> {

    override val type: Registry<Item> = Registry.ITEM

    override val entry: ModItem
        get() = this
}