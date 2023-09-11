package net.devoev.vanilla_cubed.item.modifier

import net.devoev.vanilla_cubed.entity.wearsEnderite
import net.devoev.vanilla_cubed.mixin.PlayerEntityMixin
import net.devoev.vanilla_cubed.networking.Channels
import net.devoev.vanilla_cubed.networking.writeVec3d
import net.devoev.vanilla_cubed.util.math.times
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
import kotlin.random.Random


/**
 * Stops projectiles from hitting the player if he wears full enderite armor.
 * @see PlayerEntityMixin.protectFromProjectiles
 */
fun protectFromProjectiles(player: PlayerEntity, source: DamageSource, cir: CallbackInfoReturnable<Boolean>) {
    if (player.wearsEnderite() && protectFromProjectiles(player, source, 0.5)) {
        cir.returnValue = false
    }
}

/**
 * Protects the [player] from incoming projectiles, by reflecting them.
 * @param p The probability of deflecting the projectile.
 * @return `true`, if the projectile could successfully be deflected.
 */
private fun protectFromProjectiles(player: PlayerEntity, source: DamageSource, p: Double): Boolean {
    if (!source.isIndirect // TODO: isIndirect == isProjectile ??
        || player.world.isClient
        || Random.nextDouble(1.0) > p
        ) return false
    val entity = source.source ?: return false
    entity.run {
        val r = blockPos
        val dr = velocity.normalize() * 3
        updatePosition(x + dr.x, y + dr.y, z + dr.z)
        velocity *= -2
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
            .writeBlockPos(r)
            .writeVec3d(dr*1000)
        ServerPlayNetworking.send(player, Channels.ENDERITE_SHIELD_SPAWN_PARTICLES, buf)
    }
    return true
}