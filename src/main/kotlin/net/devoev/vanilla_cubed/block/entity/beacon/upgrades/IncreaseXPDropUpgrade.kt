package net.devoev.vanilla_cubed.block.entity.beacon.upgrades

import net.devoev.vanilla_cubed.block.entity.beacon.upgrades.IncreaseXPDropUpgrade.INCREASE_XP
import net.minecraft.util.math.Vec3d
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable

/**
 * Increases the XP drop of mobs by [INCREASE_XP].
 */
object IncreaseXPDropUpgrade : ToggledUpgrade() {

    private const val INCREASE_XP = 1.5

    /**
     * Increases the XP drop
     */
    fun increaseXP(pos: Vec3d, cir: CallbackInfoReturnable<Int>) {
        if (inRange(pos)) cir.returnValue = (cir.returnValue * INCREASE_XP).toInt()
    }
}