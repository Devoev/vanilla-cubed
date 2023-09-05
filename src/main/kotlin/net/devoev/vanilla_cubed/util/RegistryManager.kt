package net.devoev.vanilla_cubed.util

import net.devoev.vanilla_cubed.VanillaCubed
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

/**
 * Manages pairs of [V]-[Identifier] which needs to be registered in a [Registry].
 */
abstract class RegistryManager<V>(private val registry: Registry<V>) : MapInitializer<Identifier, V>() {

    /**
     * Creates a new entry of the given [name] and [element] to this registry and returns it.
     */
    protected fun <T : V> create(name: String, element: T): T = create(VanillaCubed.id(name) to element)

    /**
     * Initializes this registry by registering all entries.
     */
    override fun init() = forEach { Registry.register(registry, it.key, it.value) }
}