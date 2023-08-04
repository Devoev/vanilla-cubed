package net.devoev.vanilla_cubed.util.math

import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Box

/**
 * Creates a box of the given coordinates.
 */
fun boxOf(x1: Number, y1: Number, z1: Number, x2: Number, y2: Number, z2: Number)
    = Box(x1.toDouble(), y1.toDouble(), z1.toDouble(), x2.toDouble(), y2.toDouble(), z2.toDouble())

/**
 * Returns an iterator over all [BlockPos] contained in this box.
 * Iterates first in `x` direction, then `z` and then `y`.
 */
operator fun Box.iterator(): Iterator<BlockPos> = iterator {
    for (y in minY.toInt()..maxY.toInt())
        for (z in minZ.toInt()..maxZ.toInt())
            for (x in minX.toInt()..maxX.toInt())
                yield(BlockPos(x,y,z))
}

/**
 * Returns this box as an [Iterable].
 */
fun Box.asIterable(): Iterable<BlockPos> = Iterable { iterator() }

/**
 * Returns this box as a [Sequence].
 */
fun Box.asSequence(): Sequence<BlockPos> = Sequence { iterator() }

/**
 * Converts this box to a [List].
 */
fun Box.toList(): List<BlockPos> = asIterable().toList()

/**
 * Converts this box to a [Set].
 */
fun Box.toSet(): Set<BlockPos> = asIterable().toSet()