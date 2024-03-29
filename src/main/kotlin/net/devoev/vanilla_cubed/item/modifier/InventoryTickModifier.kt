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

    /**
     * Creates a sequence of modifiers that first runs this and then [after].
     */
    infix fun andThen(after: InventoryTickModifier<T>): InventoryTickModifier<T> {
        return InventoryTickModifier { stack, world, entity, slot, selected ->
            inventoryTick(this, stack, world, entity, slot, selected)
            after.inventoryTick(this, stack, world, entity, slot, selected)
        }
    }

    /**
     * Creates a conditional [InventoryTickModifier], that runs this function if [predicate] evaluates to true.
     */
    infix fun runIf(predicate: (item: T, stack: ItemStack, world: World, entity: Entity, slot: Int, selected: Boolean) -> Boolean): InventoryTickModifier<T> {
        return InventoryTickModifier { stack, world, entity, slot, selected ->
            if (predicate(this, stack, world, entity, slot, selected))
                inventoryTick(this, stack, world, entity, slot, selected)
        }
    }
}

/**
 * Returns an empty [InventoryTickModifier].
 */
fun <T : Item> emptyInventoryTickModifier() = InventoryTickModifier<T> { _, _, _, _, _ -> }