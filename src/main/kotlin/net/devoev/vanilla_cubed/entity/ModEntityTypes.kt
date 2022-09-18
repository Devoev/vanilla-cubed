package net.devoev.vanilla_cubed.entity

import net.devoev.vanilla_cubed.entity.projectile.EnderiteTridentEntity
import net.devoev.vanilla_cubed.item.trident.EnderiteTrident
import net.devoev.vanilla_cubed.util.RegistryManager
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricEntityTypeBuilder
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.EntityType.EntityFactory
import net.minecraft.entity.SpawnGroup
import net.minecraft.util.registry.Registry

object ModEntityTypes : RegistryManager<EntityType<out Entity>>(Registry.ENTITY_TYPE) {

    val ENDERITE_TRIDENT = create("enderite_trident", ::EnderiteTridentEntity)

    fun <T : Entity> create(name: String, factory: EntityFactory<T>)
        = create(name, entityTypeFrom(factory))

    private fun <T : Entity> entityTypeFrom(factory: EntityFactory<T>): EntityType<T>
        = FabricEntityTypeBuilder.create(SpawnGroup.MISC, factory).build()
}