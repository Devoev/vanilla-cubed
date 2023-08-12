package net.devoev.vanilla_cubed.block.entity.beacon_upgrade

import net.devoev.vanilla_cubed.util.math.toVec3d
import net.minecraft.util.math.BlockPos

/**
 * A beacon upgrade that prevents environmental damage like fire spread.
 */
object DisableEnvironmentalDamageUpgrade : ToggledUpgrade() {

    /**
     * Whether the block pos is in range of an active [DisableEnvironmentalDamageUpgrade] upgrade.
     */
    operator fun invoke(pos: BlockPos): Boolean = inRange(pos.toVec3d())
}