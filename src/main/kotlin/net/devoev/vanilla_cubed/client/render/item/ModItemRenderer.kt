package net.devoev.vanilla_cubed.client.render.item

import net.devoev.vanilla_cubed.VanillaCubed
import net.devoev.vanilla_cubed.item.ModItems
import net.devoev.vanilla_cubed.resource.ModResourceReloadListeners
import net.devoev.vanilla_cubed.util.MapInitializer
import net.devoev.vanilla_cubed.util.id
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry.DynamicItemRenderer
import net.minecraft.item.ItemConvertible
import net.minecraft.util.Identifier

object ModItemRenderer : MapInitializer<ItemConvertible, DynamicItemRenderer>() {

    init {
        this[ModItems.ENDERITE_TRIDENT] = ModResourceReloadListeners.ENDERITE_TRIDENT
    }

    override fun init() = forEach { BuiltinItemRendererRegistry.INSTANCE.register(it.key, it.value) }
}