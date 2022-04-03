package net.devoev.vanilla_cubed.world.feature

import net.devoev.vanilla_cubed.block.ModBlocks
import net.devoev.vanilla_cubed.util.RegistryManager
import net.minecraft.block.BlockState
import net.minecraft.structure.rule.RuleTest
import net.minecraft.util.registry.BuiltinRegistries
import net.minecraft.world.gen.feature.ConfiguredFeature
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.OreConfiguredFeatures
import net.minecraft.world.gen.feature.OreFeatureConfig

object ModConfiguredFeatures : RegistryManager<ConfiguredFeature<*,*>>(BuiltinRegistries.CONFIGURED_FEATURE) {

    val ENDERITE_ORE = create("enderite_ore", OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.ENDERITE_ORE.defaultState, 3)

    /**
     * Creates a [ConfiguredFeature] for ores.
     */
    private fun create(name: String, test: RuleTest, state: BlockState, size: Int): ConfiguredFeature<OreFeatureConfig,Feature<OreFeatureConfig>>
        = create(name, ConfiguredFeature(Feature.ORE, OreFeatureConfig(test, state, size)))
}