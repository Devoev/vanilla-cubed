package net.devoev.vanilla_cubed.loot

import net.minecraft.item.Item
import net.minecraft.item.Items
import net.minecraft.loot.LootPool
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.loot.provider.number.ConstantLootNumberProvider

/**
 * Builds a new [LootPool.Builder] by using the given [builderAction].
 */
fun buildLootPool(builderAction: LootPool.Builder.() -> Unit): LootPool.Builder {
    val builder = LootPool.builder()
    builder.builderAction()
    return builder
}

/**
 * Creates a constant roll of [value] rolls.
 */
fun LootPool.Builder.constantRolls(value: Float): LootPool.Builder = rolls(ConstantLootNumberProvider.create(value))

/**
 * Creates an entry of the [item] with the [weight].
 */
fun LootPool.Builder.with(item: Item, weight: Int = 1): LootPool.Builder = with(ItemEntry.builder(item).weight(weight))

/**
 * Creates an empty item entry.
 */
fun LootPool.Builder.withEmpty(weight: Int = 1): LootPool.Builder = with(Items.AIR, weight)