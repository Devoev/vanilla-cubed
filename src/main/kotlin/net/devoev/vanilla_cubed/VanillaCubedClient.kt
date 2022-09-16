package net.devoev.vanilla_cubed

import net.devoev.vanilla_cubed.client.render.entity.ModEntityRenderer
import net.devoev.vanilla_cubed.client.render.entity.model.ModEntityModelLayers
import net.devoev.vanilla_cubed.client.render.entity.model.ModModelProviders
import net.devoev.vanilla_cubed.client.render.item.ModItemRenderer
import net.devoev.vanilla_cubed.resource.ModResourceReloadListeners
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment

@Environment(EnvType.CLIENT)
object VanillaCubedClient : ClientModInitializer {

    override fun onInitializeClient() {
        ModEntityModelLayers.init()
        ModResourceReloadListeners.init()
        ModItemRenderer.init()
        ModEntityRenderer.init()
        ModModelProviders.init()
    }
}