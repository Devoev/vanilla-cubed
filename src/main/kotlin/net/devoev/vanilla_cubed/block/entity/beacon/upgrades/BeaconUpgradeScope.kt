package net.devoev.vanilla_cubed.block.entity.beacon.upgrades

import net.devoev.vanilla_cubed.block.entity.ModBeaconBlockEntity
import net.minecraft.block.BlockState
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

/**
 * Scope for building a [BeaconUpgrade].
 */
class BeaconUpgradeScope {
    private var activateDelegate: (ModBeaconBlockEntity) -> Unit = { }
    private var deactivateDelegate: (ModBeaconBlockEntity) -> Unit = { }
    private var tickDelegate: ModBeaconBlockEntity.(World, BlockPos, BlockState) -> Unit = { _, _, _ -> }

    /**
     * Builds the upgrade.
     */
    fun buildUpgrade(): BeaconUpgrade = object : BeaconUpgrade {
        override fun activate(blockEntity: ModBeaconBlockEntity) = activateDelegate(blockEntity)

        override fun deactivate(blockEntity: ModBeaconBlockEntity) = deactivateDelegate(blockEntity)

        override fun ModBeaconBlockEntity.tick(world: World, pos: BlockPos, state: BlockState) = tickDelegate(world, pos, state)
    }

    /**
     * Sets [BeaconUpgrade.activate].
     */
    fun activate(block: (ModBeaconBlockEntity) -> Unit) {
        activateDelegate = block
    }

    /**
     * Sets [BeaconUpgrade.deactivate].
     */
    fun deactivate(block: (ModBeaconBlockEntity) -> Unit) {
        deactivateDelegate = block
    }

    /**
     * Sets [BeaconUpgrade.tick].
     */
    fun tick(block: ModBeaconBlockEntity.(World, BlockPos, BlockState) -> Unit) {
        tickDelegate = block
    }
}

/**
 * Builds a [BeaconUpgrade] by running the [block].
 */
fun beaconUpgrade(block: BeaconUpgradeScope.() -> Unit): BeaconUpgrade {
    with(BeaconUpgradeScope()) {
        block()
        return buildUpgrade()
    }
}

/**
 * Builds a [BeaconUpgrade] with the provided [tick] function.
 * [BeaconUpgrade.activate] and [BeaconUpgrade.deactivate] do nothing.
 */
fun tickUpgrade(tick: ModBeaconBlockEntity.(world: World, pos: BlockPos, state: BlockState) -> Unit): BeaconUpgrade {
    return beaconUpgrade {
        tick(tick)
    }
}

/**
 * Builds an empty [BeaconUpgrade].
 */
fun emptyUpgrade() = beaconUpgrade {  }