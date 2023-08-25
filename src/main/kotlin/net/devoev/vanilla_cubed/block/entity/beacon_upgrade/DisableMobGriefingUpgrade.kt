package net.devoev.vanilla_cubed.block.entity.beacon_upgrade

import net.devoev.vanilla_cubed.mixin.CreeperEntityMixin
import net.devoev.vanilla_cubed.mixin.EndermanEntityMixin
import net.devoev.vanilla_cubed.mixin.FireballEntityMixin
import net.devoev.vanilla_cubed.mixin.WitherSkullEntityMixin
import net.minecraft.block.BlockState
import net.minecraft.util.math.Vec3d
import net.minecraft.world.explosion.Explosion
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable

/**
 * Disables mob griefing in the beacons range.
 */
object DisableMobGriefingUpgrade : ToggledUpgrade() {

    /**
     * Injection for [CreeperEntityMixin.disableExplosion] and [WitherSkullEntityMixin.disableExplosion].
     */
    fun injectExplosionDestructionType(pos: Vec3d, type: Explosion.DestructionType): Explosion.DestructionType {
        return if (inRange(pos)) Explosion.DestructionType.NONE else type
    }

    /**
     * Injection for [FireballEntityMixin.disableExplosion].
     */
    fun injectFireballExplosion(pos: Vec3d, bl: Boolean): Boolean {
        return bl && !inRange(pos)
    }

    /**
     * Sets the carried block of the enderman to `null`.
     * @see EndermanEntityMixin.removeCarriedBlock
     */
    fun disableEndermanBlockPickup(pos: Vec3d, cir: CallbackInfoReturnable<BlockState>) {
        if (inRange(pos)) cir.returnValue = null
    }

    // TODO: Add more injections
}