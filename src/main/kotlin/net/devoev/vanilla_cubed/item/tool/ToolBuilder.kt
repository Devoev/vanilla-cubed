package net.devoev.vanilla_cubed.item.tool

import net.minecraft.item.*
import net.minecraft.item.Item.Settings

/**
 * Builds all 5 [tools][ToolItem] for the given [data].
 */
open class ToolBuilder(protected val data: ToolData) {

    constructor(
        material: ToolMaterial,
        attackDamageAmounts: List<Float> = ToolData.BASE_ATTACK_DAMAGE,
        attackSpeedAmounts: List<Float> = ToolData.BASE_ATTACK_SPEED,
        settings: Settings
    ) : this(ToolData(material, attackDamageAmounts, attackSpeedAmounts, settings))

    open val sword: SwordItem get() = ModSwordItem(data)
    open val shovel: ShovelItem get() = ModShovelItem(data)
    open val pickaxe: PickaxeItem get() = ModPickaxeItem(data)
    open val axe: AxeItem get() = ModAxeItem(data)
    open val hoe: HoeItem get() = ModHoeItem(data)

    operator fun component1() = sword
    operator fun component2() = shovel
    operator fun component3() = pickaxe
    operator fun component4() = axe
    operator fun component5() = hoe
}