package net.devoev.vanilla_cubed

import net.devoev.vanilla_cubed.block.ModBlocks
import net.devoev.vanilla_cubed.item.ModItems
import net.devoev.vanilla_cubed.loot.ModLootTableCallbacks
import net.fabricmc.api.ModInitializer
import net.minecraft.util.Identifier

object VanillaCubed : ModInitializer {

    const val MOD_ID = "vanilla_cubed"

    override fun onInitialize() {
        ModItems.init()
        ModBlocks.init()
        ModLootTableCallbacks.init()
    }

    /**
     * Returns an [Identifier] for the given [name].
     */
    fun id(name: String): Identifier = Identifier(MOD_ID, name)
}

