package net.devoev.vanilla_cubed.item

import net.devoev.vanilla_cubed.materials.ModArmorMaterials
import net.devoev.vanilla_cubed.materials.ModToolMaterials
import net.devoev.vanilla_cubed.util.RegistryManager
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.item.ArmorItem
import net.minecraft.item.Item
import net.minecraft.item.ShovelItem
import net.minecraft.item.SwordItem
import net.minecraft.util.registry.Registry

/**
 * All modded items.
 */
object ModItems : RegistryManager<Item>(Registry.ITEM) {

    val ELDER_GUARDIAN_SHARD = create("elder_guardian_shard", Item(ModItemGroup.MATERIALS.toSettings()))

    val GILDED_CLUSTER = create("gilded_cluster", Item(ModItemGroup.MATERIALS.toSettings()))
    val ANCIENT_GOLD_INGOT = create("ancient_gold_ingot", Item(ModItemGroup.MATERIALS.toSettings()))
    val ANCIENT_GOLD_PICKAXE = create("ancient_gold_pickaxe", ModPickaxe(ModToolMaterials.ANCIENT_GOLD, 1, 1F, ModItemGroup.TOOLS.toSettings()))
    val ANCIENT_GOLD_CHESTPLATE = create("ancient_gold_chestplate", ArmorItem(ModArmorMaterials.ANCIENT_GOLD, EquipmentSlot.CHEST, ModItemGroup.COMBAT.toSettings()))

    //Amethyst
    val AMETHYST_CRYSTAL = create("amethyst_crystal", Item(ModItemGroup.MATERIALS.toSettings()))
    val CHARGED_AMETHYST_CRYSTAL = create("amethyst_crystal_charged", Item(ModItemGroup.MATERIALS.toSettings()))

    val AMETHYST_SWORD = create("amethyst_sword", SwordItem(ModToolMaterials.AMETHYST, 3, -2F, ModItemGroup.TOOLS.toSettings()))
    val AMETHYST_SHOVEL = create("amethyst_shovel", ShovelItem(ModToolMaterials.AMETHYST, 1.5F, -2.6F, ModItemGroup.TOOLS.toSettings()))
    val AMETHYST_PICKAXE = create("amethyst_pickaxe", ModPickaxe(ModToolMaterials.AMETHYST, 1, -2.4F, ModItemGroup.TOOLS.toSettings()))
    val AMETHYST_AXE = create("amethyst_axe", ModAxe(ModToolMaterials.AMETHYST, 5.0F, -2.6F, ModItemGroup.TOOLS.toSettings()))
    val AMETHYST_HOE = create("amethyst_hoe", ModHoe(ModToolMaterials.AMETHYST, -3, 0F, ModItemGroup.TOOLS.toSettings()))

    val AMETHYST_HELMET= create("amethyst_helmet", ModArmor(ModArmorMaterials.AMETHYST, EquipmentSlot.HEAD, ModItemGroup.COMBAT.toSettings(), StatusEffects.HASTE))
    val AMETHYST_CHESTPLATE = create("amethyst_chestplate", ArmorItem(ModArmorMaterials.AMETHYST, EquipmentSlot.CHEST, ModItemGroup.COMBAT.toSettings()))
    val AMETHYST_LEGGINGS = create("amethyst_leggings", ArmorItem(ModArmorMaterials.AMETHYST, EquipmentSlot.LEGS, ModItemGroup.COMBAT.toSettings()))
    val AMETHYST_BOOTS = create("amethyst_boots", ArmorItem(ModArmorMaterials.AMETHYST, EquipmentSlot.FEET, ModItemGroup.COMBAT.toSettings()))
}