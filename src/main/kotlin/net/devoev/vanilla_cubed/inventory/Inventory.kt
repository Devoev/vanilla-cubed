package net.devoev.vanilla_cubed.inventory

import net.minecraft.inventory.Inventory
import net.minecraft.item.ItemStack

/**
 * Returns the size of the collection.
 */
val Inventory.size: Int
    get() = size()

/**
 * Returns an [IntRange] of the valid indices for this collection.
 */
val Inventory.indices: IntRange
    get() = 0..<size

/**
 * Returns an iterator over all [stacks][ItemStack] contained in this inventory.
 */
operator fun Inventory.iterator(): Iterator<ItemStack> = iterator {
    for (i in indices)
        yield(getStack(i))
}

/**
 * Returns this inventory as an [Iterable].
 */
fun Inventory.asIterable(): Iterable<ItemStack> = Iterable { iterator() }

/**
 * Returns this inventory as a [Sequence].
 */
fun Inventory.asSequence(): Sequence<ItemStack> = Sequence { iterator() }

/**
 * Converts this inventory to a [List].
 */
fun Inventory.toList(): List<ItemStack> = asIterable().toList()

/**
 * Converts this inventory to a [Set].
 */
fun Inventory.toSet(): Set<ItemStack> = asIterable().toSet()