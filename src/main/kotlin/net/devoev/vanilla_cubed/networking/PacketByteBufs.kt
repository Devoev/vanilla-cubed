package net.devoev.vanilla_cubed.networking

import net.devoev.vanilla_cubed.util.math.Vec3d
import net.minecraft.network.PacketByteBuf
import net.minecraft.util.math.Vec3d

/**
 * Write the given [vec] to this packet by converting it to an int array.
 */
fun PacketByteBuf.writeVec3d(vec: Vec3d): PacketByteBuf
    = writeIntArray(intArrayOf(vec.x.toInt(), vec.y.toInt(), vec.z.toInt()))

fun PacketByteBuf.readVec3d(): Vec3d {
    val arr = readIntArray(3)
    return Vec3d(arr[0], arr[1], arr[2])
}