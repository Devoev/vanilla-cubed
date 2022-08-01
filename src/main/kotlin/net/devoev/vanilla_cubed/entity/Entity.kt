package net.devoev.vanilla_cubed.entity

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
    val blocks = BlockPos.iterate(BlockPos(pos), BlockPos(pos).withY(world.topY))
    val blockOverhead = blocks.any { world.getBlockState(it).isOpaque }

    val underground = pos.y < world.seaLevel - 10
    val caveBiome = isInBiome(BiomeKeys.DRIPSTONE_CAVES) || isInBiome(BiomeKeys.LUSH_CAVES)
    return (caveBiome || underground) && blockOverhead
}