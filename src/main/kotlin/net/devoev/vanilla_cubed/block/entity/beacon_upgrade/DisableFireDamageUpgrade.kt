package net.devoev.vanilla_cubed.block.entity.beacon_upgrade

import net.devoev.vanilla_cubed.mixin.FireBlockMixin
import net.devoev.vanilla_cubed.mixin.LavaFluidMixin
import net.devoev.vanilla_cubed.mixin.LightningEntityMixin
import net.devoev.vanilla_cubed.util.math.toVec3d
import net.minecraft.block.Block
import net.minecraft.entity.LightningEntity
import net.minecraft.fluid.LavaFluid
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.BlockPos
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo

/**
 * A beacon upgrade that prevents environmental damage caused by fire.
 */
object DisableFireDamageUpgrade : ToggledUpgrade() {

    /**
     * Disables fire tick by cancelling the [ServerWorld.scheduleBlockTick] call.
     * @see FireBlockMixin.disableFireTick
     */
    fun disableFireTick(block: Block, world: ServerWorld, pos: BlockPos, ci: CallbackInfo) {
        if (inRange(pos.toVec3d())) {
            world.scheduleBlockTick(pos, block, 30 + world.random.nextInt(10))
            ci.cancel()
        }
    }

    /**
     * Disables lava fire tick by cancelling the [LavaFluid.onRandomTick] call.
     * @see LavaFluidMixin.disableLavaFireTick
     */
    fun disableLavaFireTick(pos: BlockPos, ci: CallbackInfo) {
        if (inRange(pos.toVec3d())) {
            ci.cancel()
        }
    }

    /**
     * Disables fire spawn of lightning strikes by cancelling the [LightningEntity.spawnFire] call.
     * @see LightningEntityMixin.disableSpawnFire
     */
    fun disableSpawnFire(entity: LightningEntity, ci: CallbackInfo) {
        if (inRange(entity.blockPos.toVec3d())) {
            ci.cancel()
        }
    }
}