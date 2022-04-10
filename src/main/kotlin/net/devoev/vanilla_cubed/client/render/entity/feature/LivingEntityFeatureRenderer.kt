package net.devoev.vanilla_cubed.client.render.entity.feature

import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback
import net.minecraft.client.render.entity.LivingEntityRenderer
import net.minecraft.client.render.entity.feature.FeatureRenderer
import net.minecraft.client.render.entity.model.EntityModel
import net.minecraft.client.render.entity.model.EntityModelLoader
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.util.Identifier


object LivingEntityFeatureRenderer : MutableMap<Identifier,
            (LivingEntityRenderer<*,*>, EntityModelLoader) -> FeatureRenderer<out LivingEntity, out EntityModel<out LivingEntity>>> by mutableMapOf() {

    init {
        this[EntityType.PLAYER] = { context, loader -> WingedDragonChestplateFeatureRenderer(context, loader) }
    }

    /**
     * Adds the given [renderer] to this. Uses the "minecraft" namespace for the id.
     */
    operator fun set(type: EntityType<*>, renderer: (LivingEntityRenderer<*,*>, EntityModelLoader) -> FeatureRenderer<out LivingEntity, out EntityModel<out LivingEntity>>)
        = set(EntityType.getId(type), renderer)

    fun init() {
        LivingEntityFeatureRendererRegistrationCallback.EVENT.register { entityType, livingEntityRenderer, registrationHelper, context ->
            val id = EntityType.getId(entityType)
            if (contains(id))
                registrationHelper.register(this[id]!!(livingEntityRenderer, context.modelLoader))
        }
    }
}