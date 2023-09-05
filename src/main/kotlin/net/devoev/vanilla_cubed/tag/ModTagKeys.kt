package net.devoev.vanilla_cubed.tag

import net.devoev.vanilla_cubed.VanillaCubed
import net.minecraft.item.Item
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey

object ModTagKeys : Map<TagKey<Item>, RegistryKey<out Registry<*>>> by mapOf() {

    val ENDERITE_ITEM = create("enderite", RegistryKeys.ITEM)

    /**
     * Creates a new entry of the given [name] to the given [registryKey] and returns it.
     */
    fun <T> create(name: String, registryKey: RegistryKey<out Registry<T>>): TagKey<T>
        = TagKey.of(registryKey, VanillaCubed.id(name))
}