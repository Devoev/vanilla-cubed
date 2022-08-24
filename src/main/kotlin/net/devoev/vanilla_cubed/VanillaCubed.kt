package net.devoev.vanilla_cubed

import net.devoev.vanilla_cubed.block.ModBlocks
import net.devoev.vanilla_cubed.client.render.entity.feature.LivingEntityFeatureRenderer
import net.devoev.vanilla_cubed.client.render.entity.model.EntityModelLayers
import net.devoev.vanilla_cubed.item.ModItems
import net.devoev.vanilla_cubed.item.ModModelPredicateProvider
import net.devoev.vanilla_cubed.loot.ModLootTables
import net.devoev.vanilla_cubed.world.feature.ModConfiguredFeatures
import net.devoev.vanilla_cubed.world.feature.ModPlacedFeatures
import net.devoev.vanilla_cubed.world.gen.ModWorldGeneration
import net.fabricmc.api.ModInitializer
import net.minecraft.util.Identifier

object VanillaCubed : ModInitializer {

    const val MOD_ID = "vanilla_cubed"

    override fun onInitialize() {
        ModItems.init()
        ModBlocks.init()
        ModLootTables.init()
        ModConfiguredFeatures.init()
        ModPlacedFeatures.init()
        ModWorldGeneration.init()
        EntityModelLayers.init()
        LivingEntityFeatureRenderer.init()
        ModModelPredicateProvider.init()
    }

    /**
     * Returns an [Identifier] for the given [name].
     */
    fun id(name: String): Identifier = Identifier(MOD_ID, name)
}

