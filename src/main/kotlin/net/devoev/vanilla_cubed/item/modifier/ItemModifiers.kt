package net.devoev.vanilla_cubed.item.modifier

import net.minecraft.block.BlockState
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

/**
 * Collection of [item modifiers][ItemModifier].
 */
typealias ItemModifiers<T> = Collection<ItemModifier<T>>

/**
 * Calls [ItemModifier.inventoryTick] for every element in this collection.
 */
fun <T : Item> ItemModifiers<T>.inventoryTick(item: T, stack: ItemStack, world: World, entity: Entity, slot: Int, selected: Boolean) {
    forEach { it.inventoryTick(item, stack, world, entity, slot, selected) }
}

/**
 * Calls [ItemModifier.postHit] for every element in this collection.
 */
fun <T : Item> ItemModifiers<T>.postHit(item: T, stack: ItemStack, target: LivingEntity, attacker: LivingEntity) {
    forEach { it.postHit(item, stack, target, attacker) }
}

/**
 * Calls [ItemModifier.postHit] for every element in this collection.
 */
fun <T : Item> ItemModifiers<T>.postMine(item: T, stack: ItemStack, world: World, state: BlockState, pos: BlockPos, miner: LivingEntity) {
    forEach { it.postMine(item, stack, world, state, pos, miner) }
}