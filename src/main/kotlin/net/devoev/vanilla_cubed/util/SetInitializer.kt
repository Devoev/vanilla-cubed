package net.devoev.vanilla_cubed.util

/**
 * An initializer object for initializing objects of type [T].
 */
abstract class SetInitializer<T> : MutableSet<T> by mutableSetOf() {

    /**
     * Initializes all stored entries.
     */
    abstract fun init()

    protected fun <E : T> create(value: E): E {
        add(value)
        return value
    }
}