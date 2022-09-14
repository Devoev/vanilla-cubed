package net.devoev.vanilla_cubed.item.behavior

import net.fabricmc.fabric.api.tag.convention.v1.ConventionalBlockTags.ORES
import net.minecraft.item.Item
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.util.math.BlockPos
import kotlin.jvm.optionals.getOrNull
import kotlin.math.sqrt

/**
 * Detects nearby ores in the given [rangeXZ] and plays a sound.
 */
class DetectOresBehavior(private val rangeXZ: Int, private val rangeY: Int) : PostMineBehavior<Item> {

    @OptIn(ExperimentalStdlibApi::class)
    override fun accept(item: Item, params: PostMineParams) {
        val closest = BlockPos.findClosest(params.pos, rangeXZ, rangeY) { params.world?.getBlockState(it)?.isIn(ORES) == true }
        val orePos: BlockPos = closest.getOrNull() ?: return
        val distance = sqrt(orePos.getSquaredDistance(params.pos))

        params.world?.playSound(
            null,
            params.pos,
            SoundEvents.BLOCK_AMETHYST_BLOCK_CHIME,
            SoundCategory.AMBIENT,
            (20 - 5*distance).toFloat(), //TODO: More general formula
            1f)
    }
}