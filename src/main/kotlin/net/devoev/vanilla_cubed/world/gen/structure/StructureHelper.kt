package net.devoev.vanilla_cubed.world.gen.structure

import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
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
    @Deprecated("Generating TagKeys not working properly")
    val tagKeys: Set<TagKey<Structure>> = keys.map { TagKey.of(RegistryKeys.STRUCTURE, it.value) }.toSet()
}