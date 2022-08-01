package net.devoev.vanilla_cubed.block

import net.devoev.vanilla_cubed.util.RegistryManager
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.MapColor
import net.minecraft.block.Material
import net.minecraft.block.OreBlock
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.registry.Registry

object ModBlocks : RegistryManager<Block>(Registry.BLOCK) {

    //Enderite
    val ENDERITE_ORE = create("enderite_ore",
        OreBlock(FabricBlockSettings.of(Material.STONE, MapColor.PALE_YELLOW)
            .requiresTool()
            .strength(15.0f, 6.0f)
            .sounds(BlockSoundGroup.DEEPSLATE)
        )
    )

    val ENDERITE_BLOCK = create("enderite_block.json",
        Block(FabricBlockSettings.of(Material.METAL, MapColor.DARK_AQUA)
            .requiresTool()
            .strength(50.0f, 1200.0f)
            .sounds(BlockSoundGroup.METAL)
        )
    )
}