package net.devoev.vanilla_cubed.block.entity.beacon_upgrade

import net.minecraft.entity.Entity
import net.minecraft.entity.mob.HostileEntity

/**
 * Disables all monster spawning in the beacons range.
 */
object DisableMonsterSpawningUpgrade : ToggledUpgrade() {

    /**
     * Whether the spawn of this entity should be canceled by a beacon.
     */
    operator fun invoke(entity: Entity): Boolean = entity is HostileEntity && inRange(entity.pos)
}