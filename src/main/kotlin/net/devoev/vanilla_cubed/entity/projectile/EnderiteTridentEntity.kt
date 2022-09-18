package net.devoev.vanilla_cubed.entity.projectile

import net.devoev.vanilla_cubed.item.ModItems
import net.devoev.vanilla_cubed.util.id
import net.devoev.vanilla_cubed.util.texture
import net.minecraft.entity.EntityType
import net.minecraft.entity.projectile.TridentEntity
import net.minecraft.world.World

class EnderiteTridentEntity(entityType: EntityType<out TridentEntity>, world: World)
    : ModTridentEntity(entityType, world, ModItems.ENDERITE_TRIDENT.id.texture) {

    override fun tick() {
        super.tick()
        print(pos)
    }
}