package net.devoev.vanilla_cubed.item.behavior

import net.devoev.vanilla_cubed.block.isIn
import net.minecraft.item.Item
import net.minecraft.tag.BlockTags
import net.minecraft.util.math.BlockPos

/**
 * Detects nearby ores in the given [range].
 */
class DetectOresBehavior(private val range: Int) : PostMineBehavior<Item> {

    override fun apply(item: Item, params: PostMineBehavior.Params): Boolean {
        val blocks = BlockPos.iterateOutwards(params.pos, range, range, range).map { params.world?.getBlockState(it) }
        if (blocks.any { it?.isIn(ORES) == true })
            println("Found ore!")
        return false
    }

    companion object {
        val ORES = listOf(BlockTags.DIAMOND_ORES, BlockTags.COAL_ORES, BlockTags.COPPER_ORES, BlockTags.EMERALD_ORES,
            BlockTags.GOLD_ORES, BlockTags.IRON_ORES, BlockTags.LAPIS_ORES, BlockTags.REDSTONE_ORES)
    }
}