package net.devoev.vanilla_cubed.util

import net.minecraft.entity.Entity
import net.minecraft.util.math.BlockPos
import net.minecraft.util.registry.RegistryKey
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.BiomeKeys

/**
 * Returns true, if the entity currently is in the biome specified by the [biomeKey].
 */
fun Entity.isInBiome(biomeKey: RegistryKey<Biome>): Boolean {
    return entityWorld.getBiome(blockPos) == biomeKey
}

/**
 * Returns true, if the entity is underground or in a cave.
 */
fun Entity.isInCave(): Boolean {
    var noSky = false
    for (y in pos.y.toInt()..world.topY) {
        if (world.getBlockState(BlockPos(x.toInt(),y,z.toInt())).isOpaque) {
            noSky = true
            break
        }
    }

    val underground = pos.y < world.seaLevel - 15
    val caveBiome = isInBiome(BiomeKeys.DRIPSTONE_CAVES) || isInBiome(BiomeKeys.LUSH_CAVES)
    return (caveBiome || underground) && noSky
}