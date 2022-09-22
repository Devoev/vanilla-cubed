package net.devoev.vanilla_cubed.item.tool

import net.devoev.vanilla_cubed.item.behavior.*
import net.devoev.vanilla_cubed.item.tool.data.ToolDataSet
import net.devoev.vanilla_cubed.item.tool.data.ToolDataSet.Companion.BASE_ATTACK_DAMAGE
import net.devoev.vanilla_cubed.item.tool.data.ToolDataSet.Companion.BASE_ATTACK_SPEED
import net.minecraft.item.*
import net.minecraft.item.Item.Settings

/**
 * Builds all 5 [tools][ToolItem] for the given [data].
 */
open class ToolBuilder(protected val data: ToolDataSet, protected val behaviors: Behaviors<Item>) {

    constructor(
        material: ToolMaterial,
        attackDamageAmounts: List<Float> = BASE_ATTACK_DAMAGE,
        attackSpeedAmounts: List<Float> = BASE_ATTACK_SPEED,
        settings: Settings,
        inventoryTickBehavior: InventoryTickBehavior<Item> = INVENTORY_TICK_DEFAULT,
        postHitBehavior: PostHitBehavior<Item> = POST_HIT_DEFAULT,
        postMineBehavior: PostMineBehavior<Item> = POST_MINE_DEFAULT,
    ) : this(
        ToolDataSet.of(material, attackDamageAmounts, attackSpeedAmounts, settings),
        DataBehaviors(inventoryTickBehavior, postHitBehavior, postMineBehavior)
    )

    open val sword: SwordItem get() = ModSwordItem(data.sword, behaviors)
    open val shovel: ShovelItem get() = ModShovelItem(data.shovel, behaviors)
    open val pickaxe: PickaxeItem get() = ModPickaxeItem(data.pickaxe, behaviors)
    open val axe: AxeItem get() = ModAxeItem(data.axe, behaviors)
    open val hoe: HoeItem get() = ModHoeItem(data.hoe, behaviors)

    operator fun component1() = sword
    operator fun component2() = shovel
    operator fun component3() = pickaxe
    operator fun component4() = axe
    operator fun component5() = hoe
}