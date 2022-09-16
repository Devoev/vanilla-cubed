package net.devoev.vanilla_cubed.resource

import net.devoev.vanilla_cubed.client.render.item.TridentItemRenderer
import net.devoev.vanilla_cubed.item.ModItems
import net.devoev.vanilla_cubed.util.ListInitializer
import net.devoev.vanilla_cubed.util.MapInitializer
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener
import net.fabricmc.fabric.api.resource.ResourceManagerHelper
import net.fabricmc.fabric.impl.resource.loader.ResourceManagerHelperImpl
import net.minecraft.resource.ResourceType
import net.minecraft.util.Identifier

object ModResourceReloadListeners : ListInitializer<IdentifiableResourceReloadListener>() {

    init {
        val tridentId: Identifier = ModItems[ModItems.ENDERITE_TRIDENT]
        val texture = Identifier(tridentId.namespace, "textures/entity/" + tridentId.path + ".png")
        create(TridentItemRenderer(tridentId, texture))
    }

    override fun init() = forEach { ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(it) }
}