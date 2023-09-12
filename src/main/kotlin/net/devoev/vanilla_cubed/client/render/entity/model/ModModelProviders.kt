package net.devoev.vanilla_cubed.client.render.entity.model

import net.devoev.vanilla_cubed.item.ModItems
import net.devoev.vanilla_cubed.item.model
import net.devoev.vanilla_cubed.util.SetInitializer
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin

@Environment(EnvType.CLIENT)
object ModModelProviders : SetInitializer<ModelLoadingPlugin>() {

    init {
        create {
            addModels(
                ModItems.NETHERITE_TRIDENT.model("_in_inventory", "inventory"),
                ModItems.ENDERITE_TRIDENT.model("_in_inventory", "inventory"),
                ModItems.AMETHYST_TRIDENT.model("_in_inventory", "inventory")
            )
        }
    }

    /**
     * Creates a new [ModelLoadingPlugin] and performs the [contextAction] on it.
     */
    fun create(contextAction: ModelLoadingPlugin.Context.() -> Unit) = create(ModelLoadingPlugin(contextAction))

    override fun init() = forEach(ModelLoadingPlugin::register)
}