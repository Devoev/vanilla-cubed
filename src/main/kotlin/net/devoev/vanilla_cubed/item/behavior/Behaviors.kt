package net.devoev.vanilla_cubed.item.behavior

import net.minecraft.item.Item

/**
 * A [Set] of [behavior modifiers][BehaviorModifier].
 */
interface Behaviors<in T : Item> {

    val inventoryTickBehavior: InventoryTickBehavior<T>
    val postHitBehavior: PostHitBehavior<T>
    val postMineBehavior: PostMineBehavior<T>
}