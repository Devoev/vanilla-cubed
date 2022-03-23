package net.devoev.vanilla_cubed

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

object VanillaCubed : ModInitializer {

    private const val MOD_ID = "vanilla_cubed"

    private val items = mapOf(
        "donut" to Item(FabricItemSettings().group(ItemGroup.FOOD))
    )

    private val blocks = mapOf<String, Block>()

    override fun onInitialize() {
        register(Registry.ITEM, items)
        register(Registry.BLOCK, blocks)
    }

    private fun <V, T : V> register(type: Registry<V>, entries: Map<String, T>) {
        for (e in entries)
            Registry.register(type, Identifier(MOD_ID, e.key), e.value)
    }
}

