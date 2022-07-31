package net.devoev.vanilla_cubed.item.behavior

import net.minecraft.item.Item

/**
 * Data class implementation of [Behaviors].
 */
data class DataBehaviors<in T : Item> (
    override val inventoryTickBehavior: InventoryTickBehavior<T> = InventoryTickBehavior.DEFAULT,
    override val postHitBehavior: PostHitBehavior<T> = PostHitBehavior.DEFAULT,
    override val postMineBehavior: PostMineBehavior<T> = PostMineBehavior.DEFAULT
) : Behaviors<T>