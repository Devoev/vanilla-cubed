package net.devoev.vanilla_cubed.item.behavior

import net.minecraft.item.Item

/**
 * Delegates the behavior to [delegate].
 */
open class DelegateBehavior<in T : Item, in P, out R>(private val delegate: BehaviorModifier<T, P, R>) : BehaviorModifier<T, P, R> {

    override fun apply(item: T, params: P): R = delegate(item, params)
}