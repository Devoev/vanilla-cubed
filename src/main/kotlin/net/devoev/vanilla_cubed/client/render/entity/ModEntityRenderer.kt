package net.devoev.vanilla_cubed.client.render.entity

import net.devoev.vanilla_cubed.entity.ModEntityTypes
import net.devoev.vanilla_cubed.util.MapInitializer
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry
import net.minecraft.client.render.entity.EntityRenderer
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.EntityRendererFactory.Context
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType

/**
 * An object for initializing a custom [EntityRenderer].
 */
@Environment(EnvType.CLIENT)
object ModEntityRenderer : MapInitializer<EntityType<out Entity>, EntityRendererFactory<Entity>>() {

    init {
        this[ModEntityTypes.ENDERITE_TRIDENT] = ::ModTridentEntityRenderer
        this[ModEntityTypes.NETHERITE_TRIDENT] = ::ModTridentEntityRenderer
        this[ModEntityTypes.AMETHYST_TRIDENT] = ::ModTridentEntityRenderer
    }

    operator fun <T : Entity> set(type: EntityType<out T>, rendererProvider: (Context) -> EntityRenderer<T>)
        = put(type) { ctx -> rendererProvider(ctx) as EntityRenderer<Entity> }

    override fun init() = forEach { EntityRendererRegistry.register(it.key, it.value) }
}