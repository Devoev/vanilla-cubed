package net.devoev.vanilla_cubed.block.entity.beacon_upgrade

import net.devoev.vanilla_cubed.block.entity.beacon_upgrade.IncreaseVehicleSpeed.INCREASE_RANGE
import net.devoev.vanilla_cubed.mixin.AbstractMinecartEntityMixin
import net.devoev.vanilla_cubed.mixin.BoatEntityMixin
import net.devoev.vanilla_cubed.util.math.toVec3d
import net.minecraft.entity.vehicle.BoatEntity
import net.minecraft.entity.vehicle.MinecartEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable

/**
 * Increases the speed of vehicles like [minecarts][MinecartEntity] by [INCREASE_RANGE].
 * Range of this upgrade is increased by [INCREASE_RANGE].
 */
object IncreaseVehicleSpeed : ToggledUpgrade() {

    private const val INCREASE_SPEED = 5
    private const val INCREASE_RANGE = 3

    /**
     * Injection for [AbstractMinecartEntityMixin.increaseMaxSpeed].
     */
    // TODO: Prevent derailing
    fun injectMinecart(pos: BlockPos, cir: CallbackInfoReturnable<Double>) {
        if (inRange(pos.toVec3d())) {
            cir.returnValue *= INCREASE_SPEED
        }
    }

    /**
     * Injection for [BoatEntityMixin.increaseVelocity].
     * TODO: Choose different injection point
     */
    fun injectBoat(entity: BoatEntity) {
        if (inRange(entity.pos)) {
            val v = entity.velocity
            entity.velocity = Vec3d(v.x * INCREASE_SPEED, v.y, v.z * INCREASE_SPEED)
        }
    }

    override fun inRange(pos: Vec3d) =  activeRanges.values
            .map { it.expand(INCREASE_SPEED*it.xLength, 0.0, INCREASE_RANGE*it.zLength) }
            .any { pos in it }
}