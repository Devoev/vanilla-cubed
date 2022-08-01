package net.devoev.vanilla_cubed.item.behavior

import net.minecraft.item.Item

/**
 * Data class implementation of [Behaviors].
 */
data class DataBehaviors<in T : Item> (
    override val inventoryTickBehavior: InventoryTickBehavior<T> = INVENTORY_TICK_DEFAULT,
    override val postHitBehavior: PostHitBehavior<T> = POST_HIT_DEFAULT,
    override val postMineBehavior: PostMineBehavior<T> = POST_MINE_DEFAULT
) : Behaviors<T>