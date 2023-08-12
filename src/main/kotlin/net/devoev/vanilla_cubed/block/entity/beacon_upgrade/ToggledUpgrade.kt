package net.devoev.vanilla_cubed.block.entity.beacon_upgrade

import net.devoev.vanilla_cubed.block.entity.ModBeaconBlockEntity
import net.minecraft.block.BlockState
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Box
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World

/**
 * A [BeaconUpgrade] that applies a static effect if toggled active.
 */
abstract class ToggledUpgrade : BeaconUpgrade {

    /**
     * A map of all ranges that are activated by various beacons.
     */
    private val activeRanges: MutableMap<BlockPos, Box> = mutableMapOf()

    override fun activate(blockEntity: ModBeaconBlockEntity) {
        activeRanges[blockEntity.pos] = blockEntity.beaconRange ?: return
    }

    override fun deactivate(blockEntity: ModBeaconBlockEntity) {
        activeRanges.remove(blockEntity.pos)
    }

    override fun ModBeaconBlockEntity.tick(world: World, pos: BlockPos, state: BlockState) = Unit

    /**
     * Whether the given [pos] is inside any of the [activeRanges].
     */
    fun inRange(pos: Vec3d) = activeRanges.values.any { pos in it }
}