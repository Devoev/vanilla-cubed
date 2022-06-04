package net.devoev.vanilla_cubed.item

import net.devoev.vanilla_cubed.util.isMadeOf
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.item.ItemStack
import net.minecraft.item.ToolMaterial
import net.minecraft.item.ToolMaterials
import net.minecraft.predicate.entity.EntityPredicates
import net.minecraft.util.math.Box
import net.minecraft.util.math.Vec3d
import java.util.stream.StreamSupport


/**
 * An item that attracts items when held.
 */
interface Magnetic {

    /**
     * The range the magnetic effect works in.
     */
    val range: Double

    /**
     * The speed with which items get pulled to the player.
     */
    val speed: Double

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
            item.move(null, playerVector.subtract(itemVector).normalize().multiply(speed))
        }
    }

    /**
     * Applies the magnetic effect, if item is [selected].
     */
    fun inventoryTick(entity: Entity, selected: Boolean) {
        if (selected) attractItems(entity)
    }
}