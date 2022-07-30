package net.devoev.vanilla_cubed.item.behavior

import net.minecraft.item.Item

/**
 * Modifies the behavior of an item by injecting the [apply] method.
 * @param T The type of item this modifier can be applied to.
 * @param P The input parameter type.
 * @param R The output return type.
 */
fun interface BehaviorModifier<in T : Item, in P, out R> {

    /**
     * Applies the modification.
     */
    fun apply(item: T, params: P): R

    operator fun invoke(item: T, params: P) = apply(item, params)
}