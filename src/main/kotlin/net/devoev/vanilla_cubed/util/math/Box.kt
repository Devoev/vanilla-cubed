package net.devoev.vanilla_cubed.util.math

import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Box

operator fun Box.iterator(): Iterator<BlockPos> {
    return object : AbstractIterator<BlockPos>() {

        var x = minX
        var y = minY
        var z = minZ

        override fun computeNext() {
            setNext(BlockPos(x,y,z))

            if (x < maxX) {
                x++
            } else {
                x = minX
                if (z < maxZ) {
                    z++
                } else {
                    z = minZ
                    if (y < maxY) {
                        y++
                    } else {
                        done()
                    }
                }
            }
        }
    }
}

/**
 * Converts this [Box] to a [List] of [BlockPos].
 */
fun Box.toList(): List<BlockPos> {
    val res = mutableListOf<BlockPos>()
    for (pos in this)
        res += pos
    return res
}