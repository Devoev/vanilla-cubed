package net.devoev.vanilla_cubed.item.modifier

import net.minecraft.item.Item

/**
 * A [Set] of [behavior modifiers][ItemModifier].
 */
interface Behaviors<in T : Item> {

    val inventoryTickModifier: InventoryTickModifier<T>
    val postHitModifier: PostHitModifier<T>
    val postMineModifier: PostMineModifier<T>
}