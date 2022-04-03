package net.devoev.vanilla_cubed.world.gen

import net.devoev.vanilla_cubed.world.feature.ModPlacedFeatures
import net.fabricmc.fabric.api.biome.v1.BiomeModifications
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors
import net.minecraft.util.registry.Registry
import net.minecraft.util.registry.RegistryKey
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.feature.PlacedFeature
import java.util.function.Predicate

/**
 * All world generation features.
 */
object ModWorldGeneration : MutableList<GenerationFeature> by mutableListOf() {

    val ENDERITE_ORE = create(BiomeSelectors.foundInTheEnd(), GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.ENDERITE_ORE)

    fun create(value: GenerationFeature): GenerationFeature {
        add(value)
        return value
    }

    fun create(biomeSelector: Predicate<BiomeSelectionContext>, step: GenerationStep.Feature, feature: PlacedFeature): GenerationFeature
        = create(Triple(biomeSelector, step, RegistryKey.of(Registry.PLACED_FEATURE_KEY, ModPlacedFeatures[feature])))

    /**
     * Initializes all world generation features.
     */
    fun init() = forEach { BiomeModifications.addFeature(it.first, it.second, it.third) }
}

typealias GenerationFeature = Triple<Predicate<BiomeSelectionContext>, GenerationStep.Feature, RegistryKey<PlacedFeature>>