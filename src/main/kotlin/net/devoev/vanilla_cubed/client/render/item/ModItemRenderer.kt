package net.devoev.vanilla_cubed.client.render.item

import net.devoev.vanilla_cubed.VanillaCubed
import net.devoev.vanilla_cubed.item.ModItems
import net.devoev.vanilla_cubed.util.MapInitializer
import net.devoev.vanilla_cubed.util.id
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry.DynamicItemRenderer
import net.minecraft.item.ItemConvertible
import net.minecraft.util.Identifier

object ModItemRenderer : MapInitializer<ItemConvertible, DynamicItemRenderer>() {

    init {
        //val id: Identifier = ModItems.ENDERITE_TRIDENT.id
        //val texture = Identifier(id.namespace, "textures/entity/" + id.path + ".png")
        val id = Identifier(VanillaCubed.MOD_ID, "enderite_trident")
        val texture = Identifier(VanillaCubed.MOD_ID, "textures/entity/enderite_trident.png")
        create(ModItems.ENDERITE_TRIDENT, TridentItemRenderer(id, texture))
    }

    override fun init() = forEach { BuiltinItemRendererRegistry.INSTANCE.register(it.key, it.value) }
}