package net.devoev.vanilla_cubed.item.modifier

import net.minecraft.block.BlockState
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

/**
 * An [ItemModifier] that only modifies [Item.inventoryTick].
 */
fun interface InventoryTickModifier<T : Item> : ItemModifier<T> {

    operator fun invoke(item: T, stack: ItemStack, world: World, entity: Entity, slot: Int, selected: Boolean)
        = inventoryTick(item, stack, world, entity, slot, selected)

    override fun T.modifyPostHit(stack: ItemStack, target: LivingEntity, attacker: LivingEntity) = Unit

    override fun T.modifyPostMine(stack: ItemStack, world: World, state: BlockState, pos: BlockPos, miner: LivingEntity) = Unit
}

/**
 * Returns an empty [InventoryTickModifier].
 */
fun <T : Item> emptyInventoryTickModifier() = InventoryTickModifier<T> { _, _, _, _, _ -> }