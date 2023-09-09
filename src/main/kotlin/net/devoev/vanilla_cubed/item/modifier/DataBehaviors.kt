package net.devoev.vanilla_cubed.item.modifier

import net.minecraft.item.Item

/**
 * Data class implementation of [Behaviors].
 */
data class DataBehaviors<in T : Item> (
    override val inventoryTickModifier: InventoryTickModifier<T> = INVENTORY_TICK_DEFAULT,
    override val postHitModifier: PostHitModifier<T> = POST_HIT_DEFAULT,
    override val postMineModifier: PostMineModifier<T> = POST_MINE_DEFAULT
) : Behaviors<T>