package net.devoev.vanilla_cubed.item.tool

import net.devoev.vanilla_cubed.item.behavior.InventoryTickBehavior
import net.devoev.vanilla_cubed.item.behavior.ItemBehaviors
import net.devoev.vanilla_cubed.item.behavior.PostHitBehavior
import net.minecraft.item.*
import net.minecraft.item.Item.Settings

/**
 * Builds all 5 [tools][ToolItem] for the given [data].
 */
open class ToolBuilder(protected val data: ToolData, protected val behaviors: ItemBehaviors<ToolItem>) {

    constructor(
        material: ToolMaterial,
        attackDamageAmounts: List<Float> = ToolData.BASE_ATTACK_DAMAGE,
        attackSpeedAmounts: List<Float> = ToolData.BASE_ATTACK_SPEED,
        settings: Settings,
        inventoryTickBehavior: InventoryTickBehavior<ToolItem> = InventoryTickBehavior.DEFAULT,
        postHitBehavior: PostHitBehavior<ToolItem> = PostHitBehavior.DEFAULT,
    ) : this(ToolData(material, attackDamageAmounts, attackSpeedAmounts, settings),
        ItemBehaviors(inventoryTickBehavior, postHitBehavior))

    open val sword: SwordItem get() = ModSwordItem(data, behaviors)
    open val shovel: ShovelItem get() = ModShovelItem(data, behaviors)
    open val pickaxe: PickaxeItem get() = ModPickaxeItem(data, behaviors)
    open val axe: AxeItem get() = ModAxeItem(data, behaviors)
    open val hoe: HoeItem get() = ModHoeItem(data, behaviors)

    operator fun component1() = sword
    operator fun component2() = shovel
    operator fun component3() = pickaxe
    operator fun component4() = axe
    operator fun component5() = hoe
}