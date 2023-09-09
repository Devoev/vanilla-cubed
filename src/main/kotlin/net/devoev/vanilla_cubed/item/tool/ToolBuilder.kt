package net.devoev.vanilla_cubed.item.tool

import net.devoev.vanilla_cubed.item.modifier.*
import net.devoev.vanilla_cubed.item.tool.data.ToolDataSet
import net.devoev.vanilla_cubed.item.tool.data.ToolDataSet.Companion.BASE_ATTACK_DAMAGE
import net.devoev.vanilla_cubed.item.tool.data.ToolDataSet.Companion.BASE_ATTACK_SPEED
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.*
import net.minecraft.item.Item.Settings

/**
 * Builds all 5 [tools][ToolItem] for the given [data].
 */
open class ToolBuilder(protected val data: ToolDataSet, protected val itemModifiers: ItemModifiers<Item>) {

    constructor(
        material: ToolMaterial,
        attackDamageAmounts: List<Float> = BASE_ATTACK_DAMAGE,
        attackSpeedAmounts: List<Float> = BASE_ATTACK_SPEED,
        settings: Settings = FabricItemSettings(),
        inventoryTickModifier: InventoryTickModifier<Item> = INVENTORY_TICK_DEFAULT,
        postHitModifier: PostHitModifier<Item> = POST_HIT_DEFAULT,
        postMineModifier: PostMineModifier<Item> = POST_MINE_DEFAULT,
    ) : this(
        ToolDataSet.of(material, attackDamageAmounts, attackSpeedAmounts, settings),
        DataBehaviors(inventoryTickModifier, postHitModifier, postMineModifier)
    )

    open val sword: SwordItem get() = ModSwordItem(data.sword, itemModifiers)
    open val shovel: ShovelItem get() = ModShovelItem(data.shovel, itemModifiers)
    open val pickaxe: PickaxeItem get() = ModPickaxeItem(data.pickaxe, itemModifiers)
    open val axe: AxeItem get() = ModAxeItem(data.axe, itemModifiers)
    open val hoe: HoeItem get() = ModHoeItem(data.hoe, itemModifiers)

    operator fun component1() = sword
    operator fun component2() = shovel
    operator fun component3() = pickaxe
    operator fun component4() = axe
    operator fun component5() = hoe
}