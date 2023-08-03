package net.devoev.vanilla_cubed.block.entity.beacon_upgrade

import net.devoev.vanilla_cubed.block.entity.ModBeaconBlockEntity
import net.devoev.vanilla_cubed.block.entity.ModBlockEntityTypes
import net.devoev.vanilla_cubed.util.math.toList
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
     * Whether the spawn of the [entity] should be canceled by this upgrade.
     */
    fun checkSpawn(entity: Entity): Boolean {
        return range?.run {
            entity is HostileEntity && entity.pos in this
        } ?: false
    }

    companion object {

        fun Entity.check() {
            if (this !is HostileEntity) return
            val range = Box(blockPos)
                .expand(50.0)
                .stretch(0.0, entityWorld.height.toDouble(), 0.0)
            val beacons = mutableListOf<ModBeaconBlockEntity>()
            range.run {
                for (x in minX.toInt()..maxX.toInt()) {
                    for (y in minY.toInt()..maxY.toInt()) {
                        for (z in minZ.toInt()..maxZ.toInt()) {
                            val beaconEntity = entityWorld.getBlockEntity(BlockPos(x, y, z), ModBlockEntityTypes.MOD_BEACON)
                            beacons += beaconEntity.getOrNull() ?: continue
                        }
                    }
                }
            }
            println(beacons)
            println(beacons.map { it.disableMonsterSpawningUpgrade?.checkSpawn(this) })

            val box = Box(blockPos).expand(3.0)
            println(box.toList())
            println(box.toList().toSet().size)
        }

        val ModBeaconBlockEntity.disableMonsterSpawningUpgrade: DisableMonsterSpawningUpgrade?
            get() = upgrade as? DisableMonsterSpawningUpgrade
    }
}