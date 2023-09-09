package net.devoev.vanilla_cubed.item.modifier

import net.minecraft.block.BlockState
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

/**
 * An [ItemModifier] that only modifies [Item.postMine].
 */
fun interface PostMineModifier<T : Item> : ItemModifier<T> {

    operator fun invoke(item: T, stack: ItemStack, world: World, state: BlockState, pos: BlockPos, miner: LivingEntity)
        = postMine(item, stack, world, state, pos, miner)

    override fun T.modifyInventoryTick(stack: ItemStack, world: World, entity: Entity, slot: Int, selected: Boolean) = Unit

    override fun T.modifyPostHit(stack: ItemStack, target: LivingEntity, attacker: LivingEntity) = Unit
}

/**
 * Returns an empty [PostHitModifier].
 */
fun <T : Item> emptyPostMineModifier() = PostMineModifier<T> { _, _, _, _, _ -> }