package net.devoev.vanilla_cubed.item.behavior

import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.world.World

/**
 * Data class implementation of [Behaviors].
 */
data class DataBehaviors<in T : Item> (
    override val inventoryTickBehavior: InventoryTickBehavior<T> = InventoryTickBehavior.DEFAULT,
    override val postHitBehavior: PostHitBehavior<T> = PostHitBehavior.DEFAULT
) : Behaviors<T> {

    override fun inventoryTick(item: T, stack: ItemStack?, world: World?, entity: Entity?, slot: Int, selected: Boolean) {
        inventoryTickBehavior.inventoryTick(item, stack, world, entity, slot, selected)
    }

    override fun postHit(item: T, stack: ItemStack?, target: LivingEntity?, attacker: LivingEntity?): Boolean {
        return postHitBehavior.postHit(item, stack, target, attacker)
    }
}