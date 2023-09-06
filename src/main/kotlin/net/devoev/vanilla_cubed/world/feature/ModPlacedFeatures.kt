//package net.devoev.vanilla_cubed.world.feature
//
//import net.devoev.vanilla_cubed.util.RegistryManager
//import net.minecraft.util.registry.BuiltinRegistries
//import net.minecraft.util.registry.RegistryEntry
//import net.minecraft.world.gen.YOffset
//import net.minecraft.world.gen.feature.ConfiguredFeature
//import net.minecraft.world.gen.feature.OrePlacedFeatures
//import net.minecraft.world.gen.feature.PlacedFeature
//import net.minecraft.world.gen.placementmodifier.*
//
///**
// * All placed features.
// */
//object ModPlacedFeatures : RegistryManager<PlacedFeature>(BuiltinRegistries.PLACED_FEATURE) {
//
//    val ENDERITE_ORE = create("enderite_ore", ModConfiguredFeatures.ENDERITE_ORE,
//        ORE.modifiersWithCount(7, HeightRangePlacementModifier.uniform(YOffset.aboveBottom(0), YOffset.aboveBottom(70))))
//
//    /**
//     * Creates a [PlacedFeature] for ores.
//     */
//    private fun create(name: String, feature: ConfiguredFeature<*,*>, modifiers: List<PlacementModifier>)
//        = create(name, PlacedFeature(RegistryEntry.of(feature) , modifiers))
//
//    /**
//     * Helper methods for [OrePlacedFeatures].
//     */
//    object ORE {
//        private fun modifiers(countModifier: PlacementModifier, heightModifier: PlacementModifier): List<PlacementModifier>
//                = listOf(countModifier, SquarePlacementModifier.of(), heightModifier, BiomePlacementModifier.of())
//
//        /**
//         * @param count The amount of generated clusters.
//         */
//        fun modifiersWithCount(count: Int, heightModifier: PlacementModifier) = modifiers(CountPlacementModifier.of(count), heightModifier)
//
//        fun modifiersWithRarity(chance: Int, heightModifier: PlacementModifier) = modifiers(RarityFilterPlacementModifier.of(chance), heightModifier)
//    }
//}