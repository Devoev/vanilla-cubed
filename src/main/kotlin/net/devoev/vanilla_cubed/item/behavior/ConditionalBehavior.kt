package net.devoev.vanilla_cubed.item.behavior

import net.minecraft.item.Item

/**
 * A conditional [BehaviorModifier]. If the [condition] is fulfilled, this behavior acts as the given [behavior].
 */
abstract class ConditionalBehavior<in T : Item, in P, out R>(private val behavior: BehaviorModifier<T, P, R>) : BehaviorModifier<T, P, R> {

    /**
     * Returns true, if the condition is fulfilled.
     */
    abstract fun condition(item: T, params: P): Boolean

    /**
     * The default return value, if the condition evaluates to false.
     */
    abstract val default: R

    override fun apply(item: T, params: P): R = if (condition(item, params)) behavior(item, params) else default

    companion object {

        /**
         * Builds a [ConditionalBehavior] with return type [Unit] from the given [behavior].
         */
        fun <T : Item, P> build(behavior: BehaviorModifier<T, P, Unit>, condition: (T, P) -> Boolean) = object : ConditionalBehavior<T, P, Unit>(behavior) {

            override val default: Unit = Unit

            override fun condition(item: T, params: P): Boolean = condition(item, params)
        }
    }
}