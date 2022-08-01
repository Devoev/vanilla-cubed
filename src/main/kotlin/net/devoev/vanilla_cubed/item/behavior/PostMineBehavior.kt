package net.devoev.vanilla_cubed.item.behavior

import net.minecraft.block.BlockState
import net.minecraft.entity.LivingEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

/**
 * Modifies the [Item.postMine] method.
 */
typealias PostMineBehavior<T> = BehaviorModifier<T, PostMineParams>

val POST_MINE_DEFAULT = PostMineBehavior<Item> { _,_ ->  }

/**
 * Parameters for [Item.postMine].
 */
data class PostMineParams(
    val stack: ItemStack?,
    val world: World?,
    val state: BlockState?,
    val pos: BlockPos?,
    val miner: LivingEntity?
)