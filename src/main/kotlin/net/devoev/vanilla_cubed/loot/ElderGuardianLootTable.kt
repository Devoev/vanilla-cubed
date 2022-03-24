package net.devoev.vanilla_cubed.loot

import net.devoev.vanilla_cubed.VanillaCubed
import net.devoev.vanilla_cubed.item.ElderGuardianShard
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.loot.provider.number.ConstantLootNumberProvider
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry


fun initLoot() {
    LootTableLoadingCallback.EVENT.register { _, _, id, table, _ ->
        if (id != Identifier("minecraft", "entities/elder_guardian")) return@register

        val item = Registry.ITEM.get(Identifier(VanillaCubed.MOD_ID, ElderGuardianShard.ID))

        val builder = FabricLootPoolBuilder.builder()
            .rolls(ConstantLootNumberProvider.create(1F))
            .with(ItemEntry.builder(item))

        table.pool(builder)
    }
}