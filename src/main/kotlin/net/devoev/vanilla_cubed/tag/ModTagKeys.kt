package net.devoev.vanilla_cubed.tag

import net.devoev.vanilla_cubed.VanillaCubed
import net.devoev.vanilla_cubed.util.MapInitializer
import net.devoev.vanilla_cubed.util.SetInitializer
import net.minecraft.item.Item
import net.minecraft.tag.TagKey
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import net.minecraft.util.registry.RegistryKey

object ModTagKeys : Map<TagKey<Item>, RegistryKey<out Registry<*>>> by mapOf() {

    val ENDERITE_ITEM = create("enderite", Registry.ITEM_KEY)

    /**
     * Creates a new entry of the given [name] to the given [registryKey] and returns it.
     */
    fun <T> create(name: String, registryKey: RegistryKey<out Registry<T>>): TagKey<T>
        = TagKey.of(registryKey, VanillaCubed.id(name))
}