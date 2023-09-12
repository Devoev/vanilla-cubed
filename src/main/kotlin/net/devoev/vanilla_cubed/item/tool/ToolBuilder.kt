package net.devoev.vanilla_cubed.item.tool

import net.devoev.vanilla_cubed.item.modifier.ItemModifiers
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.*
import net.minecraft.item.Item.Settings
import net.minecraft.text.Text

/**
 * Collection of all 5 tool items.
 */
data class ToolItems(
    val sword: SwordItem,
    val shovel: ShovelItem,
    val pickaxe: PickaxeItem,
    val axe: AxeItem,
    val hoe: HoeItem
)

/**
 * Builds all 5 tools for the given parameters.
 */
fun buildTools(
    material: ToolMaterial,
    attackDamageAmounts: List<Float> = BASE_ATTACK_DAMAGE,
    attackSpeedAmounts: List<Float> = BASE_ATTACK_SPEED,
    settings: Settings = FabricItemSettings(),
    modifiers: ItemModifiers<Item> = emptyList(),
    tooltips: Collection<List<Text>> = emptyList()
): ToolItems {
    val data = toolDataOf(material, attackDamageAmounts, attackSpeedAmounts, settings, modifiers, tooltips.flatten())
    return ToolItems(
        ModSwordItem(data[0]),
        ModShovelItem(data[1]),
        ModPickaxeItem(data[2]),
        ModAxeItem(data[3]),
        ModHoeItem(data[4])
    )
}