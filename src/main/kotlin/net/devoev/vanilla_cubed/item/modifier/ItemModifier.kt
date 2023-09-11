package net.devoev.vanilla_cubed.item.modifier

import net.minecraft.block.BlockState
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import java.util.function.Predicate

/**
 * Modifies the function of an item by injecting the [accept] method.
 */
interface ItemModifier<T : Item> {

    /**
     * @see Item.inventoryTick
     */
    fun T.modifyInventoryTick(stack: ItemStack, world: World, entity: Entity, slot: Int, selected: Boolean)

    /**
     * Modified inventory tick.
     */
    fun inventoryTick(item: T, stack: ItemStack, world: World, entity: Entity, slot: Int, selected: Boolean)
        = item.modifyInventoryTick(stack, world, entity, slot, selected)

    /**
     * @see Item.postMine
     */
    fun T.modifyPostMine(stack: ItemStack, world: World, state: BlockState, pos: BlockPos, miner: LivingEntity)

    /**
     * Modified post mine.
     */
    fun postMine(item: T, stack: ItemStack, world: World, state: BlockState, pos: BlockPos, miner: LivingEntity)
        = item.modifyPostMine(stack, world, state, pos, miner)

    /**
     * @see Item.postHit
     */
    fun T.modifyPostHit(stack: ItemStack, target: LivingEntity, attacker: LivingEntity)

    /**
     * Modified post hit.
     */
    fun postHit(item: T, stack: ItemStack, target: LivingEntity, attacker: LivingEntity)
        = item.modifyPostHit(stack, target, attacker)

    /**
     * Creates a conditional [ItemModifier], that runs this function if [predicate] evaluates to true.
     */
    fun runIf(predicate: Predicate<T>): ItemModifier<T> = buildItemModifier {
        inventoryTick { stack, world, entity, slot, selected ->
            if (predicate.test(this))
                inventoryTick(this, stack, world, entity, slot, selected)
        }

        postMine { stack, world, state, pos, miner ->
            if (predicate.test(this))
                postMine(this, stack, world, state, pos, miner)
        }

        postHit { stack, target, attacker ->
            if (predicate.test(this))
                postHit(this, stack, target, attacker)
        }
    }

    /**
     * Creates a sequence of modifiers that first runs this and then [after].
     */
    infix fun andThen(after: ItemModifier<T>): ItemModifier<T> = buildItemModifier {
        inventoryTick { stack, world, entity, slot, selected ->
            inventoryTick(this, stack, world, entity, slot, selected)
            after.inventoryTick(this, stack, world, entity, slot, selected)
        }

        postMine { stack, world, state, pos, miner ->
            postMine(this, stack, world, state, pos, miner)
            after.postMine(this, stack, world, state, pos, miner)
        }

        postHit { stack, target, attacker ->
            postHit(this, stack, target, attacker)
            after.postHit(this, stack, target, attacker)
        }
    }
}