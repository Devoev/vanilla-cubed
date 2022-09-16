package net.devoev.vanilla_cubed.client.render.entity.model

import net.devoev.vanilla_cubed.item.ModItems
import net.devoev.vanilla_cubed.util.ListInitializer
import net.fabricmc.fabric.api.client.model.ExtraModelProvider
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry
import net.minecraft.client.util.ModelIdentifier
import net.minecraft.data.client.ModelProvider
import net.minecraft.util.Identifier

object ModModelProviders : ListInitializer<ExtraModelProvider>() {

    init {
        val tridentId: Identifier = ModItems[ModItems.ENDERITE_TRIDENT]
        create(ExtraModelProvider { manager, out -> out.accept(ModelIdentifier("enderite_trident_in_inventory", "inventory")) })
    }

    override fun init() = forEach { ModelLoadingRegistry.INSTANCE.registerModelProvider(it) }
}