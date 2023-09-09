package net.devoev.vanilla_cubed.block.entity.beacon.upgrades

import net.devoev.vanilla_cubed.mixin.EnderDragonEntityMixin
import net.devoev.vanilla_cubed.mixin.EndermanEntityPickUpBlockGoalMixin
import net.devoev.vanilla_cubed.mixin.FireballEntityMixin
import net.devoev.vanilla_cubed.mixin.WorldMixin
import net.minecraft.entity.boss.dragon.EnderDragonEntity
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World.ExplosionSourceType
import net.minecraft.world.explosion.Explosion
import net.minecraft.world.explosion.Explosion.DestructionType
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable

/**
 * Disables mob griefing in the beacons range.
 */
object DisableMobGriefingUpgrade : ToggledUpgrade() {

    /**
     * Disables explosions by setting the destruction type to [Explosion.DestructionType.KEEP].
     * @see WorldMixin.disableExplosion
     */
    fun disableFire(type: DestructionType, x: Double, y: Double, z: Double, explosionSourceType: ExplosionSourceType): DestructionType {
        return if (explosionSourceType == ExplosionSourceType.MOB && inRange(Vec3d(x,y,z))) DestructionType.KEEP
        else type
    }

    /**
     * Disables the fireball explosion fire by updating the local [bl] variable.
     * @see FireballEntityMixin.disableFire
     */
    fun disableFire(pos: Vec3d, bl: Boolean): Boolean {
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