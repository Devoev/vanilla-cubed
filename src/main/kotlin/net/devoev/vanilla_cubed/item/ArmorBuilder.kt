package net.devoev.vanilla_cubed.item

import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ArmorItem
import net.minecraft.item.ArmorMaterial
import net.minecraft.item.Item.Settings

/**
 * Builds all 4 armor pieces for the given [material] and [settings].
 */
class ArmorBuilder private constructor(private val material: ArmorMaterial, private val settings: Settings, builder: (EquipmentSlot) -> ArmorItem) {

    val helmet: ArmorItem
    val chestplate: ArmorItem
    val leggings: ArmorItem
    val boots: ArmorItem

    init {
        helmet = builder(EquipmentSlot.HEAD)
        chestplate = builder(EquipmentSlot.CHEST)
        leggings = builder(EquipmentSlot.LEGS)
        boots = builder(EquipmentSlot.FEET)
    }

    constructor(material: ArmorMaterial, settings: Settings) : this(material, settings, {slot -> ArmorItem(material, slot, settings) })
    constructor(material: ArmorMaterial, settings: Settings, effect: StatusEffectInstance) : this(material, settings, {slot -> EffectArmor(material, slot, settings, effect)})

    /**
     * Armor that applies a [StatusEffect] as a full armor effect.
     */
    private class EffectArmor(material: ArmorMaterial, slot: EquipmentSlot, settings: Settings, private val statusEffect: StatusEffectInstance) : ModArmor(material, slot, settings) {
        override fun applyFullArmorEffect(player: PlayerEntity) {
            player.addStatusEffect(statusEffect)
        }
    }
}