package net.devoev.vanilla_cubed

import net.devoev.vanilla_cubed.item.ModModelProvider
import net.devoev.vanilla_cubed.recipe.ModRecipeProvider
import net.devoev.vanilla_cubed.tag.ModItemTagProvider
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator

object VanillaCubedDataGeneration : DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(fabricDataGenerator: FabricDataGenerator) {
        val pack = fabricDataGenerator.createPack()
        pack.addProvider(::ModRecipeProvider)
        pack.addProvider(::ModItemTagProvider)
        pack.addProvider(::ModModelProvider)
    }
}