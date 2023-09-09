package net.devoev.vanilla_cubed.networking

import net.devoev.vanilla_cubed.block.entity.beacon.upgrades.BeaconUpgrade
import net.devoev.vanilla_cubed.block.entity.beacon.BeaconUpgrades
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
 * Writes the given [upgrades] to this packet by storing their canonical index in [BeaconUpgrades]
 */
fun PacketByteBuf.writeBeaconUpgrades(upgrades: List<BeaconUpgrade?>): PacketByteBuf {
    return writeIntArray(upgrades.map { BeaconUpgrades.indexOf(it) }.toIntArray())
}

/**
 * Reads the [BeaconUpgrade] entries of this packet.
 */
fun PacketByteBuf.readBeaconUpgrades(): List<BeaconUpgrade?> {
    return readIntArray().map { BeaconUpgrades.getOrNull(it) }
}

/**
 * Reads the canonical index of the [BeaconUpgrade] entry of this packet.
 */
fun PacketByteBuf.readBeaconUpgradeIndex(): Int {
    return readNullable { it.readInt() } ?: error("No beacon upgrade saved in this packet.")
}