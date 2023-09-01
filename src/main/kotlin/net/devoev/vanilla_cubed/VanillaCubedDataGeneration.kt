package net.devoev.vanilla_cubed

import net.devoev.vanilla_cubed.recipe.ModRecipeProvider
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator

object VanillaCubedDataGeneration : DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(fabricDataGenerator: FabricDataGenerator) {
        println("DATA GEN !!!")
        fabricDataGenerator.addProvider(::ModRecipeProvider)
    }
}