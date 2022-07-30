package net.devoev.vanilla_cubed.item.behavior

import net.minecraft.entity.LivingEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack

/**
 * Modifies the [Item.postHit] method.
 */
fun interface PostHitBehavior<in T : Item> : BehaviorModifier<T, PostHitBehavior.Params, Boolean> {

    companion object {
        val DEFAULT = PostHitBehavior<Item> { _,_ -> false }
    }

    data class Params(
        val stack: ItemStack?,
        val target: LivingEntity?,
        val attacker: LivingEntity?
    )
}