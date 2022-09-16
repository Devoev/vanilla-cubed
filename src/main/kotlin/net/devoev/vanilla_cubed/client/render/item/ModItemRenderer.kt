package net.devoev.vanilla_cubed.client.render.item

import net.devoev.vanilla_cubed.item.ModItems
import net.devoev.vanilla_cubed.util.MapInitializer
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry.DynamicItemRenderer
import net.minecraft.item.ItemConvertible
import net.minecraft.util.Identifier

object ModItemRenderer : MapInitializer<ItemConvertible, DynamicItemRenderer>() {

    init {
        val tridentId: Identifier = ModItems[ModItems.ENDERITE_TRIDENT]
        val texture = Identifier(tridentId.namespace, "textures/entity/" + tridentId.path + ".png")
        create(ModItems.ENDERITE_TRIDENT, TridentItemRenderer(tridentId, texture))
    }

    override fun init() = forEach { BuiltinItemRendererRegistry.INSTANCE.register(it.key, it.value) }
}