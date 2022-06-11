package net.devoev.vanilla_cubed.item.behavior

import net.minecraft.item.Item

/**
 * Data to modify the behavior of [items][Item].
 */
data class ItemBehaviors<T : Item> (
    val inventoryTickBehavior: InventoryTickBehavior<T> = InventoryTickBehavior.DEFAULT,
    val postHitBehavior: PostHitBehavior<T> = PostHitBehavior.DEFAULT
)