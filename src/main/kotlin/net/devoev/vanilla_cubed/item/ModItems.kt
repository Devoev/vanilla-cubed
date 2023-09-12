package net.devoev.vanilla_cubed.item

import net.devoev.vanilla_cubed.block.ModBlocks
import net.devoev.vanilla_cubed.entity.projectile.NetheriteTridentEntity
import net.devoev.vanilla_cubed.item.armor.ModArmor
import net.devoev.vanilla_cubed.item.tool.ModTools
import net.devoev.vanilla_cubed.item.tool.ModTridentItem
import net.devoev.vanilla_cubed.util.RegistryManager
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.ArmorItem
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ToolMaterials
import net.minecraft.registry.Registries

/**
 * All modded items.
 */
object ModItems : RegistryManager<Item>(Registries.ITEM) {

    //Tridents
    val ELDER_GUARDIAN_SHARD = create("elder_guardian_shard", Item(FabricItemSettings()))
    val NETHERITE_TRIDENT = create("netherite_trident", ModTridentItem(ToolMaterials.NETHERITE, FabricItemSettings().maxDamage(324).fireproof(), ::NetheriteTridentEntity))

    //Ancient Gold
    val GILDED_CLUSTER = create("gilded_cluster", Item(FabricItemSettings()))
    val ANCIENT_GOLD_INGOT = create("ancient_gold_ingot", Item(FabricItemSettings()))
    val ANCIENT_GOLD_BLOCK = create("ancient_gold_block", BlockItem(ModBlocks.ANCIENT_GOLD_BLOCK, FabricItemSettings()))
    val GILDED_BOOK = create("gilded_book", GildedBook())
    val ANCIENT_GOLD_UPGRADE_SMITHING_TEMPLATE = create("ancient_gold_upgrade_smithing_template", ancientGoldUpgrade)


    val ANCIENT_GOLD_SWORD = create("ancient_gold_sword", ModTools.ANCIENT_GOLD.sword)
    val ANCIENT_GOLD_SHOVEL = create("ancient_gold_shovel", ModTools.ANCIENT_GOLD.shovel)
    val ANCIENT_GOLD_PICKAXE = create("ancient_gold_pickaxe", ModTools.ANCIENT_GOLD.pickaxe)
    val ANCIENT_GOLD_AXE = create("ancient_gold_axe", ModTools.ANCIENT_GOLD.axe)
    val ANCIENT_GOLD_HOE = create("ancient_gold_hoe", ModTools.ANCIENT_GOLD.hoe)

    val ANCIENT_GOLD_HELMET = create("ancient_gold_helmet", ModArmor.ANCIENT_GOLD.helmet)
    val ANCIENT_GOLD_CHESTPLATE = create("ancient_gold_chestplate", ModArmor.ANCIENT_GOLD.chestplate)
    val ANCIENT_GOLD_LEGGINGS = create("ancient_gold_leggings", ModArmor.ANCIENT_GOLD.leggings)
    val ANCIENT_GOLD_BOOTS = create("ancient_gold_boots", ModArmor.ANCIENT_GOLD.boots)

    //Amethyst
    val AMETHYST_CRYSTAL = create("amethyst_crystal", Item(FabricItemSettings()))
    val CHARGED_AMETHYST_CRYSTAL = create("amethyst_crystal_charged", Item(FabricItemSettings()))
    val AMETHYST_CRYSTAL_BLOCK = create("amethyst_crystal_block", BlockItem(ModBlocks.AMETHYST_CRYSTAL_BLOCK, FabricItemSettings()))
    val CHARGED_AMETHYST_CRYSTAL_BLOCK = create("amethyst_crystal_block_charged", BlockItem(ModBlocks.CHARGED_AMETHYST_CRYSTAL_BLOCK, FabricItemSettings()))
    val AMETHYST_COMPASS = create("amethyst_compass", AmethystCompass())
    val AMETHYST_UPGRADE_SMITHING_TEMPLATE = create("amethyst_upgrade_smithing_template", amethystUpgrade)


    val AMETHYST_SWORD = create("amethyst_sword", ModTools.AMETHYST.sword)
    val AMETHYST_SHOVEL = create("amethyst_shovel", ModTools.AMETHYST.shovel)
    val AMETHYST_PICKAXE = create("amethyst_pickaxe", ModTools.AMETHYST.pickaxe)
    val AMETHYST_AXE = create("amethyst_axe", ModTools.AMETHYST.axe)
    val AMETHYST_HOE = create("amethyst_hoe", ModTools.AMETHYST.hoe)

