package net.devoev.vanilla_cubed.block.entity.beacon_upgrade

import net.minecraft.util.math.Vec3d
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable

/**
 * Increases the XP drop of mobs by 50%.
 */
object IncreaseXPDropUpgrade : ToggledUpgrade() {

    /**
     * Increases the XP drop
     */
    fun increaseXP(pos: Vec3d, cir: CallbackInfoReturnable<Int>) {
        if (inRange(pos)) cir.returnValue = (cir.returnValue * 1.5).toInt()
    }
}