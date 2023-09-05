package net.devoev.vanilla_cubed.entity

import net.devoev.vanilla_cubed.entity.projectile.ModTridentEntity
import net.devoev.vanilla_cubed.util.RegistryManager
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricEntityTypeBuilder
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.EntityType.EntityFactory
import net.minecraft.entity.SpawnGroup
import net.minecraft.registry.Registries

object ModEntityTypes : RegistryManager<EntityType<out Entity>>(Registries.ENTITY_TYPE) {

    val ENDERITE_TRIDENT = create("enderite_trident", ::ModTridentEntity)
    val NETHERITE_TRIDENT = create("netherite_trident", ::ModTridentEntity) { it.fireImmune() }

    fun <T : Entity> create(name: String, factory: EntityFactory<T>,
                            block: (FabricEntityTypeBuilder<T>) -> FabricEntityTypeBuilder<T> = {builder -> builder})
        = create(name, entityTypeFrom(factory, block))

    /**
     * Creates a new [EntityType] from the given [factory].
     * The created type can be modified using the [block] function.
     */
    private fun <T : Entity> entityTypeFrom(
        factory: EntityFactory<T>, block: (FabricEntityTypeBuilder<T>) -> FabricEntityTypeBuilder<T>): EntityType<T>
        = FabricEntityTypeBuilder.create(SpawnGroup.MISC, factory).let(block).build()
}