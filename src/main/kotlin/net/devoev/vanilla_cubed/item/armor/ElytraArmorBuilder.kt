package net.devoev.vanilla_cubed.item.armor

import net.devoev.vanilla_cubed.item.behavior.Behaviors
import net.devoev.vanilla_cubed.item.behavior.DataBehaviors
import net.devoev.vanilla_cubed.item.behavior.InventoryTickBehavior
import net.devoev.vanilla_cubed.item.behavior.PostHitBehavior
import net.fabricmc.fabric.api.entity.event.v1.FabricElytraItem
import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.ArmorItem
import net.minecraft.item.ArmorMaterial
import net.minecraft.item.Item

/**
 * An [ArmorBuilder] that also constructs a [FabricElytraItem] for the chestplate slot.
 */
open class ElytraArmorBuilder(data: ArmorData, behaviors: Behaviors<ArmorItem>, onlyOne: Boolean) :
    ArmorBuilder(data, behaviors, onlyOne) {

    constructor(
        material: ArmorMaterial,
        settings: Item.Settings,
        inventoryTickBehavior: InventoryTickBehavior<ArmorItem> = InventoryTickBehavior.DEFAULT,
        postHitBehavior: PostHitBehavior<ArmorItem> = PostHitBehavior.DEFAULT,
        onlyOne: Boolean = false
    ) : this(ArmorData(material, settings),
        DataBehaviors(inventoryTickBehavior, postHitBehavior),
        onlyOne)

    open val elytra: ArmorItem = object : ModArmorItem(data, EquipmentSlot.CHEST, behaviors), FabricElytraItem {}

    operator fun component5() = elytra
}