package net.devoev.vanilla_cubed.entity.projectile

import net.devoev.vanilla_cubed.entity.ModEntityTypes
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemStack
import net.minecraft.world.World

class NetheriteTridentEntity(world: World, owner: LivingEntity, stack: ItemStack)
    : ModTridentEntity(world, owner, stack, ModEntityTypes.NETHERITE_TRIDENT) {
}