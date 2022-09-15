package net.devoev.vanilla_cubed.entity

import net.minecraft.entity.Entity
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import net.minecraft.util.registry.RegistryKey
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.BiomeKeys

/**
 * Returns true, if the entity currently is in the biome specified by the [biomeKey].
 */
fun Entity.inBiome(biomeKey: RegistryKey<Biome>): Boolean {
    return entityWorld.getBiome(blockPos) == biomeKey
}

/**
 * Returns true, if the entity is underground or in a cave.
 */
val Entity.inCave: Boolean get() {
    val blocks = BlockPos.iterate(blockPos.add(0,2,0), blockPos.withY(world.topY)).map { world.getBlockState(it) }
    val blockOverhead = blocks.any { it.isSolidBlock(world, blockPos) && it.isOpaqueFullCube(world, blockPos) }

    val underground = pos.y < world.seaLevel - 10
    val caveBiome = inBiome(BiomeKeys.DRIPSTONE_CAVES) || inBiome(BiomeKeys.LUSH_CAVES)
    return (caveBiome || underground) && blockOverhead
}

fun Entity.addVelocity(vec: Vec3d) = addVelocity(vec.x, vec.y, vec.z)

/**
 * Returns true, if the entity is falling.
 */
val Entity.falling: Boolean get() = fallDistance != 0f