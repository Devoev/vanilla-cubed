package net.devoev.vanilla_cubed.item.behavior

import net.minecraft.item.Item
import java.util.function.BiConsumer
import java.util.function.Predicate

/**
 * Modifies the behavior of an item by injecting the [accept] method.
 * @param T The type of item this modifier can be applied to.
 * @param P The input parameter type.
 */
fun interface BehaviorModifier<in T : Item, P> : BiConsumer<@UnsafeVariance T, P> {

    operator fun invoke(item: T, params: P) = accept(item, params)

    /**
     * Creates a conditional [BehaviorModifier], that runs this function if [predicate] evaluates to true.
     * @return A composed [BehaviorModifier].
     */
    fun runIf(predicate: Predicate<P>): BehaviorModifier<T, P>
        = BehaviorModifier { t, p -> if (predicate.test(p)) accept(t,p) else Unit }

    /**
     * Returns a composed [BehaviorModifier] that performs, in sequence, this operation followed by the after operation.
     * @see BiConsumer.andThen
     */
    fun andThen(after: BehaviorModifier<@UnsafeVariance T, P>): BehaviorModifier<T, P>
        = BehaviorModifier { t, p -> accept(t,p); after.accept(t,p) }
}