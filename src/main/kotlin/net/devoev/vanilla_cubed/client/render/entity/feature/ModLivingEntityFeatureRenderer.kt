package net.devoev.vanilla_cubed.client.render.entity.feature

import net.devoev.vanilla_cubed.util.MapInitializer
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
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
@Environment(EnvType.CLIENT)
object ModLivingEntityFeatureRenderer : MapInitializer<EntityType<*>, MutableSet<FeatureRendererProvider<*>>>() {

    init {
        create(EntityType.PLAYER, WingedDragonChestplateFeatureRenderer.featureRendererProvider)
        create(EntityType.ARMOR_STAND, WingedDragonChestplateFeatureRenderer.featureRendererProvider)
    }

    /**
     * Adds the given [provider] to the set of providers of [entityType].
     */
    private fun create(entityType: EntityType<*>, provider: FeatureRendererProvider<*>): FeatureRendererProvider<*> {
        val set = this[entityType] ?: mutableSetOf()
        set.add(provider)
        this[entityType] = set
        return provider
    }

    override fun init() {
        LivingEntityFeatureRendererRegistrationCallback.EVENT.register { entityType, livingEntityRenderer, registrationHelper, context ->
            this[entityType]?.forEach {
                registrationHelper.register(
                    it(livingEntityRenderer, context.modelLoader) as FeatureRenderer<LivingEntity,*>
                )
            }
        }
    }
}

typealias FeatureRendererProvider<T> = (LivingEntityRenderer<*,*>, EntityModelLoader) -> FeatureRenderer<T, out EntityModel<T>>