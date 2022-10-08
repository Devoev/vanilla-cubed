package net.devoev.vanilla_cubed.networking

import net.devoev.vanilla_cubed.util.math.*
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking.PlayChannelHandler
import net.minecraft.particle.ParticleTypes
import net.minecraft.util.math.Vec3d
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Summons the shielding particles, if the enderite armor blocks incoming projectiles.
 */
val ENDERITE_SHIELD_PARTICLES = PlayChannelHandler { client, _, buf, _ ->
    val pos = buf.readBlockPos().toVec3d() + Vec3d(0.45, 0.45, 0.45)
    val n = buf.readVec3d().normalize()
    val ex = Vec3d(1,0,0) //TODO: Find linear independent vector
    val ez = Vec3d(0,0,1)

    // span vectors
    val v1 = (ex - ex.proj(n)).normalize()
    val v2 = (ez - ez.proj(n) - ez.proj(v1)).normalize()

    val r = 5
    val uRange = -r..r

    val draw = mutableSetOf<Vec3d>()
    for (u in uRange) {
        val vMax = sqrt((r.toDouble().pow(2) - u.toDouble().pow(2))).toInt()
        for (v in -vMax..vMax)
            draw.add((v1*u + v2*v)/r)
    }

    client.execute {
        (pos - (n/2)).run {
            for (vec in draw)
                client.world?.addParticle(
                    ParticleTypes.GLOW,
                    x + vec.x,
                    y + vec.y,
                    z + vec.z,
                    0.0,
                    0.0,
                    0.0
                )
        }
    }
}