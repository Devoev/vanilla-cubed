package net.devoev.vanilla_cubed.loot

import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback
import net.minecraft.util.Identifier

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