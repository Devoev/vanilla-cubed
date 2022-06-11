package net.devoev.vanilla_cubed.item.behavior

import net.minecraft.entity.LivingEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack

fun interface PostHitBehavior<in T : Item> : ItemBehavior<T> {

    fun postHit(item: T, stack: ItemStack?, target: LivingEntity?, attacker: LivingEntity?): Boolean
}