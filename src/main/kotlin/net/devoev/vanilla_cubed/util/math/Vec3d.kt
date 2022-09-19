package net.devoev.vanilla_cubed.util.math

import net.minecraft.util.math.Vec3d

operator fun Vec3d.plus(other: Vec3d): Vec3d = add(other)

operator fun Vec3d.minus(other: Vec3d): Vec3d = subtract(other)

operator fun Vec3d.times(other: Number): Vec3d = multiply(other.toDouble())

operator fun Vec3d.times(other: Vec3d): Double = dotProduct(other)

operator fun Vec3d.div(other: Number): Vec3d = multiply(1/other.toDouble())

operator fun Vec3d.unaryMinus(): Vec3d = negate()

fun Vec3d(x: Number, y: Number, z: Number) = Vec3d(x.toDouble(), y.toDouble(), z.toDouble())