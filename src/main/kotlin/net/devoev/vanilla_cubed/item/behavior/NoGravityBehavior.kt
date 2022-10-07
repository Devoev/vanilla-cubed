package net.devoev.vanilla_cubed.item.behavior

import net.devoev.vanilla_cubed.util.math.toVec3d
import net.minecraft.entity.EntityType
import net.minecraft.item.Item
import net.minecraft.predicate.entity.EntityPredicates
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.Box
import net.minecraft.util.math.Vec3d
import net.minecraft.world.ServerWorldAccess
import net.minecraft.world.World

/**
 * Removes gravity from the loot of killed targets.
 */
val NoGravityPostHitBehavior = PostHitBehavior<Item> { _, params ->
    if (params.target != null && params.target.isDead && !params.target.world.isClient)
        removeGravity(params.target.pos, params.target.world)
}

/**
 * Removes gravity from the loot of a mined block.
 */
val NoGravityPostMineBehavior = PostMineBehavior<Item> { _, params ->
    if (params.state != null && params.pos != null && params.world != null && !params.world.isClient)
        removeGravity(params.pos.toVec3d(), params.world)
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
            .forEach { it.setNoGravity(true) }
    }
}