package net.devoev.vanilla_cubed.item.tool

import net.devoev.vanilla_cubed.item.behavior.*
import net.minecraft.item.*
import net.minecraft.item.Item.Settings

/**
 * Builds all 5 [tools][ToolItem] for the given [data].
 */
open class ToolBuilder(private val data: ToolData, private val behaviors: Behaviors<ToolItem>) {

    constructor(
        material: ToolMaterial,
        attackDamageAmounts: List<Float> = ToolData.BASE_ATTACK_DAMAGE,
        attackSpeedAmounts: List<Float> = ToolData.BASE_ATTACK_SPEED,
        settings: Settings,
        inventoryTickBehavior: InventoryTickBehavior<ToolItem> = INVENTORY_TICK_DEFAULT,
        postHitBehavior: PostHitBehavior<ToolItem> = POST_HIT_DEFAULT,
        postMineBehavior: PostMineBehavior<ToolItem> = POST_MINE_DEFAULT,
    ) : this(ToolData(material, attackDamageAmounts, attackSpeedAmounts, settings),
        DataBehaviors(inventoryTickBehavior, postHitBehavior, postMineBehavior)
    )

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