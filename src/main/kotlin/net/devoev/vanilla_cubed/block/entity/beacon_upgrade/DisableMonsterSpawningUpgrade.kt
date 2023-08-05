package net.devoev.vanilla_cubed.block.entity.beacon_upgrade

import net.devoev.vanilla_cubed.block.entity.ModBeaconBlockEntity
import net.minecraft.block.BlockState
import net.minecraft.entity.Entity
import net.minecraft.entity.mob.HostileEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Box
import net.minecraft.world.World

/**
 * Disables all monster spawning in the beacons range.
 */
class DisableMonsterSpawningUpgrade : BeaconUpgrade {

    override fun activate(blockEntity: ModBeaconBlockEntity) {
        activeRanges[blockEntity] = blockEntity.beaconRange
    }

    override fun deactivate(blockEntity: ModBeaconBlockEntity) {
        activeRanges.remove(blockEntity)
    }

    override fun ModBeaconBlockEntity.tick(world: World, pos: BlockPos, state: BlockState) = Unit

    companion object {

        /**
         * A map of active ranges by this upgrade.
         */
        val activeRanges: MutableMap<ModBeaconBlockEntity, Box> = mutableMapOf()

        /**
         * Whether the spawn of this entity should be canceled by a beacon.
         */
        fun Entity.checkSpawn(): Boolean = this is HostileEntity && activeRanges.values.any { pos in it }
    }
}