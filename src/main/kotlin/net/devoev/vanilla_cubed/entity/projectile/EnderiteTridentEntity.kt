package net.devoev.vanilla_cubed.entity.projectile

import net.devoev.vanilla_cubed.entity.ModEntityTypes
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.projectile.TridentEntity
import net.minecraft.item.ItemStack
import net.minecraft.world.World

class EnderiteTridentEntity(entityType: EntityType<out TridentEntity>, world: World) : TridentEntity(entityType, world) {

    constructor(world: World, owner: LivingEntity, stack: ItemStack) : this(ModEntityTypes.ENDERITE_TRIDENT, world)
}