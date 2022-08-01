package net.devoev.vanilla_cubed.item.behavior

import net.minecraft.entity.LivingEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack

/**
 * Modifies the [Item.postHit] method.
 */
typealias PostHitBehavior<T> = BehaviorModifier<T, PostHitParams>

val POST_HIT_DEFAULT = PostHitBehavior<Item> { _,_ ->  }

/**
 * Parameters for [Item.postHit].
 */
data class PostHitParams(
    val stack: ItemStack?,
    val target: LivingEntity?,
    val attacker: LivingEntity?
)