package net.devoev.vanilla_cubed.util

/**
 * An initializer object for initializing objects of type [T].
 */
abstract class SetInitializer<T> : MutableSet<T> by mutableSetOf() {

    /**
     * Initializes all stored entries.
     */
    abstract fun init()

    /**
     * Adds the given [value] to the set and returns it.
     */
    protected fun <E : T> create(value: E): E {
        add(value)
        return value
    }
}