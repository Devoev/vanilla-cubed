package net.devoev.vanilla_cubed.block.entity.beacon_upgrade

import net.devoev.vanilla_cubed.block.entity.ModBeaconBlockEntity
import net.devoev.vanilla_cubed.block.entity.ModBlockEntityTypes
import net.devoev.vanilla_cubed.util.math.asIterable
import net.minecraft.block.BlockState
import net.minecraft.entity.Entity
import net.minecraft.entity.mob.HostileEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Box
import net.minecraft.world.World
import kotlin.jvm.optionals.getOrNull

/**
 * Disables all monster spawning in the beacons range.
 */
class DisableMonsterSpawningUpgrade : BeaconUpgrade {

    private var range: Box? = null

    override fun ModBeaconBlockEntity.accept(world: World, pos: BlockPos, state: BlockState) {
        // TODO: Overwrite ServerWorld.spawnEntity fun or ServerChunkManager.tickChunks -> SpawnHelper.spawn fun
        //  Disable ALL hostile mob spawning or just natural ones?
        range = boxRange
    }

    /**
     * Whether the given [entity] is in the range of this upgrade.
     */
    fun inRange(entity: Entity): Boolean {
        return range?.run {
            entity.pos in this
        } ?: false
    }

    companion object {

        /**
         * Whether the spawn of this entity should be canceled by a beacon.
         */
        fun Entity.checkSpawn(): Boolean {
            if (this !is HostileEntity) return false
            return Box(blockPos)
                .expand(50.0)
                .stretch(0.0, entityWorld.height.toDouble(), 0.0)
                .asIterable()
                .mapNotNull { entityWorld.getBlockEntity(it, ModBlockEntityTypes.MOD_BEACON).getOrNull() }
                .any { it.disableMonsterSpawningUpgrade?.inRange(this) == true }
        }

        private val ModBeaconBlockEntity.disableMonsterSpawningUpgrade: DisableMonsterSpawningUpgrade?
            get() = upgrade as? DisableMonsterSpawningUpgrade
    }
}