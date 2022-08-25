package net.devoev.vanilla_cubed.world.gen.structure

import net.minecraft.tag.TagKey
import net.minecraft.util.registry.Registry
import net.minecraft.util.registry.RegistryKey
import net.minecraft.world.gen.structure.Structure
import net.minecraft.world.gen.structure.StructureKeys
import kotlin.reflect.KVisibility
import kotlin.reflect.full.staticProperties

/**
 * An object with helper functions for [structures][Structure].
 */
object StructureHelper {

    /**
     * A [Set] of all unique structure keys.
     */
    val keys: Set<RegistryKey<Structure>> = StructureKeys::class.staticProperties
        .filter { it.visibility == KVisibility.PUBLIC }
        .map { it.get() }
        .filterIsInstance<RegistryKey<Structure>>()
        .toSet()

    /**
     * A [Set] of all unique structure tag keys.
     */
    val tagKeys: Set<TagKey<Structure>> = keys.map { TagKey.of(Registry.STRUCTURE_KEY, it.value) }.toSet()
}