package net.devoev.vanilla_cubed.item

import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.item.ItemStack
import net.minecraft.predicate.entity.EntityPredicates
import net.minecraft.util.math.Box
import net.minecraft.util.math.Vec3d


/**
 * An item that attracts items when held.
 */
interface Magnetic {

    /**
     * The range the magnetic effect works in.
     */
    val range: Double

    /**
     * Attracts all items in range of the given [entity].
     */
    fun attractItems(entity: Entity) {
        val x = entity.x
        val y = entity.y
        val z = entity.z

        val items = entity.entityWorld.getEntitiesByType(
            EntityType.ITEM,
            Box(x - range, y - range, z - range, x + range, y + range, z + range),
            EntityPredicates.VALID_ENTITY
        )

        for (item in items) {
            item.setPickupDelay(0)
            val itemVector = Vec3d(item.x, item.y, item.z)
            val playerVector = Vec3d(x, y + 0.75, z)
            //TODO: Different formula -> normalize, speed
            item.move(null, playerVector.subtract(itemVector).multiply(0.5))
        }
    }
}