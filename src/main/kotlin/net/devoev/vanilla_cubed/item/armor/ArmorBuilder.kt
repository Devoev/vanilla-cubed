package net.devoev.vanilla_cubed.item.armor

import net.devoev.vanilla_cubed.item.behavior.*
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.ArmorItem
import net.minecraft.item.ArmorMaterial
import net.minecraft.item.Item.Settings

/**
 * Builds all 4 armor pieces for given [data].
 * Applies [behaviors] only to the helmet.
 */
open class ArmorBuilder(
    protected val data: ArmorData,
    protected val behaviors: Behaviors<ArmorItem>) {

    constructor(
        material: ArmorMaterial,
        settings: Settings = FabricItemSettings(),
        inventoryTickBehavior: InventoryTickBehavior<ArmorItem> = INVENTORY_TICK_DEFAULT,
        postHitBehavior: PostHitBehavior<ArmorItem> = POST_HIT_DEFAULT,
        postMineBehavior: PostMineBehavior<ArmorItem> = POST_MINE_DEFAULT
    ) : this(ArmorData(material, settings),
        DataBehaviors(inventoryTickBehavior, postHitBehavior, postMineBehavior))

    open val helmet: ArmorItem get() = buildArmorItem(ArmorItem.Type.HELMET)
    open val chestplate: ArmorItem get() = buildArmorItem(ArmorItem.Type.CHESTPLATE)
    open val leggings: ArmorItem get() = buildArmorItem(ArmorItem.Type.LEGGINGS)
    open val boots: ArmorItem get() = buildArmorItem(ArmorItem.Type.BOOTS)

    protected open fun buildArmorItem(type: ArmorItem.Type) =
        ModArmorItem(data, type, if (type == ArmorItem.Type.HELMET) behaviors else DataBehaviors())

    operator fun component1() = helmet
    operator fun component2() = chestplate
    operator fun component3() = leggings
    operator fun component4() = boots
}