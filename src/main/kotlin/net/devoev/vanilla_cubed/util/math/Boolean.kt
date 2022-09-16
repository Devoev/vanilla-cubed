package net.devoev.vanilla_cubed.util.math

/**
 * Converts true to 1 and false to 0.
 */
fun Boolean.toInt() = if (this) 1 else 0

/**
 * Converts true to 1f and false to 0f.
 */
fun Boolean.toFloat() = toInt().toFloat()