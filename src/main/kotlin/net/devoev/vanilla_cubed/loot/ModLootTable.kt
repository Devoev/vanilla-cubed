package net.devoev.vanilla_cubed.loot

import net.devoev.vanilla_cubed.VanillaCubed
import net.devoev.vanilla_cubed.item.ElderGuardianShard
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback
import net.minecraft.item.Item
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.loot.provider.number.ConstantLootNumberProvider
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

abstract class ModLootTable(private val identifier: Identifier, private val builder: FabricLootPoolBuilder) {

    /**
     * Registers this loot table.
     */
    fun register() {
        LootTableLoadingCallback.EVENT.register { _, _, id, table, _ ->
            if (id == identifier)
                table.pool(builder)
        }
    }
}