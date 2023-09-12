package net.devoev.vanilla_cubed.client.render.item

import net.devoev.vanilla_cubed.item.ModItems
import net.devoev.vanilla_cubed.resource.ModResourceReloadListeners
import net.devoev.vanilla_cubed.util.MapInitializer
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry.DynamicItemRenderer
import net.minecraft.item.ItemConvertible

@Environment(EnvType.CLIENT)
object ModItemRenderer : MapInitializer<ItemConvertible, DynamicItemRenderer>() {

    init {
        this[ModItems.ENDERITE_TRIDENT] = ModResourceReloadListeners.ENDERITE_TRIDENT
        this[ModItems.NETHERITE_TRIDENT] = ModResourceReloadListeners.NETHERITE_TRIDENT
        this[ModItems.AMETHYST_TRIDENT] = ModResourceReloadListeners.AMETHYST_TRIDENT
    }

    override fun init() = forEach { BuiltinItemRendererRegistry.INSTANCE.register(it.key, it.value) }
}