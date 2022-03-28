package net.devoev.vanilla_cubed.item

import net.devoev.vanilla_cubed.util.RegistryManager
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.registry.Registry

/**
 * The set of all mod items.
 */
object ModItems : RegistryManager<Item>(Registry.ITEM) {

    val ELDER_GUARDIAN_SHARD = create("elder_guardian_shard", Item(FabricItemSettings().group(ItemGroup.MATERIALS)))
    val GILDED_CLUSTER = create("gilded_cluster", Item(FabricItemSettings().group(ItemGroup.MATERIALS)))
    val ANCIENT_GOLD_INGOT = create("ancient_gold_ingot", Item(FabricItemSettings().group(ItemGroup.MATERIALS)))
    val ANCIENT_GOLD_PICKAXE = create("ancient_gold_pickaxe", AncientGoldPickaxe)
}