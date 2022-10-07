package net.devoev.vanilla_cubed.item.behavior

import net.devoev.vanilla_cubed.util.math.times
import net.minecraft.entity.EntityType
import net.minecraft.item.Item
import net.minecraft.predicate.entity.EntityPredicates
import net.minecraft.util.math.Box
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World

/**
 * Removes gravity from the loot of killed targets.
 */
val NoGravityBehavior = PostHitBehavior<Item> { _, params ->
    if (params.target != null && params.target.isDead && !params.target.world.isClient)
        removeGravity(params.target.pos, params.target.world)
}

/**
 * Removes the gravity from all items that just spawned inside a small region around the given [pos].
 */
private fun removeGravity(pos: Vec3d, world: World) {
    pos.run {
        val delta = 0.1
        world.getEntitiesByType(
                EntityType.ITEM,
                Box(x - delta, y - delta, z - delta, x + delta, y + delta, z + delta),
                EntityPredicates.VALID_ENTITY)
            .filter { it.itemAge < 5 }
            .forEach {
                it.setNoGravity(true)
            }
    }
}