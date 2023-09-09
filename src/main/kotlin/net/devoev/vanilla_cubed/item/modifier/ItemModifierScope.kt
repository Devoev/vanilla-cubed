package net.devoev.vanilla_cubed.item.modifier

import net.minecraft.block.BlockState
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

/**
 * Scope for building an [ItemModifier].
 */
class ItemModifierScope<T : Item> {

    private var inventoryTickDelegate: InventoryTick<T> = { _, _, _, _, _ -> }
    private var postMineDelegate: PostMine<T> = { _, _, _, _, _ -> }
    private var postHitDelegate: PostHit<T> = { _, _, _ -> }

    fun inventoryTick(block: InventoryTick<T>) {
        inventoryTickDelegate = block
    }

    fun postMine(block: PostMine<T>) {
        postMineDelegate = block
    }

    fun postHit(block: PostHit<T>) {
        postHitDelegate = block
    }

    /**
     * Builds the item modifier.
     */
    internal fun buildModifier(): ItemModifier<T> {
        return object : ItemModifier<T> {
            override fun T.modifyInventoryTick(stack: ItemStack, world: World, entity: Entity, slot: Int, selected: Boolean)
                = inventoryTickDelegate(stack, world, entity, slot, selected)

            override fun T.modifyPostMine(stack: ItemStack, world: World, state: BlockState, pos: BlockPos, miner: LivingEntity)
                = postMineDelegate(stack, world, state, pos, miner)

            override fun T.modifyPostHit(stack: ItemStack, target: LivingEntity, attacker: LivingEntity)
                = postHitDelegate(stack, target, attacker)
        }
    }
}

/**
 * Signature of the [Item.inventoryTick] function.
 */
typealias InventoryTick<T> = T.(stack: ItemStack, world: World, entity: Entity, slot: Int, selected: Boolean) -> Unit

/**
 * Signature of the [Item.postMine] function.
 */
typealias PostMine<T> = T.(stack: ItemStack, world: World, state: BlockState, pos: BlockPos, miner: LivingEntity) -> Unit

/**
 * Signature of the [Item.postHit] function.
 */
typealias PostHit<T> = T.(stack: ItemStack, target: LivingEntity, attacker: LivingEntity) -> Unit

/**
 * Builds an [ItemModifier] by running the [builderAction].
 */
fun <T : Item> buildItemModifier(builderAction: ItemModifierScope<T>.() -> Unit): ItemModifier<T> {
    with(ItemModifierScope<T>()) {
        builderAction()
        return buildModifier()
    }
}

/**
 * Builds an [ItemModifier] with the provided [block] as an inventory tick function.
 */
fun <T : Item> inventoryTickModifier(block: InventoryTick<T>): ItemModifier<T>
    = buildItemModifier { inventoryTick(block) }

/**
 * Builds an [ItemModifier] with the provided [block] as a post mine function.
 */
fun <T : Item> postMineModifier(block: PostMine<T>) : ItemModifier<T>
    = buildItemModifier { postMine(block) }

/**
 * Builds an [ItemModifier] with the provided [block] as a post hit function.
 */
fun <T : Item> postHitModifier(block: PostHit<T>) : ItemModifier<T>
    = buildItemModifier { postHit(block) }