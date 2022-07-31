package net.devoev.vanilla_cubed.item.behavior

import net.minecraft.item.Item
import java.util.function.Predicate

/**
 * A conditional [BehaviorModifier]. If the [predicate] is fulfilled, this behavior acts as the given [behavior].
 */
class ConditionalBehavior<in T : Item, in P, out R>(
    private val behavior: BehaviorModifier<T, P, R>,
    private val default: R,
    private val predicate: Predicate<P>) : BehaviorModifier<T, P, R> {

    override fun apply(item: T, params: P): R = if (predicate.test(params)) behavior(item, params) else default

    companion object {

        /**
         * Builds a [ConditionalBehavior] with return type [Unit] from the given [behavior].
         */
        fun <T : Item, P> build(behavior: BehaviorModifier<T, P, Unit>, predicate: Predicate<P>)
            = ConditionalBehavior(behavior, Unit, predicate)
    }
}