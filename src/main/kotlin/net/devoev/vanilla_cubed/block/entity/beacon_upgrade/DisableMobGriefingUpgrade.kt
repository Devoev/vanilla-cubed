package net.devoev.vanilla_cubed.block.entity.beacon_upgrade

import net.devoev.vanilla_cubed.mixin.CreeperEntityMixin
import net.devoev.vanilla_cubed.mixin.EndermanEntityPickUpBlockGoalMixin
import net.devoev.vanilla_cubed.mixin.FireballEntityMixin
import net.devoev.vanilla_cubed.mixin.WitherSkullEntityMixin
import net.minecraft.util.math.Vec3d
import net.minecraft.world.explosion.Explosion
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable

/**
 * Disables mob griefing in the beacons range.
 */
object DisableMobGriefingUpgrade : ToggledUpgrade() {

    /**
     * Disables explosions by setting the destruction type to [Explosion.DestructionType.NONE].
     * @see CreeperEntityMixin.disableExplosion
     * @see WitherSkullEntityMixin.disableExplosion
     */
    fun disableExplosion(pos: Vec3d, type: Explosion.DestructionType): Explosion.DestructionType {
        return if (inRange(pos)) Explosion.DestructionType.NONE else type
    }

    /**
     * Disables the fireball explosion by updating the local [bl] variable.
     * @see FireballEntityMixin.disableExplosion
     */
    fun disableExplosion(pos: Vec3d, bl: Boolean): Boolean {
        return bl && !inRange(pos)
    }

    /**
     * Sets the carried block of the enderman to `null`.
     * @see EndermanEntityPickUpBlockGoalMixin.disableStart
     */
    fun disableEndermanBlockPickup(pos: Vec3d, cir: CallbackInfoReturnable<Boolean>) {
        if (inRange(pos)) cir.returnValue = false
    }

    // TODO: Add more injections
}