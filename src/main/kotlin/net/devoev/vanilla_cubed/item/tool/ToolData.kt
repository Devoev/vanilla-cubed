package net.devoev.vanilla_cubed.item.tool

import net.minecraft.item.Item.Settings
import net.minecraft.item.ToolItem
import net.minecraft.item.ToolMaterial

/**
 * Data needed for the creation of a set of [tools][ToolItem].
 */
data class ToolData(
    val material: ToolMaterial,
    val attackDamageAmounts: List<Float> = BASE_ATTACK_DAMAGE,
    val attackSpeedAmounts: List<Float> = BASE_ATTACK_SPEED,
    val settings: Settings
) {
    val swordData = SingleToolData(material, attackDamageAmounts[0].toInt(), attackSpeedAmounts[0], settings)
    val shovelData = SingleToolData(material, attackDamageAmounts[1], attackSpeedAmounts[1], settings)
    val pickaxeData = SingleToolData(material, attackDamageAmounts[2].toInt(), attackSpeedAmounts[2], settings)
    val axeData = SingleToolData(material, attackDamageAmounts[3], attackSpeedAmounts[3], settings)
    val hoeData = SingleToolData(material, attackDamageAmounts[4].toInt(), attackSpeedAmounts[4], settings)

    /**
     * Data needed for the creation of a single [ToolItem].
     */
    data class SingleToolData<T1 : Number, T2 : Number>(
        val material: ToolMaterial,
        val attackDamage: T1,
        val attackSpeed: T2,
        val settings: Settings
    )

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
