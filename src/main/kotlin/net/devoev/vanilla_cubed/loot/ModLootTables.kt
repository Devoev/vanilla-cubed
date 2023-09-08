package net.devoev.vanilla_cubed.loot

import net.devoev.vanilla_cubed.item.ModItems
import net.devoev.vanilla_cubed.util.MapInitializer
import net.fabricmc.fabric.api.loot.v2.LootTableEvents
import net.minecraft.item.Items
import net.minecraft.loot.LootPool
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.loot.provider.number.ConstantLootNumberProvider
import net.minecraft.util.Identifier

/**
 * An object for initializing a custom loot table.
 */
object ModLootTables : MapInitializer<Identifier, LootPool.Builder>() {

    init {
        this["entities/elder_guardian"] = LootPool.builder()
            .rolls(ConstantLootNumberProvider.create(1F))
            .with(ItemEntry.builder(ModItems.ELDER_GUARDIAN_SHARD))

        this["chests/bastion_hoglin_stable"] = LootPool.builder()
            .rolls(ConstantLootNumberProvider.create(1f))
            .with(ItemEntry.builder(Items.AIR).weight(9))
            .with(ItemEntry.builder(ModItems.ANCIENT_GOLD_UPGRADE_SMITHING_TEMPLATE))

        this["chests/bastion_bridge"] = LootPool.builder()
            .rolls(ConstantLootNumberProvider.create(1f))
            .with(ItemEntry.builder(Items.AIR).weight(9))
            .with(ItemEntry.builder(ModItems.ANCIENT_GOLD_UPGRADE_SMITHING_TEMPLATE))

        this["chests/bastion_other"] = LootPool.builder()
            .rolls(ConstantLootNumberProvider.create(1f))
            .with(ItemEntry.builder(Items.AIR).weight(9))
            .with(ItemEntry.builder(ModItems.ANCIENT_GOLD_UPGRADE_SMITHING_TEMPLATE))

        this["chests/bastion_treasure"] = LootPool.builder()
            .rolls(ConstantLootNumberProvider.create(1f))
            .with(ItemEntry.builder(ModItems.ANCIENT_GOLD_UPGRADE_SMITHING_TEMPLATE))
    }

    /**
     * Adds the given [builder] to this. Uses the `minecraft` namespace for the id.
     */
    operator fun set(name: String, builder: LootPool.Builder) = set(Identifier("minecraft", name), builder)

    /**
     * Registers all loot table callbacks.
     */
    override fun init() {
        LootTableEvents.MODIFY.register { _, _, id, table, _ ->
            if (contains(id)) table.pool(this[id])
        }
    }
}