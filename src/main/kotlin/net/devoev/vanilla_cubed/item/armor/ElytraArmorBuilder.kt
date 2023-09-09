package net.devoev.vanilla_cubed.item.armor

import net.devoev.vanilla_cubed.item.modifier.*
import net.fabricmc.fabric.api.entity.event.v1.FabricElytraItem
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
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
        settings: Item.Settings = FabricItemSettings(),
        inventoryTickModifier: InventoryTickModifier<ArmorItem> = INVENTORY_TICK_DEFAULT,
        postHitModifier: PostHitModifier<ArmorItem> = POST_HIT_DEFAULT
    ) : this(
        ArmorData(material, settings),
        DataBehaviors(inventoryTickModifier, postHitModifier)
    )

    open val elytra: ArmorItem = object : ModArmorItem(data, Type.CHESTPLATE, behaviors), FabricElytraItem {}

    operator fun component5() = elytra
}