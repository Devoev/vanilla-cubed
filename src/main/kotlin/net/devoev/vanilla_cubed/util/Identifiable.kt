package net.devoev.vanilla_cubed.util

import net.devoev.vanilla_cubed.VanillaCubed
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

interface Identifiable<V> {

    val ID: String

    val entry: V

    val type: Registry<V>

    val identifier: Identifier
        get() = Identifier(VanillaCubed.MOD_ID, ID)

    /**
     * Registers the [entry] of this.
     * @return The registered entry.
     */
    fun register(): V = Registry.register(type, Identifier(VanillaCubed.MOD_ID, ID), entry)

    /**
     * Returns the registered [entry] of this.
     */
    fun get() = type.get(identifier)
}