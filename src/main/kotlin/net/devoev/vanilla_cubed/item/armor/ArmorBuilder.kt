package net.devoev.vanilla_cubed.item.armor

import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.item.ArmorItem
import net.minecraft.item.ArmorMaterial
import net.minecraft.item.Item.Settings

/**
 * Builds all 4 armor pieces for given [ArmorMaterial] and [Settings].
 */
class ArmorBuilder(material: ArmorMaterial, settings: Settings, effect: StatusEffectInstance? = null) {

    val helmet: ArmorItem = ModArmorItem(material, EquipmentSlot.HEAD, settings, effect)
    val chestplate: ArmorItem = ModArmorItem(material, EquipmentSlot.CHEST, settings, effect)
    val leggings: ArmorItem = ModArmorItem(material, EquipmentSlot.LEGS, settings, effect)
    val boots: ArmorItem = ModArmorItem(material, EquipmentSlot.FEET, settings, effect)
}