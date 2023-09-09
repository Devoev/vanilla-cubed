package net.devoev.vanilla_cubed.block.entity.beacon.upgrades

import net.devoev.vanilla_cubed.block.entity.beacon.upgrades.IncreaseMinecartSpeedUpgrade.INCREASE_RANGE
import net.devoev.vanilla_cubed.block.enums.isCurved
import net.devoev.vanilla_cubed.mixin.AbstractMinecartEntityMixin
import net.devoev.vanilla_cubed.util.math.toVec3d
import net.minecraft.block.AbstractRailBlock
import net.minecraft.entity.vehicle.MinecartEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable

/**
 * Increases the speed of [minecarts][MinecartEntity] by [INCREASE_RANGE].
 * Range of this upgrade is increased by [INCREASE_RANGE].
 */
object IncreaseMinecartSpeedUpgrade : ToggledUpgrade() {

    private const val INCREASE_SPEED = 3
    private const val INCREASE_RANGE = 5
    private const val CURVED_SPEED = 0.3

    /**
     * Injection for [AbstractMinecartEntityMixin.increaseMaxSpeed].
     */
    // TODO: Prevent derailing
    fun increaseMaxSpeed(pos: BlockPos, world: World, cir: CallbackInfoReturnable<Double>) {
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

    override fun inRange(pos: Vec3d) =  activeRanges.values
            .map { it.expand(INCREASE_RANGE*it.xLength, 0.0, INCREASE_RANGE*it.zLength) }
            .any { pos in it }
}