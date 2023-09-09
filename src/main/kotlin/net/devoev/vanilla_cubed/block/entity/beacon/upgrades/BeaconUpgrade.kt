package net.devoev.vanilla_cubed.block.entity.beacon.upgrades

import net.devoev.vanilla_cubed.block.entity.ModBeaconBlockEntity
import net.minecraft.block.BlockState
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World


/**
 * A beacon upgrade stage. Modifies the [ModBeaconBlockEntity.tick] function by injecting
 * the [activate], [deactivate] and [tick] methods.
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

    /**
     * Calls [tick] on the given [blockEntity].
     */
    operator fun invoke(world: World, pos: BlockPos, state: BlockState, blockEntity: ModBeaconBlockEntity)
        = blockEntity.tick(world, pos, state)

    /**
     * Creates a composed [BeaconUpgrade] that first runs this and then [after].
     */
    infix fun andThen(after: BeaconUpgrade): BeaconUpgrade = beaconUpgrade {

        activate {
            activate(it)
            after.activate(it)
        }

        deactivate {
            deactivate(it)
            after.deactivate(it)
        }

        tick { world, blockPos, blockState ->
            tick(world, blockPos, blockState)
            after(world, blockPos, blockState, this)
        }
    }
}