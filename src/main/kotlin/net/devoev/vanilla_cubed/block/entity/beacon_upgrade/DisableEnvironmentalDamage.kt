package net.devoev.vanilla_cubed.block.entity.beacon_upgrade

import net.devoev.vanilla_cubed.util.math.toVec3d
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Box

/**
 * A beacon upgrade that prevents environmental damage like fire spread.
 */
val disableEnvironmentalDamage = beaconUpgrade {

    activate {
        activeRanges[it.pos] = it.beaconRange ?: return@activate
    }

    deactivate {
        activeRanges.remove(it.pos)
    }
}

/**
 * A map of active ranges by this upgrade.
 */
private val activeRanges: MutableMap<BlockPos, Box> = mutableMapOf()

/**
 * Whether the block pos is in range of an active [disableEnvironmentalDamage] upgrade.
 */
fun BlockPos.checkRange(): Boolean = activeRanges.values.any { this.toVec3d() in it }