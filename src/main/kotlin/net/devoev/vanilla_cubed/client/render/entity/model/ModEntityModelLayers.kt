package net.devoev.vanilla_cubed.client.render.entity.model

import net.devoev.vanilla_cubed.VanillaCubed
import net.devoev.vanilla_cubed.item.ModItems
import net.devoev.vanilla_cubed.item.id
import net.devoev.vanilla_cubed.util.MapInitializer
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry.TexturedModelDataProvider
import net.minecraft.client.render.entity.model.ElytraEntityModel
import net.minecraft.client.render.entity.model.EntityModelLayer
import net.minecraft.client.render.entity.model.TridentEntityModel
import net.minecraft.util.Identifier

/**
 * An object for initializing a custom [EntityModelLayer].
 */
@Environment(EnvType.CLIENT)
object ModEntityModelLayers : MapInitializer<EntityModelLayer, TexturedModelDataProvider>() {

    init {
        this[ModItems.WINGED_DRAGON_SCALE_CHESTPLATE.id] = { ElytraEntityModel.getTexturedModelData() }
        this[ModItems.ENDERITE_TRIDENT.id] = { TridentEntityModel.getTexturedModelData() }
        this[ModItems.NETHERITE_TRIDENT.id] = { TridentEntityModel.getTexturedModelData() }
        this[ModItems.AMETHYST_TRIDENT.id] = { TridentEntityModel.getTexturedModelData() }
    }

    operator fun set(name: String, provider: TexturedModelDataProvider) = put(EntityModelLayer(VanillaCubed.id(name), name), provider)

    operator fun set(id: Identifier, provider: TexturedModelDataProvider) = put(EntityModelLayer(id, id.path), provider)

    /**
     * Registers all model layers.
     */
    override fun init() = forEach { EntityModelLayerRegistry.registerModelLayer(it.key, it.value) }
}