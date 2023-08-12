package net.devoev.vanilla_cubed.block.entity.beacon_upgrade

import net.devoev.vanilla_cubed.mixin.FireBlockMixin
import net.devoev.vanilla_cubed.util.math.toVec3d
import net.minecraft.block.Block
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.BlockPos
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo

/**
 * A beacon upgrade that prevents environmental damage like fire spread.
 */
object DisableEnvironmentalDamageUpgrade : ToggledUpgrade() {

    /**
     * Injection function for [FireBlockMixin.disableFireTick].
     */
    fun inject(block: Block, world: ServerWorld, pos: BlockPos, ci: CallbackInfo) {
        if (inRange(pos.toVec3d())) {
            world.createAndScheduleBlockTick(pos, block, 30 + world.random.nextInt(10))
            ci.cancel()
        }
    }
}