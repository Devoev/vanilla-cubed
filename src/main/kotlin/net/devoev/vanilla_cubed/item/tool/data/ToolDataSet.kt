package net.devoev.vanilla_cubed.item.tool.data

import net.minecraft.item.Item
import net.minecraft.item.ToolMaterial

/**
 * A set of [ToolData] for the creation of the vanilla tools (sword, shovel, pickaxe, axe, hoe).
 */
interface ToolDataSet {
    val sword: ToolData<Int, Float>
    val shovel: ToolData<Float, Float>
    val pickaxe: ToolData<Int, Float>
    val axe: ToolData<Float, Float>
    val hoe: ToolData<Int, Float>

    companion object {

        /**
         * Creates a [ToolDataSet].
         */
        fun of(material: ToolMaterial,
               attackDamageAmounts: List<Float> = BASE_ATTACK_DAMAGE,
               attackSpeedAmounts: List<Float> = BASE_ATTACK_SPEED,
               settings: Item.Settings): ToolDataSet
            = ToolDataImpl(material, attackDamageAmounts, attackSpeedAmounts, settings)

        /**
         * The base attack damage values for sword, shovel, pickaxe, axe and hoe.
         */
        val BASE_ATTACK_DAMAGE: List<Float> = listOf(3F, 1.5F, 1F, 5F, -3F)

        /**
         * The base attack speed values for sword, shovel, pickaxe, axe and hoe.
         */
        val BASE_ATTACK_SPEED: List<Float> = listOf(-2.4F, -3F, -2.8F, -3F, 0F)
    }

    data class ToolDataImpl(
        val material: ToolMaterial,
        val attackDamageAmounts: List<Float>,
        val attackSpeedAmounts: List<Float>,
        val settings: Item.Settings
    ) : ToolDataSet {

        override val sword: ToolData<Int, Float>
                = ToolData(material, attackDamageAmounts[0].toInt(), attackSpeedAmounts[0], settings)
        override val shovel: ToolData<Float, Float>
                = ToolData(material, attackDamageAmounts[1], attackSpeedAmounts[1], settings)
        override val pickaxe: ToolData<Int, Float>
                = ToolData(material, attackDamageAmounts[2].toInt(), attackSpeedAmounts[2], settings)
        override val axe: ToolData<Float, Float>
                = ToolData(material, attackDamageAmounts[3], attackSpeedAmounts[3], settings)
        override val hoe: ToolData<Int, Float>
                = ToolData(material, attackDamageAmounts[4].toInt(), attackSpeedAmounts[4], settings)
    }
}