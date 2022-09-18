package net.devoev.vanilla_cubed.resource

import net.devoev.vanilla_cubed.VanillaCubed
import net.devoev.vanilla_cubed.client.render.item.TridentItemRenderer
import net.devoev.vanilla_cubed.item.ModItems
import net.devoev.vanilla_cubed.util.ListInitializer
import net.devoev.vanilla_cubed.util.MapInitializer
import net.devoev.vanilla_cubed.util.id
import net.devoev.vanilla_cubed.util.texture
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener
import net.fabricmc.fabric.api.resource.ResourceManagerHelper
import net.fabricmc.fabric.impl.resource.loader.ResourceManagerHelperImpl
import net.minecraft.resource.ResourceType
import net.minecraft.util.Identifier

object ModResourceReloadListeners : ListInitializer<IdentifiableResourceReloadListener>() {

    val ENDERITE_TRIDENT = create(TridentItemRenderer(ModItems.ENDERITE_TRIDENT.id, ModItems.ENDERITE_TRIDENT.id.texture))

    override fun init() = forEach { ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(it) }
}