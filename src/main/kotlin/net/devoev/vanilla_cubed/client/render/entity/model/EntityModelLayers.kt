package net.devoev.vanilla_cubed.client.render.entity.model

import net.devoev.vanilla_cubed.VanillaCubed
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry.TexturedModelDataProvider
import net.minecraft.client.render.entity.model.ElytraEntityModel
import net.minecraft.client.render.entity.model.EntityModelLayer

object EntityModelLayers : MutableMap<EntityModelLayer, TexturedModelDataProvider> by mutableMapOf() {

    init {
        this["dragon_scale_chestplate_winged"] = { ElytraEntityModel.getTexturedModelData() }
    }

    operator fun set(name: String, provider: TexturedModelDataProvider) = put(EntityModelLayer(VanillaCubed.id(name), name), provider)

    /**
     * Registers all model layers.
     */
    fun init() {
        EntityModelLayers.forEach {EntityModelLayerRegistry.registerModelLayer(it.key, it.value)}
    }
}