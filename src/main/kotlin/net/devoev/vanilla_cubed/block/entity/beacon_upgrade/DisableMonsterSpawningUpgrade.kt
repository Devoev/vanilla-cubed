package net.devoev.vanilla_cubed.block.entity.beacon_upgrade

import net.devoev.vanilla_cubed.block.entity.ModBeaconBlockEntity
import net.minecraft.block.BlockState
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

/**
 * Disables all monster spawning in the beacons range.
 */
object DisableMonsterSpawningUpgrade : BeaconUpgrade {

    override fun ModBeaconBlockEntity.accept(world: World, pos: BlockPos, state: BlockState) {
        // TODO("Not yet implemented")
    }
}