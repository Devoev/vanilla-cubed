package net.devoev.vanilla_cubed.item.armor

import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.item.ArmorItem
import net.minecraft.item.ArmorMaterial
import net.minecraft.item.Item.Settings

/**
 * Builds all 4 armor pieces for given [ArmorMaterial] and [Settings].
 * @param effect A status effect that gets applied when wearing a full set of armor.
 */
class ArmorBuilder(private val material: ArmorMaterial,
                   private val settings: Settings,
                   private val effect: StatusEffectInstance? = null) {

    val helmet: ArmorItem get() = ModArmorItem(material, EquipmentSlot.HEAD, settings, effect)
    val chestplate: ArmorItem get() = ModArmorItem(material, EquipmentSlot.CHEST, settings, effect)
    val leggings: ArmorItem get() = ModArmorItem(material, EquipmentSlot.LEGS, settings, effect)
    val boots: ArmorItem get() = ModArmorItem(material, EquipmentSlot.FEET, settings, effect)

    operator fun component1() = helmet
    operator fun component2() = chestplate
    operator fun component3() = leggings
    operator fun component4() = boots
}