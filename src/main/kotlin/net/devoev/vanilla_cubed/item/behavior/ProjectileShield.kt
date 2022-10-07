package net.devoev.vanilla_cubed.item.behavior

import net.devoev.vanilla_cubed.util.math.times
import net.devoev.vanilla_cubed.util.wearsEnderite
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.particle.ParticleTypes
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents

/**
 * Protects the [player] from incoming projectiles, by reflecting them.
 * @return True, if the projectile could successfully be deflected.
 */
fun protectFromProjectiles(player: PlayerEntity, source: DamageSource): Boolean {
    if (!player.wearsEnderite() || !source.isProjectile) return false
    val world = player.world
    val entity = source.source ?: return false
    entity.run {
        if (!world.isClient) {
            val dir = velocity.normalize() * 0.1
            updatePosition(x + dir.x, y + dir.y, z + dir.z)
            world.playSound(
                null,
                blockPos,
                SoundEvents.ITEM_SHIELD_BLOCK,
                SoundCategory.PLAYERS,
                10f,
                3f
            )
        } else {
            //TODO: Particle effects using networking
            repeat(5) {
                world.addParticle(ParticleTypes.EXPLOSION, x, y, z, 1.0, 1.0, 1.0)
            }
        }
    }


    return true
}