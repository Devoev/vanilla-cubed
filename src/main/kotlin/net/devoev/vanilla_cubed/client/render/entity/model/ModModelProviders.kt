package net.devoev.vanilla_cubed.client.render.entity.model

import net.devoev.vanilla_cubed.item.ModItems
import net.devoev.vanilla_cubed.item.model
import net.devoev.vanilla_cubed.util.SetInitializer
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.client.model.ExtraModelProvider
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry

@Environment(EnvType.CLIENT)
object ModModelProviders : SetInitializer<ExtraModelProvider>() {

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