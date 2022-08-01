package net.devoev.vanilla_cubed.item.behavior

import net.devoev.vanilla_cubed.item.ModItems
import net.minecraft.item.ArmorItem
import net.minecraft.item.Item
import net.minecraft.item.Items
import net.minecraft.item.ToolItem
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
        = BehaviorModifier { t, p -> if (predicate.test(p)) accept(t,p) }

    override fun andThen(after: BiConsumer<in @UnsafeVariance T, in P>): BehaviorModifier<T, P>
            = BehaviorModifier { t, p -> super.andThen(after).accept(t,p) }
}