package net.devoev.vanilla_cubed.util

import net.minecraft.item.*
import net.minecraft.item.Item.Settings

class ToolBuilder(material: ToolMaterial, settings: Settings, attackDamageAmounts: List<Float> = BASE_ATTACK_DAMAGE, attackSpeedAmounts: List<Float> = BASE_ATTACK_SPEED) {

    val sword: SwordItem = SwordItem(material, attackDamageAmounts[0].toInt(), attackSpeedAmounts[0], settings)
    val shovel: ShovelItem = ShovelItem(material, attackDamageAmounts[1], attackSpeedAmounts[1], settings)
    val pickaxe: PickaxeItem = ModPickaxe(material, attackDamageAmounts[2].toInt(), attackSpeedAmounts[2], settings)
    val axe: AxeItem = ModAxe(material, attackDamageAmounts[3], attackSpeedAmounts[3], settings)
    val hoe: HoeItem = ModHoe(material, attackDamageAmounts[4].toInt(), attackSpeedAmounts[4], settings)

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

    class ModPickaxe(material: ToolMaterial, attackDamage: Int, attackSpeed: Float, settings: Settings) : PickaxeItem(material, attackDamage, attackSpeed, settings)

    class ModAxe(material: ToolMaterial, attackDamage: Float, attackSpeed: Float, settings: Settings) : AxeItem(material, attackDamage, attackSpeed, settings)

    class ModHoe(material: ToolMaterial, attackDamage: Int, attackSpeed: Float, settings: Settings) : HoeItem(material, attackDamage, attackSpeed, settings)
}