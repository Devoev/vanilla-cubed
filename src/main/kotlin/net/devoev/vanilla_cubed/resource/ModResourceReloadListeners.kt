package net.devoev.vanilla_cubed.resource

import net.devoev.vanilla_cubed.client.render.item.TridentItemRenderer
import net.devoev.vanilla_cubed.entity.ModEntityTypes
import net.devoev.vanilla_cubed.entity.entityTexture
import net.devoev.vanilla_cubed.item.ModItems
import net.devoev.vanilla_cubed.util.ListInitializer
import net.devoev.vanilla_cubed.util.id
import net.devoev.vanilla_cubed.util.entityTexture
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener
import net.fabricmc.fabric.api.resource.ResourceManagerHelper
import net.minecraft.resource.ResourceType

object ModResourceReloadListeners : ListInitializer<IdentifiableResourceReloadListener>() {

    val ENDERITE_TRIDENT = create(TridentItemRenderer(
        ModItems.ENDERITE_TRIDENT.id, ModEntityTypes.ENDERITE_TRIDENT.entityTexture
    ))
    val NETHERITE_TRIDENT = create(TridentItemRenderer(
        ModItems.NETHERITE_TRIDENT.id, ModEntityTypes.NETHERITE_TRIDENT.entityTexture
    ))

    override fun init() = forEach { ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(it) }
}