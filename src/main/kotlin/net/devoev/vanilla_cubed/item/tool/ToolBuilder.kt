package net.devoev.vanilla_cubed.item.tool

import net.minecraft.entity.attribute.EntityAttribute
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.item.*
import net.minecraft.item.Item.Settings

/**
 * Builds all 5 [tools][ToolItem] for given parameters.
 * @param modifiers Modifiers that get applied when the tool is selected. See [AttributeToolItem].
 */
class ToolBuilder(material: ToolMaterial, settings: Settings,
                  attackDamageAmounts: List<Float> = BASE_ATTACK_DAMAGE,
                  attackSpeedAmounts: List<Float> = BASE_ATTACK_SPEED,
                  modifiers: Map<EntityAttribute, EntityAttributeModifier>? = null) {

    val sword: SwordItem = ModSwordItem(material, attackDamageAmounts[0].toInt(), attackSpeedAmounts[0], settings, modifiers)
    val shovel: ShovelItem = ModShovelItem(material, attackDamageAmounts[1], attackSpeedAmounts[1], settings, modifiers)
    val pickaxe: PickaxeItem = ModPickaxeItem(material, attackDamageAmounts[2].toInt(), attackSpeedAmounts[2], settings, modifiers)
    val axe: AxeItem = ModAxeItem(material, attackDamageAmounts[3], attackSpeedAmounts[3], settings, modifiers)
    val hoe: HoeItem = ModHoeItem(material, attackDamageAmounts[4].toInt(), attackSpeedAmounts[4], settings, modifiers)

    companion object {

        /**
         * The base attack damage values for sword, shovel, pickaxe, axe and hoe.
         */
        val BASE_ATTACK_DAMAGE: List<Float> = listOf(3F, 1.5F, 1F, 5F, -3F)

        /**
         * The base attack speed values for sword, shovel, pickaxe, axe and hoe.
         */
        val BASE_ATTACK_SPEED: List<Float> = listOf(-2.4F, -3F, -2.8F, -3F, 0F)
    }
}