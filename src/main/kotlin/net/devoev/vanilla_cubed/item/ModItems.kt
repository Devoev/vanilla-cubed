package net.devoev.vanilla_cubed.item

import net.devoev.vanilla_cubed.block.ModBlocks
import net.devoev.vanilla_cubed.materials.ModArmorMaterials
import net.devoev.vanilla_cubed.materials.ModToolMaterials
import net.devoev.vanilla_cubed.util.ArmorBuilder
import net.devoev.vanilla_cubed.util.RegistryManager
import net.devoev.vanilla_cubed.util.ToolBuilder
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.util.registry.Registry

/**
 * All modded items.
 */
object ModItems : RegistryManager<Item>(Registry.ITEM) {

    //Tridents
    val ELDER_GUARDIAN_SHARD = create("elder_guardian_shard", Item(ModItemGroup.MATERIALS.toSettings()))

    //Ancient Gold
    val GILDED_CLUSTER = create("gilded_cluster", Item(ModItemGroup.MATERIALS.toSettings()))
    val ANCIENT_GOLD_INGOT = create("ancient_gold_ingot", Item(ModItemGroup.MATERIALS.toSettings()))

    private val ANCIENT_GOLD_TOOLS = ToolBuilder(ModToolMaterials.ANCIENT_GOLD, ModItemGroup.TOOLS.toSettings())
    val ANCIENT_GOLD_SWORD = create("ancient_gold_sword", ANCIENT_GOLD_TOOLS.sword)
    val ANCIENT_GOLD_SHOVEL = create("ancient_gold_shovel", ANCIENT_GOLD_TOOLS.shovel)
    val ANCIENT_GOLD_PICKAXE = create("ancient_gold_pickaxe", ANCIENT_GOLD_TOOLS.pickaxe)
    val ANCIENT_GOLD_AXE = create("ancient_gold_axe", ANCIENT_GOLD_TOOLS.axe)
    val ANCIENT_GOLD_HOE = create("ancient_gold_hoe", ANCIENT_GOLD_TOOLS.hoe)

    private val ANCIENT_GOLD_ARMOR = ArmorBuilder(ModArmorMaterials.ANCIENT_GOLD, ModItemGroup.COMBAT.toSettings(), StatusEffectInstance(StatusEffects.LUCK))
    val ANCIENT_GOLD_HELMET = create("ancient_gold_helmet", ANCIENT_GOLD_ARMOR.helmet)
    val ANCIENT_GOLD_CHESTPLATE = create("ancient_gold_chestplate", ANCIENT_GOLD_ARMOR.chestplate)
    val ANCIENT_GOLD_LEGGINGS = create("ancient_gold_leggings", ANCIENT_GOLD_ARMOR.leggings)
    val ANCIENT_GOLD_BOOTS = create("ancient_gold_boots", ANCIENT_GOLD_ARMOR.boots)

    //Amethyst
    val AMETHYST_CRYSTAL = create("amethyst_crystal", Item(ModItemGroup.MATERIALS.toSettings()))
    val CHARGED_AMETHYST_CRYSTAL = create("amethyst_crystal_charged", Item(ModItemGroup.MATERIALS.toSettings()))

    private val AMETHYST_TOOLS = ToolBuilder(ModToolMaterials.AMETHYST, ModItemGroup.TOOLS.toSettings(), attackSpeedAmounts = ToolBuilder.BASE_ATTACK_SPEED.map { it + 0.4F })
    val AMETHYST_SWORD = create("amethyst_sword", AMETHYST_TOOLS.sword)
    val AMETHYST_SHOVEL = create("amethyst_shovel", AMETHYST_TOOLS.shovel)
    val AMETHYST_PICKAXE = create("amethyst_pickaxe", AMETHYST_TOOLS.pickaxe)
    val AMETHYST_AXE = create("amethyst_axe", AMETHYST_TOOLS.axe)
    val AMETHYST_HOE = create("amethyst_hoe", AMETHYST_TOOLS.hoe)

    private val AMETHYST_ARMOR = ArmorBuilder(ModArmorMaterials.AMETHYST, ModItemGroup.COMBAT.toSettings(), StatusEffectInstance(StatusEffects.HASTE))
    val AMETHYST_HELMET = create("amethyst_helmet", AMETHYST_ARMOR.helmet)
    val AMETHYST_CHESTPLATE = create("amethyst_chestplate", AMETHYST_ARMOR.chestplate)
    val AMETHYST_LEGGINGS = create("amethyst_leggings", AMETHYST_ARMOR.leggings)
    val AMETHYST_BOOTS = create("amethyst_boots", AMETHYST_ARMOR.boots)

    //Enderite
    val ENDERITE_ORE = create("enderite_ore", BlockItem(ModBlocks.ENDERITE_ORE, ModItemGroup.MATERIALS.toSettings()))
    val ENDERITE_SHARD = create("enderite_shard", Item(ModItemGroup.MATERIALS.toSettings()))
    val ENDERITE_INGOT = create("enderite_ingot", Item(ModItemGroup.MATERIALS.toSettings()))

    private val ENDERITE_TOOLS = ToolBuilder(ModToolMaterials.ENDERITE, ModItemGroup.TOOLS.toSettings())
    val ENDERITE_SWORD = create("enderite_sword", ENDERITE_TOOLS.sword)
    val ENDERITE_SHOVEL = create("enderite_shovel", ENDERITE_TOOLS.shovel)
    val ENDERITE_PICKAXE = create("enderite_pickaxe", ENDERITE_TOOLS.pickaxe)
    val ENDERITE_AXE = create("enderite_axe", ENDERITE_TOOLS.axe)
    val ENDERITE_HOE = create("enderite_hoe", ENDERITE_TOOLS.hoe)

    private val ENDERITE_ARMOR = ArmorBuilder(ModArmorMaterials.ENDERITE, ModItemGroup.COMBAT.toSettings())
    val ENDERITE_HELMET = create("enderite_helmet", ENDERITE_ARMOR.helmet)
    val ENDERITE_CHESTPLATE = create("enderite_chestplate", ENDERITE_ARMOR.chestplate)
    val ENDERITE_LEGGINGS = create("enderite_leggings", ENDERITE_ARMOR.leggings)
    val ENDERITE_BOOTS = create("enderite_boots", ENDERITE_ARMOR.boots)

    //Dragon Scale
    val DRAGON_SCALE = create("dragon_scale", DragonScale())
    val INFUSED_DRAGON_SCALE = create("dragon_scale_infused", Item(ModItemGroup.MATERIALS.toSettings()))
}