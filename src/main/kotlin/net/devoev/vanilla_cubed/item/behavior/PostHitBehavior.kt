package net.devoev.vanilla_cubed.item.behavior

import net.minecraft.entity.LivingEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack

fun interface PostHitBehavior<in T : Item> {

    operator fun invoke(item: T, stack: ItemStack?, target: LivingEntity?, attacker: LivingEntity?): Boolean

    companion object {
        val DEFAULT = PostHitBehavior<Item> { _,_,_,_ -> false }
    }
}