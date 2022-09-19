package net.devoev.vanilla_cubed.client.render.entity.feature

import net.devoev.vanilla_cubed.util.ListInitializer
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback
import net.minecraft.client.render.entity.LivingEntityRenderer
import net.minecraft.client.render.entity.feature.FeatureRenderer
import net.minecraft.client.render.entity.feature.FeatureRendererContext
import net.minecraft.client.render.entity.model.EntityModel
import net.minecraft.client.render.entity.model.EntityModelLoader
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity


/**
 * An object for initializing a custom [LivingEntityRenderer].
 */
object ModLivingEntityFeatureRenderer : ListInitializer<FeatureRendererProvider<*>>() {

    init {
        create(EntityType.PLAYER to { context, loader -> WingedDragonChestplateFeatureRenderer(context, loader) })
    }

    override fun init() {
        LivingEntityFeatureRendererRegistrationCallback.EVENT.register { entityType, livingEntityRenderer, registrationHelper, context ->
            filter { it.first == entityType }.forEach {
                registrationHelper.register(
                    it.second(livingEntityRenderer, context.modelLoader) as FeatureRenderer<LivingEntity,*>
                )
            }
        }
    }
}

//typealias FeatureRendererContextKt<T> = FeatureRendererContext<out T, EntityModelKt<out T>>
//typealias EntityModelKt<T> = EntityModel<out T>
//typealias EntityTypeKt<T> = EntityType<out T>

typealias FeatureRendererProvider<T> = Pair<EntityType<T>, FeatureRendererGenerator<T>>

typealias FeatureRendererGenerator<T> = (LivingEntityRenderer<*,*>, EntityModelLoader)
    -> FeatureRenderer<T, out EntityModel<T>>