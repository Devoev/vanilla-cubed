package net.devoev.vanilla_cubed.block.entity.beacon.upgrades

import net.minecraft.entity.Entity
import net.minecraft.entity.mob.HostileEntity

/**
 * Disables all monster spawning in the beacons range.
 */
object DisableMonsterSpawningUpgrade : ToggledUpgrade() {

    /**
     * Disables the spawn of the hostile [entity] by calling [Entity.discard].
     */
    fun disableMonsterSpawn(entity: Entity) {
        if (entity is HostileEntity && inRange(entity.pos)) entity.discard()
    }
}