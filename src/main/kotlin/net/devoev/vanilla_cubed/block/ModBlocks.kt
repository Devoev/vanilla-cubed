package net.devoev.vanilla_cubed.block

import net.devoev.vanilla_cubed.util.RegistryManager
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.ExperienceDroppingBlock
import net.minecraft.block.MapColor
import net.minecraft.registry.Registries
import net.minecraft.sound.BlockSoundGroup

object ModBlocks : RegistryManager<Block>(Registries.BLOCK) {

    // Enderite
    val ENDERITE_ORE = create("enderite_ore",
        ExperienceDroppingBlock(FabricBlockSettings.create()
            .mapColor(MapColor.PALE_YELLOW)
            .requiresTool()
            .strength(15.0f, 6.0f)
            .sounds(BlockSoundGroup.DEEPSLATE)
        )
    )

    val ENDERITE_BLOCK = create("enderite_block",
        Block(FabricBlockSettings.create()
            .mapColor(MapColor.DARK_AQUA)
            .requiresTool()
            .strength(50.0f, 1200.0f)
            .sounds(BlockSoundGroup.NETHERITE)
        )
    )

    // Ancient gold
    val ANCIENT_GOLD_BLOCK = create("ancient_gold_block",
        Block(FabricBlockSettings.create()
            .mapColor(MapColor.GOLD)
            .requiresTool()
            .strength(3f, 6f)
            .sounds(BlockSoundGroup.METAL)
        )
    )

    // Amethyst
    val AMETHYST_CRYSTAL_BLOCK = create("amethyst_crystal_block",
        Block(FabricBlockSettings.create()
            .mapColor(MapColor.PURPLE)
            .requiresTool()
            .strength(5f, 6f)
            .sounds(BlockSoundGroup.AMETHYST_BLOCK)
        )
    )

    val CHARGED_AMETHYST_CRYSTAL_BLOCK = create("amethyst_crystal_block_charged",
        Block(FabricBlockSettings.create()
            .mapColor(MapColor.PURPLE)
            .requiresTool()
            .strength(5f, 6f)
            .sounds(BlockSoundGroup.AMETHYST_BLOCK)
        )
    )
}