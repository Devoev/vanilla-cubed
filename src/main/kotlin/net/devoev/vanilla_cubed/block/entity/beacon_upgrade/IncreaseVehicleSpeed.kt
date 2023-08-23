package net.devoev.vanilla_cubed.block.entity.beacon_upgrade

import net.devoev.vanilla_cubed.block.entity.beacon_upgrade.IncreaseVehicleSpeed.INCREASE
import net.devoev.vanilla_cubed.mixin.AbstractMinecartEntityMixin
import net.devoev.vanilla_cubed.util.math.toVec3d
import net.minecraft.entity.vehicle.MinecartEntity
import net.minecraft.util.math.BlockPos
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable

/**
 * Increases the speed of vehicles like [minecarts][MinecartEntity] by [INCREASE].
 */
object IncreaseVehicleSpeed : ToggledUpgrade() {

    private const val INCREASE = 2

    /**
     * Injection for [AbstractMinecartEntityMixin.increaseMaxSpeed].
     */
    fun injectMinecart(pos: BlockPos, cir: CallbackInfoReturnable<Double>) {
        if (inRange(pos.toVec3d())) {
            cir.returnValue *= INCREASE
        }
    }
}