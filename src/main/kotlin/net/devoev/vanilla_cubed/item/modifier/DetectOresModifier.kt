package net.devoev.vanilla_cubed.item.modifier

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
@Deprecated("Replace with different modifier")
fun detectOresModifierOf(rangeXZ: Int, rangeY: Int) = PostMineModifier<Item> { _, world, _, pos, _ ->
    if (world.isClient) return@PostMineModifier

    val closest = BlockPos.findClosest(pos, rangeXZ, rangeY) { world.getBlockState(it)?.isIn(ORES) == true }
    val orePos: BlockPos = closest.getOrNull() ?: return@PostMineModifier
    val distance = sqrt(orePos.getSquaredDistance(pos))

    world.playSound(
        null,
        pos,
        SoundEvents.BLOCK_AMETHYST_BLOCK_CHIME,
        SoundCategory.AMBIENT,
        (20 - 5*distance).toFloat(), //TODO: More general formula
        1f)
}
