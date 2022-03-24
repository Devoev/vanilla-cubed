package net.devoev.vanilla_cubed

import net.devoev.vanilla_cubed.item.ElderGuardianShard
import net.devoev.vanilla_cubed.loot.initLoot
import net.fabricmc.api.ModInitializer
import net.minecraft.block.Block
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

object VanillaCubed : ModInitializer {

    const val MOD_ID = "vanilla_cubed"

    private val items = mapOf(
        ElderGuardianShard.ID to ElderGuardianShard()
    )

    private val blocks = mapOf<String, Block>()

    override fun onInitialize() {
        register(Registry.ITEM, items)
        register(Registry.BLOCK, blocks)
        initLoot()
    }

    private fun <V, T : V> register(type: Registry<V>, entries: Map<String, T>) {
        for (e in entries)
            Registry.register(type, Identifier(MOD_ID, e.key), e.value)
    }
}

