package net.devoev.vanilla_cubed.item.armor

import net.devoev.vanilla_cubed.item.behavior.*
import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.ArmorItem
import net.minecraft.item.ArmorMaterial
import net.minecraft.item.Item.Settings

/**
 * Builds all 4 armor pieces for given [data].
 * @param onlyOne Whether the [behaviors] should only applied to one armor item (helmet).
 */
open class ArmorBuilder(
    protected val data: ArmorData,
    protected val behaviors: Behaviors<ArmorItem>,
    private val onlyOne: Boolean) {

    constructor(
        material: ArmorMaterial,
        settings: Settings,
        inventoryTickBehavior: InventoryTickBehavior<ArmorItem> = InventoryTickBehavior.DEFAULT,
        postHitBehavior: PostHitBehavior<ArmorItem> = PostHitBehavior.DEFAULT,
        postMineBehavior: PostMineBehavior<ArmorItem> = PostMineBehavior.DEFAULT,
        onlyOne: Boolean = false
    ) : this(ArmorData(material, settings),
        DataBehaviors(inventoryTickBehavior, postHitBehavior, postMineBehavior),
        onlyOne)

    open val helmet: ArmorItem get() = buildArmorItem(EquipmentSlot.HEAD)
    open val chestplate: ArmorItem get() = buildArmorItem(EquipmentSlot.CHEST)
    open val leggings: ArmorItem get() = buildArmorItem(EquipmentSlot.LEGS)
    open val boots: ArmorItem get() = buildArmorItem(EquipmentSlot.FEET)

    protected open fun buildArmorItem(slot: EquipmentSlot) =
        ModArmorItem(data, slot, if (!onlyOne || slot == EquipmentSlot.HEAD) behaviors else DataBehaviors())

    operator fun component1() = helmet
    operator fun component2() = chestplate
    operator fun component3() = leggings
    operator fun component4() = boots
}