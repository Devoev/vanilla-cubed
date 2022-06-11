package net.devoev.vanilla_cubed.item.behavior

import net.minecraft.entity.Entity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.world.World

fun interface InventoryTickBehavior<in T : Item> : ItemBehavior<T> {

    fun inventoryTick(item: T, stack: ItemStack?, world: World?, entity: Entity?, slot: Int, selected: Boolean)
}