package net.devoev.vanilla_cubed.block.entity.beacon_upgrade

import net.devoev.vanilla_cubed.mixin.CreeperEntityMixin
import net.devoev.vanilla_cubed.mixin.FireballEntityMixin
import net.devoev.vanilla_cubed.mixin.WitherSkullEntityMixin
import net.devoev.vanilla_cubed.util.math.toVec3d
import net.minecraft.util.math.BlockPos
import net.minecraft.world.explosion.Explosion

/**
 * Disables mob griefing in the beacons range.
 */
object DisableMobGriefingUpgrade : ToggledUpgrade() {

    /**
     * Injection for [CreeperEntityMixin.disableExplosion] and [WitherSkullEntityMixin.disableExplosion].
     */
    fun injectExplosionDestructionType(pos: BlockPos, type: Explosion.DestructionType): Explosion.DestructionType {
        return if (inRange(pos.toVec3d())) Explosion.DestructionType.NONE else type
    }

    /**
     * Injection for [FireballEntityMixin.disableExplosion].
     */
    fun injectFireballExplosion(pos: BlockPos, bl: Boolean): Boolean {
        return bl && !inRange(pos.toVec3d())
    }

    // TODO: Add more injections
}