package net.devoev.vanilla_cubed.block.entity.behavior

import net.devoev.vanilla_cubed.block.entity.ModBeaconBlockEntity
import net.minecraft.block.BlockState
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World


/**
 * A beacon upgrade stage. Modifies the [ModBeaconBlockEntity.tick] function by injecting the [invoke] method.
 */
fun interface BeaconUpgrade {

    operator fun invoke(world: World, pos: BlockPos, state: BlockState, blockEntity: ModBeaconBlockEntity)

    companion object {

        /**
         * The default [BeaconUpgrade] that does nothing.
         */
        val EMPTY = BeaconUpgrade { _, _, _, _ ->  }
    }
}