package net.devoev.vanilla_cubed.entity.effect

import net.devoev.vanilla_cubed.entity.droppedByPlayer
import net.devoev.vanilla_cubed.util.math.minus
import net.devoev.vanilla_cubed.util.math.times
import net.minecraft.entity.EntityType
import net.minecraft.entity.ItemEntity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.MovementType
import net.minecraft.entity.effect.StatusEffectCategory
import net.minecraft.predicate.entity.EntityPredicates
import net.minecraft.util.math.Box
import net.minecraft.util.math.Vec3d
import java.util.function.Predicate
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Attracts all items in [range] to the player.
 * Only items that fulfill the [predicate] get attracted.
 */
class MagneticStatusEffect internal constructor(private val predicate: Predicate<ItemEntity> = Predicate { true })
    : ModStatusEffect(StatusEffectCategory.BENEFICIAL, 0x5a575a) {

    override fun applyUpdateEffect(entity: LivingEntity, amplifier: Int) {
        val range = range(amplifier)
        val speed = speed(amplifier)

        with(entity) {
            val items = world.getEntitiesByType(
                EntityType.ITEM,
                Box(x - range, y - range, z - range, x + range, y + range, z + range),
                EntityPredicates.VALID_ENTITY
            ).filter(predicate::test)

            val maxDistance = sqrt(2f) * range // Corner point of box
            for (item in items) {
                item.setPickupDelay(0)
                val vec = Vec3d(x, y + standingEyeHeight/2, z) - Vec3d(item.x, item.y, item.z)
                val velocity = (1 - vec.length() / maxDistance).pow(2) * speed
                item.addVelocity(vec.normalize() * velocity)
                item.move(MovementType.SELF, item.velocity)
            }
        }
    }

    override fun canApplyUpdateEffect(duration: Int, amplifier: Int): Boolean = true

    /**
     * Calculates the range in dependence of the [amplifier].
     */
    private fun range(amplifier: Int) = 3.5 + 2*amplifier

    /**
     * Calculates the speed in dependence of the [amplifier].
     */
    private fun speed(amplifier: Int) = 0.2 * (amplifier + 1)
}

/**
 * The default [MagneticStatusEffect] that doesn't attract items dropped by the player.
 */
val DefaultMagneticStatusEffect = MagneticStatusEffect { !it.droppedByPlayer }