    val AMETHYST_HELMET = create("amethyst_helmet", ModArmor.AMETHYST.helmet)
    val AMETHYST_CHESTPLATE = create("amethyst_chestplate", ModArmor.AMETHYST.chestplate)
    val AMETHYST_LEGGINGS = create("amethyst_leggings", ModArmor.AMETHYST.leggings)
    val AMETHYST_BOOTS = create("amethyst_boots", ModArmor.AMETHYST.boots)

    //Enderite
    val ENDERITE_ORE = create("enderite_ore", BlockItem(ModBlocks.ENDERITE_ORE, FabricItemSettings()))
    val ENDERITE_BLOCK = create("enderite_block", BlockItem(ModBlocks.ENDERITE_BLOCK, FabricItemSettings()))
    val ENDERITE_SHARD = create("enderite_shard", Item(FabricItemSettings()))
    val ENDERITE_INGOT = create("enderite_ingot", Item(FabricItemSettings()))
    val ENDERITE_POWDER = create("enderite_powder", EnderitePowder())
    val ENDERITE_UPGRADE_SMITHING_TEMPLATE = create("enderite_upgrade_smithing_template", enderiteUpgrade)


    val ENDERITE_SWORD = create("enderite_sword", ModTools.ENDERITE.sword)
    val ENDERITE_SHOVEL = create("enderite_shovel", ModTools.ENDERITE.shovel)
    val ENDERITE_PICKAXE = create("enderite_pickaxe", ModTools.ENDERITE.pickaxe)
    val ENDERITE_AXE = create("enderite_axe", ModTools.ENDERITE.axe)
    val ENDERITE_HOE = create("enderite_hoe", ModTools.ENDERITE.hoe)
    val ENDERITE_TRIDENT = create("enderite_trident", ModTools.ENDERITE.trident)

    val ENDERITE_HELMET = create("enderite_helmet", ModArmor.ENDERITE.helmet)
    val ENDERITE_CHESTPLATE = create("enderite_chestplate", ModArmor.ENDERITE.chestplate)
    val ENDERITE_LEGGINGS = create("enderite_leggings", ModArmor.ENDERITE.leggings)
    val ENDERITE_BOOTS = create("enderite_boots", ModArmor.ENDERITE.boots)

    //Dragon Scale
    val DRAGON_SCALE = create("dragon_scale", DragonScale())
    val INFUSED_DRAGON_SCALE = create("dragon_scale_infused", Item(FabricItemSettings()))
    val INFUSED_DRAGON_SCALE_CHUNK = create("dragon_scale_chunk_infused", Item(FabricItemSettings()))
    val INFUSED_FIREWORK_ROCKET = create("firework_rocket_infused", InfusedFireworkRocket())
    val DRAGON_SCALE_UPGRADE_SMITHING_TEMPLATE = create("dragon_scale_upgrade_smithing_template", dragonScaleUpgrade)
    val ELYTRA_UPGRADE_SMITHING_TEMPLATE = create("elytra_upgrade_smithing_template", elytraUpgrade)

    val DRAGON_SCALE_SWORD = create("dragon_scale_sword", ModTools.DRAGON_SCALE.sword)
    val DRAGON_SCALE_SHOVEL = create("dragon_scale_shovel", ModTools.DRAGON_SCALE.shovel)
    val DRAGON_SCALE_PICKAXE = create("dragon_scale_pickaxe", ModTools.DRAGON_SCALE.pickaxe)
    val DRAGON_SCALE_AXE = create("dragon_scale_axe", ModTools.DRAGON_SCALE.axe)
    val DRAGON_SCALE_HOE = create("dragon_scale_hoe", ModTools.DRAGON_SCALE.hoe)

    val DRAGON_SCALE_HELMET = create("dragon_scale_helmet", ModArmor.DRAGON_SCALE.helmet)
    val DRAGON_SCALE_CHESTPLATE = create("dragon_scale_chestplate", ModArmor.DRAGON_SCALE.chestplate)
    val DRAGON_SCALE_LEGGINGS = create("dragon_scale_leggings", ModArmor.DRAGON_SCALE.leggings)
    val DRAGON_SCALE_BOOTS = create("dragon_scale_boots", ModArmor.DRAGON_SCALE.boots)
    val WINGED_DRAGON_SCALE_CHESTPLATE = create("dragon_scale_chestplate_winged", ModArmor.DRAGON_SCALE.elytra)
}

/**
 * List of all modded [armor items][ArmorItem].
 */
val ModItems.armorItems: List<ArmorItem>
    get() = values.filterIsInstance<ArmorItem>()