package net.devoev.vanilla_cubed.item.modifier

import net.devoev.vanilla_cubed.item.tool.ModTools
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.stat.Stats
import net.minecraft.util.math.BlockPos

/**
 * Mines the block above the mined block as well.
 * @see ModTools.AMETHYST
 */
val VeinMiningModifier = PostMineModifier<Item> { stack, world, state, pos, miner ->
    val blockPos = BlockPos(pos.x, pos.y+1, pos.z)
    val blockState = world.getBlockState(blockPos)
    val block = blockState.block

    // Only mine block above if
    // - tool is suitable for the block
    // - block is maximally 25% harder than the original one
    if (miner !is PlayerEntity
        || !stack.isSuitableFor(blockState)
        || state.block.hardness < blockState.block.hardness * 0.75) return@PostMineModifier


    // Break block
    block.onBreak(world, blockPos, blockState, miner)
    if (world.removeBlock(blockPos, false)) {
        block.onBroken(world, blockPos, blockState)
        block.afterBreak(world, miner, blockPos, blockState, world.getBlockEntity(blockPos), stack.copy())
    }

    // Post mine
    if (!world.isClient && state.getHardness(world, blockPos) != 0.0f) {
        stack.damage(1, miner) { it.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND) }
    }
    miner.incrementStat(Stats.USED.getOrCreateStat(stack.item))
}