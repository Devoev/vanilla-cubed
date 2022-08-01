package net.devoev.vanilla_cubed.item.behavior

import net.devoev.vanilla_cubed.block.isIn
import net.minecraft.item.Item
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.tag.BlockTags
import net.minecraft.util.math.BlockPos

/**
 * Detects nearby ores in the given [range] and plays a sound.
 */
class DetectOresBehavior(private val range: Int) : PostMineBehavior<Item> {

    override fun accept(item: Item, params: PostMineParams) {
        val blocks = BlockPos.iterateOutwards(params.pos, range, range, range).map { params.world?.getBlockState(it) }
        if (blocks.any { it?.isIn(ORES) == true })
            params.world?.playSound(
                null,
                params.pos,
                SoundEvents.BLOCK_AMETHYST_BLOCK_CHIME,
                SoundCategory.AMBIENT,
                25f,
                1f)
    }

    companion object {
        val ORES = listOf(BlockTags.DIAMOND_ORES, BlockTags.COAL_ORES, BlockTags.COPPER_ORES, BlockTags.EMERALD_ORES,
            BlockTags.GOLD_ORES, BlockTags.IRON_ORES, BlockTags.LAPIS_ORES, BlockTags.REDSTONE_ORES)
    }
}