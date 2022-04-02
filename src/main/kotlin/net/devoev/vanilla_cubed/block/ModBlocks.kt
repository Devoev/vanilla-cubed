package net.devoev.vanilla_cubed.block

import net.devoev.vanilla_cubed.util.RegistryManager
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.Material
import net.minecraft.block.OreBlock
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.registry.Registry

object ModBlocks : RegistryManager<Block>(Registry.BLOCK) {

    val ENDERITE_ORE = create("enderite_ore", OreBlock(FabricBlockSettings.of(Material.METAL).requiresTool()
        .strength(15.0f, 6.0f).sounds(BlockSoundGroup.DEEPSLATE)))
}