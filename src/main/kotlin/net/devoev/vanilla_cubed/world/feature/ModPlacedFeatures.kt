package net.devoev.vanilla_cubed.world.feature

import net.devoev.vanilla_cubed.VanillaCubed
import net.devoev.vanilla_cubed.util.MapInitializer
import net.fabricmc.fabric.api.biome.v1.BiomeModifications
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.util.Identifier
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.feature.PlacedFeature
import java.util.function.Predicate

/**
 * All placed features.
 */
object ModPlacedFeatures : MapInitializer<Identifier, PlacedFeatureData>() {

    init {
        create("enderite_ore", BiomeSelectors.foundInTheEnd(), GenerationStep.Feature.UNDERGROUND_ORES)
    }

    /**
     * Creates a new entry for the given [biomeSelectors] and [step] under the [name].
     */
    fun create(
        name: String,
        biomeSelectors: Predicate<BiomeSelectionContext>,
        step: GenerationStep.Feature
    ) {
        val id = VanillaCubed.id(name)
        ModPlacedFeatures[id] = Triple(biomeSelectors, step, placedFeatureKeyOf(id))
    }

    override fun init() {
        ModPlacedFeatures.forEach { _, data ->
            BiomeModifications.addFeature(data.first, data.second, data.third)
        }
    }
}

typealias PlacedFeatureData = Triple<Predicate<BiomeSelectionContext>, GenerationStep.Feature, RegistryKey<PlacedFeature>>

/**
 * Creates a [RegistryKeys.PLACED_FEATURE] for the given [id].
 */
private fun placedFeatureKeyOf(id: Identifier) = RegistryKey.of(RegistryKeys.PLACED_FEATURE, id)