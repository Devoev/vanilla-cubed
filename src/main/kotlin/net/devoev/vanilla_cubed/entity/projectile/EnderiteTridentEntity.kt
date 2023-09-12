package net.devoev.vanilla_cubed.entity.projectile

import net.devoev.vanilla_cubed.entity.ModEntityTypes
import net.devoev.vanilla_cubed.text.translatableTextOf
import net.devoev.vanilla_cubed.util.math.Vec3d
import net.devoev.vanilla_cubed.util.math.minus
import net.devoev.vanilla_cubed.util.math.plus
import net.devoev.vanilla_cubed.util.math.times
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.passive.TameableEntity
import net.minecraft.item.ItemStack
import net.minecraft.text.Text
import net.minecraft.util.Formatting
import net.minecraft.util.math.Box
import net.minecraft.world.World

/**
 * A trident made out of enderite. A thrown enderite trident has auto aim.
 */
class EnderiteTridentEntity(world: World, owner: LivingEntity, stack: ItemStack)
    : ModTridentEntity(world, owner, stack, ModEntityTypes.ENDERITE_TRIDENT) {

    override fun tick() {
        super.tick()
        if (isOnGround || targetHit) return

        val range = 10
        val interpolationFactor = 0.3
        val min = 0.7

        val entities = world.getEntitiesByClass(
            LivingEntity::class.java,
            Box(pos - Vec3d(range,range,range), pos + Vec3d(range,range,range))
        ) { it != this.owner && !(it is TameableEntity && it.isTamed) }

        val target = entities.maxByOrNull { (it.pos - pos).normalize() * velocity.normalize() } ?: return
        val targetVec = (target.pos - pos).normalize()
        if (targetVec * velocity.normalize() < min) return

        val interpolationVec = velocity.normalize() * (1 - interpolationFactor) + targetVec * interpolationFactor
        velocity = interpolationVec * velocity.length()
    }
}

val AIM_ASSIST_TEXT: Text = translatableTextOf("modifier", "aim_assist").formatted(Formatting.BLUE)