package net.devoev.vanilla_cubed.util

import net.devoev.vanilla_cubed.item.ModArmor
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ArmorItem
import net.minecraft.item.ArmorMaterial
import net.minecraft.item.Item.Settings

/**
 * Builds all 4 armor pieces for given [ArmorMaterial] and [Settings].
 */
class ArmorBuilder private constructor(build: (EquipmentSlot) -> ArmorItem) {

    val helmet: ArmorItem = build(EquipmentSlot.HEAD)
    val chestplate: ArmorItem = build(EquipmentSlot.CHEST)
    val leggings: ArmorItem = build(EquipmentSlot.LEGS)
    val boots: ArmorItem = build(EquipmentSlot.FEET)

    constructor(material: ArmorMaterial, settings: Settings) : this({ slot -> ArmorItem(material, slot, settings) })
    constructor(material: ArmorMaterial, settings: Settings, effect: StatusEffectInstance) : this({ slot -> EffectArmor(material, slot, settings, effect) })

    /**
     * Armor that applies a [StatusEffect] as a full armor effect.
     */
    private class EffectArmor(material: ArmorMaterial, slot: EquipmentSlot, settings: Settings, private val statusEffect: StatusEffectInstance) : ModArmor(material, slot, settings) {
        override fun applyFullArmorEffect(player: PlayerEntity) {
            player.addStatusEffect(statusEffect)
        }
    }
}