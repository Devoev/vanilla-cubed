package net.devoev.vanilla_cubed.item.behavior

import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.item.Item
import net.minecraft.predicate.entity.EntityPredicates
import net.minecraft.util.math.Box
import net.minecraft.util.math.Vec3d

/**
 * Attracts all items in [range] to the player holding the item.
 * The speed of attraction is determined by the [speed] parameter.
 * The speed scales according to the inverse square law.
 */
class MagneticBehavior(private val range: Double, private val speed: Double) : InventoryTickBehavior<Item> {

    override fun accept(item: Item, params: InventoryTickParams) {
        if (params.selected && !params.world!!.isClient) params.entity?.attractItems(range, speed)
    }

    /**
     * Attracts items to this entity.
     */
    private fun Entity.attractItems(range: Double, delta: Double) {
        val items = entityWorld.getEntitiesByType(
            EntityType.ITEM,
            Box(x - range, y - range, z - range, x + range, y + range, z + range),
            EntityPredicates.VALID_ENTITY
        )

        for (item in items) {
            item.setPickupDelay(0)
            val itemVector = Vec3d(item.x, item.y, item.z)
            val playerVector = Vec3d(x, y + 0.75, z)
            val vec = playerVector.subtract(itemVector)
            item.move(null, vec.multiply(delta / vec.lengthSquared()))
        }
    }
}