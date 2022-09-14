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
 * Only items that have a smaller age value than [ageLimit] get attracted. A value of 0 disables this limit.
 */
class MagneticBehavior(private val range: Double, private val speed: Double, private val ageLimit: Int) : InventoryTickBehavior<Item> {

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
        ).filter { ageLimit == 0 || it.itemAge < ageLimit }

        for (item in items) {
            item.setPickupDelay(0)
            val itemVector = Vec3d(item.x, item.y, item.z)
            val playerVector = Vec3d(x, y + 0.75, z)
            val vec = playerVector.subtract(itemVector)
            item.move(null, vec.multiply(delta / vec.lengthSquared()))
        }
    }
}