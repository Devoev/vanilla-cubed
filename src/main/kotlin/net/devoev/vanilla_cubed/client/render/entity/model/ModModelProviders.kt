package net.devoev.vanilla_cubed.client.render.entity.model

import net.devoev.vanilla_cubed.item.ModItems
import net.devoev.vanilla_cubed.util.ListInitializer
import net.devoev.vanilla_cubed.util.id
import net.devoev.vanilla_cubed.util.model
import net.fabricmc.fabric.api.client.model.ExtraModelProvider
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry
import net.minecraft.client.util.ModelIdentifier
import net.minecraft.data.client.ModelProvider
import net.minecraft.util.Identifier

object ModModelProviders : ListInitializer<ExtraModelProvider>() {

    init {
        create(ExtraModelProvider { _, out -> out.accept(
            ModItems.ENDERITE_TRIDENT.model("_in_inventory", "inventory")
        ) })

        create(ExtraModelProvider { _, out -> out.accept(
            ModItems.NETHERITE_TRIDENT.model("_in_inventory", "inventory")
        ) })
    }

    override fun init() = forEach { ModelLoadingRegistry.INSTANCE.registerModelProvider(it) }
}