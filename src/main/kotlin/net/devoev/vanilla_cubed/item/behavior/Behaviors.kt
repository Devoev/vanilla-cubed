package net.devoev.vanilla_cubed.item.behavior

import net.minecraft.item.Item

/**
 * Modifies [Item] trough composition.
 */
interface Behaviors<in T : Item> : InventoryTickBehavior<T>, PostHitBehavior<T> {

    //TODO: Remove properties
    val inventoryTickBehavior: InventoryTickBehavior<T>
    val postHitBehavior: PostHitBehavior<T>
}