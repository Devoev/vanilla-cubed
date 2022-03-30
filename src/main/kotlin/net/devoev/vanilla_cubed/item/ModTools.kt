package net.devoev.vanilla_cubed.item

import net.minecraft.item.AxeItem
import net.minecraft.item.HoeItem
import net.minecraft.item.PickaxeItem
import net.minecraft.item.ToolMaterial

class ModPickaxe(material: ToolMaterial, attackDamage: Int, attackSpeed: Float, settings: Settings) : PickaxeItem(material, attackDamage, attackSpeed, settings)

class ModAxe(material: ToolMaterial, attackDamage: Float, attackSpeed: Float, settings: Settings) : AxeItem(material, attackDamage, attackSpeed, settings)

class ModHoe(material: ToolMaterial, attackDamage: Int, attackSpeed: Float, settings: Settings) : HoeItem(material, attackDamage, attackSpeed, settings)