package net.devoev.vanilla_cubed.loot

import net.devoev.vanilla_cubed.item.ModItems
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback
import net.minecraft.loot.LootPool
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.loot.provider.number.ConstantLootNumberProvider
import net.minecraft.loot.provider.number.UniformLootNumberProvider
import net.minecraft.util.Identifier

/**
 * An object for initializing all custom loot tables.
 */
object ModLootTables : MutableMap<Identifier, LootPool.Builder> by mutableMapOf() {

    init {
        this["entities/elder_guardian"] = FabricLootPoolBuilder.builder()
            .rolls(ConstantLootNumberProvider.create(1F))
            .with(ItemEntry.builder(ModItems.ELDER_GUARDIAN_SHARD))

        this["entities/ender_dragon"] = FabricLootPoolBuilder.builder()
            .rolls(UniformLootNumberProvider.create(1F, 3F))
            .with(ItemEntry.builder(ModItems.DRAGON_SCALE))
    }

    /**
     * Adds the given [builder] to this. Uses the "minecraft" namespace for the id.
     */
    operator fun set(name: String, builder: LootPool.Builder) = set(Identifier("minecraft", name), builder)

    /**
     * Registers all loot table callbacks.
     */
    fun init() {
        LootTableLoadingCallback.EVENT.register { _, _, id, table, _ ->
            if (contains(id)) table.pool(this[id])
        }
    }
}