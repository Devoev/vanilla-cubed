package net.devoev.vanilla_cubed.item.behavior

import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.predicate.entity.EntityPredicates
import net.minecraft.util.math.Box
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World

/**
 * Attracts all items in [range] to the player holding the item.
 * The speed of attraction is determined by [delta] blocks each tick.
 */
class MagneticBehavior(private val range: Double, private val delta: Double) : InventoryTickBehavior<Item> {

    override fun inventoryTick(item: Item, stack: ItemStack?, world: World?, entity: Entity?, slot: Int, selected: Boolean) {
        if (!selected) return
        entity?.attractItems(range, delta)
    }

    /**
     * Attracts items to this player.
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
            item.move(null, playerVector.subtract(itemVector).normalize().multiply(delta))
        }
    }
}