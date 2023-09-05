package net.devoev.vanilla_cubed.block.entity.beacon_upgrade

import net.devoev.vanilla_cubed.mixin.*
import net.minecraft.entity.boss.dragon.EnderDragonEntity
import net.minecraft.util.math.Vec3d
import net.minecraft.world.explosion.Explosion
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable

/**
 * Disables mob griefing in the beacons range.
 */
object DisableMobGriefingUpgrade : ToggledUpgrade() {

    /**
     * Disables explosions by setting the destruction type to [Explosion.DestructionType.KEEP].
     * @see CreeperEntityMixin.disableExplosion
     * @see WitherSkullEntityMixin.disableExplosion
     * @see WitherEntityMixin.disableExplosion
     */
    fun disableExplosion(pos: Vec3d, type: Explosion.DestructionType): Explosion.DestructionType {
        return if (inRange(pos)) Explosion.DestructionType.KEEP else type
    }

    /**
     * Disables the fireball explosion by updating the local [bl] variable.
     * @see FireballEntityMixin.disableExplosion
     */
    fun disableExplosion(pos: Vec3d, bl: Boolean): Boolean {
        return bl && !inRange(pos)
    }

    /**
     * Sets the return value of the enderman's pickup goal to `false`.
     * @see EndermanEntityPickUpBlockGoalMixin.disableEndermanBlockPickup
     */
    fun disableEndermanBlockPickup(pos: Vec3d, cir: CallbackInfoReturnable<Boolean>) {
        if (inRange(pos)) cir.returnValue = false
    }

    /**
     * Disables block destruction by the ender dragon by returning `true` in [EnderDragonEntity.destroyBlocks].
     * @see EnderDragonEntityMixin.disableEnderDragonBlockDestruction
     */
    fun disableEnderDragonBlockDestruction(pos: Vec3d, cir: CallbackInfoReturnable<Boolean>) {
        if (inRange(pos)) cir.returnValue = true
    }

    // TODO: Add more injections
}