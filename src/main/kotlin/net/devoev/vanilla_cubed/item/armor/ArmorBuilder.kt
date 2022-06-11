package net.devoev.vanilla_cubed.item.armor

import net.devoev.vanilla_cubed.item.behavior.ItemBehaviors
import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.ArmorItem
import net.minecraft.item.ArmorMaterial
import net.minecraft.item.Item.Settings

/**
 * Builds all 4 armor pieces for given [data].
 */
open class ArmorBuilder(protected val data: ArmorData, protected val behaviors: ItemBehaviors<ArmorItem>) {

    constructor(
        material: ArmorMaterial,
        settings: Settings
    ) : this(ArmorData(material, settings), ItemBehaviors())

    open val helmet: ArmorItem get() = buildArmorItem(EquipmentSlot.HEAD)
    open val chestplate: ArmorItem get() = buildArmorItem(EquipmentSlot.CHEST)
    open val leggings: ArmorItem get() = buildArmorItem(EquipmentSlot.LEGS)
    open val boots: ArmorItem get() = buildArmorItem(EquipmentSlot.FEET)

    protected open fun buildArmorItem(slot: EquipmentSlot) = ModArmorItem(data, slot, behaviors)

    operator fun component1() = helmet
    operator fun component2() = chestplate
    operator fun component3() = leggings
    operator fun component4() = boots
}