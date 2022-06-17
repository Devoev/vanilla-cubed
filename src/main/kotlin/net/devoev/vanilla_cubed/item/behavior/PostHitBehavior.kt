package net.devoev.vanilla_cubed.item.behavior

import net.minecraft.entity.LivingEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack

/**
 * Modifies the [Item.postHit] method.
 */
fun interface PostHitBehavior<in T : Item> {

    fun postHit(item: T, stack: ItemStack?, target: LivingEntity?, attacker: LivingEntity?): Boolean

    companion object {
        val DEFAULT = PostHitBehavior<Item> { _,_,_,_ -> false }
    }
}