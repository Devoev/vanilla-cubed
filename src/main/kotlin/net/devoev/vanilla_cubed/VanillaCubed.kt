package net.devoev.vanilla_cubed

import net.devoev.vanilla_cubed.item.ModItems
import net.devoev.vanilla_cubed.loot.ModLootTableCallbacks
import net.fabricmc.api.ModInitializer

object VanillaCubed : ModInitializer {

    const val MOD_ID = "vanilla_cubed"

    override fun onInitialize() {
        ModItems.init()
        ModLootTableCallbacks.init()
    }
}

