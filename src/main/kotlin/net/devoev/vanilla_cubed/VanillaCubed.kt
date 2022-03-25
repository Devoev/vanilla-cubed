package net.devoev.vanilla_cubed

import net.devoev.vanilla_cubed.item.ElderGuardianShard
import net.devoev.vanilla_cubed.item.ModItems
import net.devoev.vanilla_cubed.loot.ModLootTables
import net.devoev.vanilla_cubed.loot.initLoot
import net.fabricmc.api.ModInitializer
import net.minecraft.block.Block
import net.minecraft.item.Items
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

object VanillaCubed : ModInitializer {

    const val MOD_ID = "vanilla_cubed"

    override fun onInitialize() {
        ModItems
        ModLootTables.init()
    }
}

