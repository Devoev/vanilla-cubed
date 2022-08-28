package net.devoev.vanilla_cubed.block

import net.devoev.vanilla_cubed.util.RegistryManager
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.*
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.registry.Registry

object ModBlocks : RegistryManager<Block>(Registry.BLOCK) {

    // Enderite
    val ENDERITE_ORE = create("enderite_ore",
        OreBlock(FabricBlockSettings.of(Material.STONE, MapColor.PALE_YELLOW)
            .requiresTool()
            .strength(15.0f, 6.0f)
            .sounds(BlockSoundGroup.DEEPSLATE)
        )
    )

    val ENDERITE_BLOCK = create("enderite_block",
        Block(FabricBlockSettings.of(Material.METAL, MapColor.DARK_AQUA)
            .requiresTool()
            .strength(50.0f, 1200.0f)
            .sounds(BlockSoundGroup.NETHERITE)
        )
    )

    // Ancient gold
    val ANCIENT_GOLD_BLOCK = create("ancient_gold_block",
        Block(FabricBlockSettings.of(Material.METAL, MapColor.GOLD)
            .requiresTool()
            .strength(3f, 6f)
            .sounds(BlockSoundGroup.METAL)
        )
    )

    // Amethyst
    val AMETHYST_CRYSTAL_BLOCK = create("amethyst_crystal_block",
        Block(FabricBlockSettings.of(Material.AMETHYST, MapColor.PURPLE)
            .requiresTool()
            .strength(5f, 6f)
            .sounds(BlockSoundGroup.AMETHYST_BLOCK)
        )
    )
}