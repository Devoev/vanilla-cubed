package net.devoev.vanilla_cubed.util.math

import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.GlobalPos
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World

/**
 * Converse this [BlockPos] to a [Vec3d].
 */
fun BlockPos.toVec3d() = Vec3d(this.x.toDouble(), this.y.toDouble(), this.z.toDouble())

/**
 * Converts this [BlockPos] to a [GlobalPos].
 */
fun BlockPos.toGlobalPos(world: World): GlobalPos = GlobalPos.create(world.registryKey, this)