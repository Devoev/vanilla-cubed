package net.devoev.vanilla_cubed.block.entity.beacon_upgrade

import net.devoev.vanilla_cubed.block.entity.ModBeaconBlockEntity
import net.minecraft.block.BlockState
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World


/**
 * A beacon upgrade stage. Modifies the [ModBeaconBlockEntity.tick] function by injecting the [invoke] method.
 */
interface BeaconUpgrade {

    /**
     * Called on upgrades activation.
     */
    fun activate(blockEntity: ModBeaconBlockEntity)

    /**
     * Called on upgrades deactivation.
     */
    fun deactivate(blockEntity: ModBeaconBlockEntity)

    /**
     * Called on each game tick.
     */
    fun ModBeaconBlockEntity.tick(world: World, pos: BlockPos, state: BlockState)

    operator fun invoke(world: World, pos: BlockPos, state: BlockState, blockEntity: ModBeaconBlockEntity)
        = blockEntity.tick(world, pos, state)

    /**
     * Creates a composed [BeaconUpgrade] that first runs this and then [after].
     */
    infix fun andThen(after: BeaconUpgrade): BeaconUpgrade = tickUpgrade { world, pos, state ->
        // TODO: DONT built a tickupgrade but a general one!
        invoke(world, pos, state, this)
        after(world, pos, state, this)
    }

    companion object {

        /**
         * The default [BeaconUpgrade] that does nothing.
         */
        val EMPTY = tickUpgrade { _, _, _ ->  }
    }
}

/**
 * Builds a [BeaconUpgrade] with the provided [tick] function.
 * [BeaconUpgrade.activate] and [BeaconUpgrade.deactivate] do nothing.
 */
fun tickUpgrade(tick: ModBeaconBlockEntity.(world: World, pos: BlockPos, state: BlockState) -> Unit): BeaconUpgrade {
    return object : BeaconUpgrade {

        override fun activate(blockEntity: ModBeaconBlockEntity) = Unit

        override fun deactivate(blockEntity: ModBeaconBlockEntity) = Unit

        override fun ModBeaconBlockEntity.tick(world: World, pos: BlockPos, state: BlockState) = tick(world, pos, state)

    }
}