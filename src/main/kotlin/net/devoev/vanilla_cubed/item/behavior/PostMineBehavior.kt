package net.devoev.vanilla_cubed.item.behavior

import net.minecraft.block.BlockState
import net.minecraft.entity.LivingEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

/**
 * Modifies the [Item.postHit] method.
 */
fun interface PostMineBehavior<in T : Item> : BehaviorModifier<T, PostMineBehavior.Params, Boolean> {

    companion object {
        val DEFAULT = PostMineBehavior<Item> { _, _ -> false }
    }

    data class Params(
        val stack: ItemStack?,
        val world: World?,
        val state: BlockState?,
        val pos: BlockPos?,
        val miner: LivingEntity?
    )
}