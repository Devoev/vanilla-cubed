package net.devoev.vanilla_cubed.block.entity.behavior

import net.devoev.vanilla_cubed.block.entity.ModBeaconBlockEntity
import net.minecraft.block.BlockState
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World


/**
 * Modifies the [ModBeaconBlockEntity.tick] function by injecting the [invoke] method.
 */
fun interface BeaconTickBehavior {

    operator fun invoke(world: World, pos: BlockPos, state: BlockState, blockEntity: ModBeaconBlockEntity)
}