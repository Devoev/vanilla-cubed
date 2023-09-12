package net.devoev.vanilla_cubed.item.tool

import net.devoev.vanilla_cubed.item.modifier.ItemModifiers
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.*
import net.minecraft.text.Text

/**
 * Collection of all 5 tool items and a trident.
 */
data class TridentToolItems(
    val sword: SwordItem,
    val shovel: ShovelItem,
    val pickaxe: PickaxeItem,
    val axe: AxeItem,
    val hoe: HoeItem,
    val trident: TridentItem
)

/**
 * Builds all 5 tools and a trident for the given parameters.
 */
fun buildTridentTools(
    material: ToolMaterial,
    attackDamageAmounts: List<Float> = BASE_ATTACK_DAMAGE,
    attackSpeedAmounts: List<Float> = BASE_ATTACK_SPEED,
    settings: Item.Settings = FabricItemSettings(),
    entityProvider: EntityProvider,
    modifiers: ItemModifiers<Item> = emptyList(),
    tooltips: Collection<List<Text>> = emptyList()
): TridentToolItems {
    val tools = buildTools(material, attackDamageAmounts, attackSpeedAmounts, settings, modifiers, tooltips)
    val tridentData = TridentToolData(material, settings, entityProvider, modifiers).withDurability(material.durability)
    return TridentToolItems(
        tools.sword,
        tools.shovel,
        tools.pickaxe,
        tools.axe,
        tools.hoe,
        ModTridentItem(tridentData)
    )
}