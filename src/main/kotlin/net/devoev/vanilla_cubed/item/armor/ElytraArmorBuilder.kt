package net.devoev.vanilla_cubed.item.armor

import net.devoev.vanilla_cubed.item.behavior.*
import net.fabricmc.fabric.api.entity.event.v1.FabricElytraItem
import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.ArmorItem
import net.minecraft.item.ArmorMaterial
import net.minecraft.item.Item

/**
 * An [ArmorBuilder] that also constructs a [FabricElytraItem] for the chestplate slot.
 */
open class ElytraArmorBuilder(data: ArmorData, behaviors: Behaviors<ArmorItem>) :
    ArmorBuilder(data, behaviors) {

    constructor(
        material: ArmorMaterial,
        settings: Item.Settings,
        inventoryTickBehavior: InventoryTickBehavior<ArmorItem> = INVENTORY_TICK_DEFAULT,
        postHitBehavior: PostHitBehavior<ArmorItem> = POST_HIT_DEFAULT
    ) : this(
        ArmorData(material, settings),
        DataBehaviors(inventoryTickBehavior, postHitBehavior)
    )

    open val elytra: ArmorItem = object : ModArmorItem(data, EquipmentSlot.CHEST, behaviors), FabricElytraItem {}

    operator fun component5() = elytra
}