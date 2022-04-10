package net.devoev.vanilla_cubed.client.render.entity.feature

import net.devoev.vanilla_cubed.util.MapInitializer
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback
import net.minecraft.client.render.entity.LivingEntityRenderer
import net.minecraft.client.render.entity.feature.FeatureRenderer
import net.minecraft.client.render.entity.model.EntityModel
import net.minecraft.client.render.entity.model.EntityModelLoader
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity


/**
 * An object for initializing a custom [LivingEntityRenderer].
 */
object LivingEntityFeatureRenderer : MapInitializer<EntityType<*>, FeatureRendererGenerator>() {

    init {
        this[EntityType.PLAYER] = { context, loader -> WingedDragonChestplateFeatureRenderer(context, loader) }
    }

    override fun init() {
        LivingEntityFeatureRendererRegistrationCallback.EVENT.register { entityType, livingEntityRenderer, registrationHelper, context ->
            if (contains(entityType))
                registrationHelper.register(this[entityType]!!(livingEntityRenderer, context.modelLoader))
        }
    }
}

typealias FeatureRendererGenerator = (LivingEntityRenderer<*,*>, EntityModelLoader) -> FeatureRenderer<out LivingEntity, out EntityModel<out LivingEntity>>