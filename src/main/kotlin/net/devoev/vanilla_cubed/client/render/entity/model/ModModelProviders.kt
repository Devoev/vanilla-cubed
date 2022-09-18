package net.devoev.vanilla_cubed.client.render.entity.model

import net.devoev.vanilla_cubed.item.ModItems
import net.devoev.vanilla_cubed.util.ListInitializer
import net.devoev.vanilla_cubed.util.id
import net.fabricmc.fabric.api.client.model.ExtraModelProvider
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry
import net.minecraft.client.util.ModelIdentifier
import net.minecraft.data.client.ModelProvider
import net.minecraft.util.Identifier

object ModModelProviders : ListInitializer<ExtraModelProvider>() {

    init {
        val tridentId: Identifier = ModItems.ENDERITE_TRIDENT.id
        create(ExtraModelProvider { _, out -> out.accept(ModelIdentifier(tridentId.toString() + "_in_inventory", "inventory")) })
    }

    override fun init() = forEach { ModelLoadingRegistry.INSTANCE.registerModelProvider(it) }
}