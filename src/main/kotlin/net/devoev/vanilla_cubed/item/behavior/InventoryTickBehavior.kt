package net.devoev.vanilla_cubed.item.behavior

import net.minecraft.entity.Entity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.world.World

/**
 * Modifies the [Item.inventoryTick] method.
 */
typealias InventoryTickBehavior<T> = BehaviorModifier<T, InventoryTickParams>

val INVENTORY_TICK_DEFAULT = InventoryTickBehavior<Item> { _, _ ->  }

/**
 * Parameters for [Item.inventoryTick].
 */
data class InventoryTickParams(
    val stack: ItemStack?,
    val world: World?,
    val entity: Entity?,
    val slot: Int,
    val selected: Boolean
)