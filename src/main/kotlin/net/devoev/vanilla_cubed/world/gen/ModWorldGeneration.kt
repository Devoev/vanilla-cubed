package net.devoev.vanilla_cubed.world.gen

import net.devoev.vanilla_cubed.world.feature.ModPlacedFeatures
import net.fabricmc.fabric.api.biome.v1.BiomeModifications
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors
import net.minecraft.util.registry.Registry
import net.minecraft.util.registry.RegistryKey
import net.minecraft.world.gen.GenerationStep

object ModWorldGeneration {

    fun init() {
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
            GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(Registry.PLACED_FEATURE_KEY, ModPlacedFeatures[ModPlacedFeatures.ENDERITE_ORE]))
    }
}