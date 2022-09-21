package net.devoev.vanilla_cubed.entity.projectile

import net.devoev.vanilla_cubed.entity.ModEntityTypes
import net.devoev.vanilla_cubed.item.magnetic
import net.devoev.vanilla_cubed.util.isNetherite
import net.devoev.vanilla_cubed.util.math.Vec3d
import net.devoev.vanilla_cubed.util.math.minus
import net.devoev.vanilla_cubed.util.math.plus
import net.minecraft.entity.EntityType
import net.minecraft.entity.ItemEntity
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemStack
import net.minecraft.predicate.entity.EntityPredicates
import net.minecraft.util.hit.EntityHitResult
import net.minecraft.util.math.Box
import net.minecraft.world.World

class NetheriteTridentEntity(world: World, owner: LivingEntity, stack: ItemStack)
    : ModTridentEntity(world, owner, stack, ModEntityTypes.NETHERITE_TRIDENT) {

    private val items: MutableSet<ItemEntity> = mutableSetOf()

    override fun tick() {
        super.tick()
        if (itemStack.item.isNetherite() && !itemStack.magnetic || inGroundTime > 40) return
        val range = 2.5

        items.addAll(entityWorld.getEntitiesByType(
            EntityType.ITEM,
            Box(pos - Vec3d(range,range,range), pos + Vec3d(range,range,range)),
            EntityPredicates.VALID_ENTITY
        ))

        items.forEach { it.setPosition(pos + Vec3d(0, 0.75, 0)) }
    }
}