package net.devoev.vanilla_cubed.item.behavior

import net.minecraft.entity.Entity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.world.World

/**
 * A conditional [InventoryTickBehavior]. If the [condition] is fulfilled, this behavior acts as the given [behavior].
 */
abstract class ConditionalBehavior<in T : Item>(private val behavior: InventoryTickBehavior<T>) : InventoryTickBehavior<T> {

    abstract fun condition(item: T, stack: ItemStack?, world: World?, entity: Entity?, slot: Int, selected: Boolean): Boolean

    override fun inventoryTick(item: T, stack: ItemStack?, world: World?, entity: Entity?, slot: Int, selected: Boolean) {
        if (condition(item, stack, world, entity, slot, selected))
            behavior.inventoryTick(item, stack, world, entity, slot, selected)
    }
}