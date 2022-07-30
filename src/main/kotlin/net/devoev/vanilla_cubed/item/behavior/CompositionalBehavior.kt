package net.devoev.vanilla_cubed.item.behavior

import net.minecraft.item.Item

/**
 * A composition of multiple [behaviors][BehaviorModifier].
 */
abstract class CompositionalBehavior<in T : Item, in P, R>(private val behaviors: Collection<BehaviorModifier<T, P, R>>) : BehaviorModifier<T, P, R> {

    /**
     * Processes the return values of all behaviors to produce a single return value.
     */
    abstract fun process(result: List<R>): R

    override fun apply(item: T, params: P): R = process(behaviors.map { it(item, params) })

    companion object {

        /**
         * Builds a [CompositionalBehavior] with return type [Unit] from the given [behaviors].
         */
        fun <T : Item, P> build(vararg behaviors: BehaviorModifier<T, P, Unit>) = object : CompositionalBehavior<T, P, Unit>(behaviors.toList()) {
            override fun process(result: List<Unit>) = Unit
        }
    }
}