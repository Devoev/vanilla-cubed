package net.devoev.vanilla_cubed.item.behavior

import net.minecraft.entity.Entity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.world.World

/**
 * Modifies the [Item.inventoryTick] method.
 */
fun interface InventoryTickBehavior<in T : Item> : BehaviorModifier<T, InventoryTickBehavior.Params> {

    companion object {
        val DEFAULT = InventoryTickBehavior<Item> { _,_ ->  }

        /**
         * Creates an [InventoryTickBehavior] from the given [behavior].
         */
        fun <T : Item> from(behavior: BehaviorModifier<T, Params>)
            = InventoryTickBehavior<T> { t, u -> behavior(t,u) }
    }

    data class Params(
        val stack: ItemStack?,
        val world: World?,
        val entity: Entity?,
        val slot: Int,
        val selected: Boolean
    )
}