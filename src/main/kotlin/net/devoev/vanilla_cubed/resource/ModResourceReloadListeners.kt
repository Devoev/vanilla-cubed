package net.devoev.vanilla_cubed.resource

import net.devoev.vanilla_cubed.client.render.item.TridentItemRenderer
import net.devoev.vanilla_cubed.entity.ModEntityTypes
import net.devoev.vanilla_cubed.entity.texture
import net.devoev.vanilla_cubed.item.ModItems
import net.devoev.vanilla_cubed.util.SetInitializer
import net.devoev.vanilla_cubed.item.id
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener
import net.fabricmc.fabric.api.resource.ResourceManagerHelper
import net.minecraft.resource.ResourceType

object ModResourceReloadListeners : SetInitializer<IdentifiableResourceReloadListener>() {

    val ENDERITE_TRIDENT = create(TridentItemRenderer(
        ModItems.ENDERITE_TRIDENT.id, ModEntityTypes.ENDERITE_TRIDENT.texture
    ))
    val NETHERITE_TRIDENT = create(TridentItemRenderer(
        ModItems.NETHERITE_TRIDENT.id, ModEntityTypes.NETHERITE_TRIDENT.texture
    ))

    override fun init() = forEach { ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(it) }
}