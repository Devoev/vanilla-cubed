package net.devoev.vanilla_cubed.item.behavior

import net.devoev.vanilla_cubed.networking.Channels
import net.devoev.vanilla_cubed.networking.writeVec3d
import net.devoev.vanilla_cubed.util.math.times
import net.devoev.vanilla_cubed.util.wearsEnderite
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.client.gui.screen.pack.ResourcePackOrganizer.Pack
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.particle.ParticleTypes
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents

/**
 * Protects the [player] from incoming projectiles, by reflecting them.
 * @return True, if the projectile could successfully be deflected.
 */
fun protectFromProjectiles(player: PlayerEntity, source: DamageSource): Boolean {
    if (!player.wearsEnderite() || !source.isProjectile || player.world.isClient) return false
    val entity = source.source ?: return false
    entity.run {
        val dir = velocity.normalize() * 0.1
        updatePosition(x + dir.x, y + dir.y, z + dir.z)
        player.world.playSound(
            null,
            blockPos,
            SoundEvents.ENTITY_ENDERMAN_TELEPORT,
            SoundCategory.PLAYERS,
            10f,
            -10f
        )

        if (player !is ServerPlayerEntity) return@run
        val buf = PacketByteBufs.create()
            .writeBlockPos(entity.blockPos)
            .writeVec3d(velocity*1000)
        ServerPlayNetworking.send(player, Channels.ENDERITE_SHIELD_SPAWN_PARTICLES, buf)
    }
    return true
}