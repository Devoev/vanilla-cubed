package net.devoev.vanilla_cubed.client.render.entity.feature

import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback
import net.minecraft.client.render.entity.PlayerEntityRenderer
import net.minecraft.client.render.entity.model.PlayerEntityModel


object LivingEntityFeatureRenderer {

    fun init() {
        LivingEntityFeatureRendererRegistrationCallback.EVENT.register { entityType, livingEntityRenderer, registrationHelper, context ->
            if (livingEntityRenderer.model is PlayerEntityModel<*>) {
                registrationHelper.register(WingedDragonChestplateFeatureRenderer(PlayerEntityRenderer(context, false), context.modelLoader))
            }
        }
    }
}