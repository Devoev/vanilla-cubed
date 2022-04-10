package net.devoev.vanilla_cubed.util

/**
 * An initializer object for initializing [key][K] [value][V] pairs.
 */
abstract class MapInitializer<K, V> : MutableMap<K, V> by mutableMapOf() {

    /**
     * Initializes all stored entries.
     */
    abstract fun init()

    /**
     * Creates a new entry of the given [pair] to this and returns it.
     */
    fun <T : V> create(pair: Pair<K, T>): T {
        val (id, element) = pair
        this[id] = element
        return element
    }

    /**
     * Creates a new entry of the given [key] and [value] to this and returns it.
     */
    fun <T : V> create(key: K, value: T) = create(key to value)

    /**
     * The first key that matches the given [value].
     */
    fun getKey(value: V): K = filterValues { it == value }.keys.first()

    /**
     * The first key that matches the given [value].
     */
    @JvmName("get_by_value")
    operator fun get(value: V): K = getKey(value)
}