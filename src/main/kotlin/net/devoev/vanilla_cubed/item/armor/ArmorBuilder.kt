package net.devoev.vanilla_cubed.item.armor

import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.ArmorItem
import net.minecraft.item.ArmorMaterial
import net.minecraft.item.Item.Settings

/**
 * Builds all 4 armor pieces for given [material] and [settings].
 */
open class ArmorBuilder(protected val material: ArmorMaterial, protected val settings: Settings) {

    open val helmet: ArmorItem get() = buildArmorItem(EquipmentSlot.HEAD)
    open val chestplate: ArmorItem get() = buildArmorItem(EquipmentSlot.CHEST)
    open val leggings: ArmorItem get() = buildArmorItem(EquipmentSlot.LEGS)
    open val boots: ArmorItem get() = buildArmorItem(EquipmentSlot.FEET)

    protected open fun buildArmorItem(slot: EquipmentSlot) = ArmorItem(material, slot, settings)

    operator fun component1() = helmet
    operator fun component2() = chestplate
    operator fun component3() = leggings
    operator fun component4() = boots
}