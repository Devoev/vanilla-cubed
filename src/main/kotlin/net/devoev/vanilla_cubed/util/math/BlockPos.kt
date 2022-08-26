package net.devoev.vanilla_cubed.util.math

import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d

/**
 * Converse this [BlockPos] to a [Vec3d].
 */
fun BlockPos.toVec3d() = Vec3d(this.x.toDouble(), this.y.toDouble(), this.z.toDouble())