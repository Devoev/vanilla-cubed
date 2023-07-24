package net.devoev.vanilla_cubed.networking

import net.devoev.vanilla_cubed.block.entity.behavior.BeaconUpgrade
import net.devoev.vanilla_cubed.block.entity.behavior.BeaconUpgrades
import net.devoev.vanilla_cubed.util.math.Vec3d
import net.minecraft.network.PacketByteBuf
import net.minecraft.util.math.Vec3d

/**
 * Write the given [vec] to this packet by converting it to an int array.
 */
fun PacketByteBuf.writeVec3d(vec: Vec3d): PacketByteBuf
    = writeIntArray(intArrayOf(vec.x.toInt(), vec.y.toInt(), vec.z.toInt()))

/**
 * Reads the [Vec3d] entry of this packet.
 */
fun PacketByteBuf.readVec3d(): Vec3d {
    val arr = readIntArray(3)
    return Vec3d(arr[0], arr[1], arr[2])
}

/**
 * Writes the given [upgrade] to this packet by storing its canonical index in [BeaconUpgrades]
 */
fun PacketByteBuf.writeBeaconUpgrade(upgrade: BeaconUpgrade): PacketByteBuf {
    writeNullable(upgrade) { packet, upgrade2 -> packet.writeInt(BeaconUpgrades.indexOf(upgrade2)) }
    return this
}

/**
 * Reads the [BeaconUpgrade] entry of this packet.
 */
fun PacketByteBuf.readBeaconUpgrade(): BeaconUpgrade? {
    val i = readNullable { it.readInt() } ?: error("No beacon upgrade saved in this packet.")
    return BeaconUpgrades[i]
}

/**
 * Reads the canonical index of the [BeaconUpgrade] entry of this packet.
 */
fun PacketByteBuf.readBeaconUpgradeIndex(): Int {
    return readNullable { it.readInt() } ?: error("No beacon upgrade saved in this packet.")
}