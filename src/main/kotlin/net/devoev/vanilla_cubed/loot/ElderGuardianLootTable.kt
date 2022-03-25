package net.devoev.vanilla_cubed.loot

import net.devoev.vanilla_cubed.VanillaCubed
import net.devoev.vanilla_cubed.item.ElderGuardianShard
import net.devoev.vanilla_cubed.item.ModItems
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.loot.provider.number.ConstantLootNumberProvider
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry


object ElderGuardianLootTable : ModLootTable(
    identifier = Identifier("minecraft", "entities/elder_guardian"),
    builder = FabricLootPoolBuilder.builder()
        .rolls(ConstantLootNumberProvider.create(1F))
        .with(ItemEntry.builder(ModItems.ELDER_GUARDIAN_SHARD))
)