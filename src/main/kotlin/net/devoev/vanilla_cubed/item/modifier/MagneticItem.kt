package net.devoev.vanilla_cubed.item.modifier

import net.devoev.vanilla_cubed.util.math.minus
import net.devoev.vanilla_cubed.util.math.times
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.ItemEntity
import net.minecraft.entity.MovementType
import net.minecraft.item.Item
import net.minecraft.predicate.entity.EntityPredicates
import net.minecraft.util.math.Box
import net.minecraft.util.math.Vec3d
import java.util.function.Predicate
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Attracts all items in [range] to the player holding the item.
 * The speed of attraction is determined by the [speed] parameter.
 * Only items that fulfill the [predicate] get attracted.
 */
class MagneticItem(private val range: Double,
                   private val speed: Double,
                   private val predicate: Predicate<ItemEntity> = Predicate { true })
    : InventoryTickModifier<Item> {

    override fun accept(item: Item, params: InventoryTickParams) {
        if (params.selected && !params.world!!.isClient) params.entity?.attractItems(range, speed)
    }

    /**
     * Attracts items to this entity.
     */
    private fun Entity.attractItems(range: Double, speed: Double) {
        val items = entityWorld.getEntitiesByType(
            EntityType.ITEM,
            Box(x - range, y - range, z - range, x + range, y + range, z + range),
            EntityPredicates.VALID_ENTITY
        ).filter(predicate::test)

        val maxDistance = sqrt(2f)*range // Corner point of box
        for (item in items) {
            item.setPickupDelay(0)
            val vec = Vec3d(x, y + standingEyeHeight/2, z) - Vec3d(item.x, item.y, item.z)
            val velocity = (1 - vec.length() / maxDistance).pow(2) * speed
            item.addVelocity(vec.normalize() * velocity)
            item.move(MovementType.SELF, item.velocity)
        }
    }
}