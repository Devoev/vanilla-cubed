package net.devoev.vanilla_cubed.client.render.entity.model

import net.devoev.vanilla_cubed.VanillaCubed
import net.devoev.vanilla_cubed.util.MapInitializer
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry.TexturedModelDataProvider
import net.minecraft.client.render.entity.model.ElytraEntityModel
import net.minecraft.client.render.entity.model.EntityModelLayer

/**
 * An object for initializing a custom [EntityModelLayer].
 */
object EntityModelLayers : MapInitializer<EntityModelLayer, TexturedModelDataProvider>() {

    init {
        this["dragon_scale_chestplate_winged"] = { ElytraEntityModel.getTexturedModelData() }
    }

    operator fun set(name: String, provider: TexturedModelDataProvider) = put(EntityModelLayer(VanillaCubed.id(name), name), provider)

    /**
     * Registers all model layers.
     */
    override fun init() = forEach { EntityModelLayerRegistry.registerModelLayer(it.key, it.value) }
}