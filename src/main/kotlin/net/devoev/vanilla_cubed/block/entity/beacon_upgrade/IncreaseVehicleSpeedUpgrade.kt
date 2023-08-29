package net.devoev.vanilla_cubed.block.entity.beacon_upgrade

import net.devoev.vanilla_cubed.block.entity.beacon_upgrade.IncreaseVehicleSpeedUpgrade.INCREASE_RANGE
import net.devoev.vanilla_cubed.block.enums.isCurved
import net.devoev.vanilla_cubed.mixin.AbstractMinecartEntityMixin
import net.devoev.vanilla_cubed.mixin.BoatEntityMixin
import net.devoev.vanilla_cubed.util.math.toVec3d
import net.minecraft.block.AbstractRailBlock
import net.minecraft.entity.vehicle.BoatEntity
import net.minecraft.entity.vehicle.MinecartEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable

/**
 * Increases the speed of vehicles like [minecarts][MinecartEntity] by [INCREASE_RANGE].
 * Range of this upgrade is increased by [INCREASE_RANGE].
 */
object IncreaseVehicleSpeedUpgrade : ToggledUpgrade() {

    private const val INCREASE_SPEED = 3
    private const val INCREASE_RANGE = 5
    private const val CURVED_SPEED = 0.3

    /**
     * Injection for [AbstractMinecartEntityMixin.increaseMaxSpeed].
     */
    // TODO: Prevent derailing
    fun injectMinecart(pos: BlockPos, world: World, cir: CallbackInfoReturnable<Double>) {
        if (!inRange(pos.toVec3d())) return

        val state = world.getBlockState(pos)
        val block = state.block
        if (block !is AbstractRailBlock) return
        if (state.get(block.shapeProperty).isCurved) {
            cir.returnValue = CURVED_SPEED
        } else {
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
            .map { it.expand(INCREASE_RANGE*it.xLength, 0.0, INCREASE_RANGE*it.zLength) }
            .any { pos in it }
}