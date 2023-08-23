package net.devoev.vanilla_cubed.block.entity.beacon_upgrade

import net.devoev.vanilla_cubed.mixin.AbstractMinecartEntityMixin
import net.devoev.vanilla_cubed.util.math.toVec3d
import net.minecraft.entity.vehicle.MinecartEntity
import net.minecraft.tag.BlockTags
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable

/**
 * Increases the speed of vehicles like [minecarts][MinecartEntity]
 */
object IncreaseVehicleSpeed : ToggledUpgrade() {

    /**
     * Injection for [AbstractMinecartEntityMixin.increaseVelocityMultiplier].
     * TODO: Change mixin methods
     */
    fun injectMinecart(pos: BlockPos, world: World, cir: CallbackInfoReturnable<Float>) {
        if (inRange(pos.toVec3d()) && world.getBlockState(pos).isIn(BlockTags.RAILS)) {
            cir.returnValue = 10f
        }
    }
}