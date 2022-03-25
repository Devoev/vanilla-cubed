package net.devoev.vanilla_cubed.util

abstract class RegistryManager<V, E : Identifiable<V>>(vararg elements: E) : MutableSet<E> by mutableSetOf(*elements) {

    /**
     * Adds the given [element] to this registry and returns it.
     */
    fun <T : E> set(element: T): T {
        add(element)
        return element
    }

    /**
     * Initializes this registry by registering all entries.
     */
    fun init() = forEach { it.register() }
}