package net.devoev.vanilla_cubed.util

import net.devoev.vanilla_cubed.world.gen.ModWorldGeneration

/**
 * An initializer object for initializing objects of type [T].
 */
abstract class ListInitializer<T> : MutableList<T> by mutableListOf<T>() {

    /**
     * Initializes all stored entries.
     */
    abstract fun init()

    protected fun create(value: T): T {
        add(value)
        return value
    }
}