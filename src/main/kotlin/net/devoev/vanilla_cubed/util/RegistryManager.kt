package net.devoev.vanilla_cubed.util

import net.devoev.vanilla_cubed.VanillaCubed
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

/**
 * Manages pairs of [V]-[Identifier] which needs to be registered in a [Registry].
 */
abstract class RegistryManager<V>(private val registry: Registry<V>, vararg pairs: Pair<Identifier, V>)
    : MutableMap<Identifier, V> by mutableMapOf(*pairs) {

    /**
     * Creates a new entry of the given [pair] to this registry and returns it.
     */
    fun <T : V> create(pair: Pair<Identifier, T>): T {
        val (id, element) = pair
        this[id] = element
        return element
    }

    /**
     * Creates a new entry of the given [name] and [element] to this registry and returns it.
     */
    fun <T : V> create(name: String, element: T): T = create(VanillaCubed.id(name) to element)

    /**
     * Initializes this registry by registering all entries.
     */
    fun init() = forEach { Registry.register(registry, it.key, it.value) }
}