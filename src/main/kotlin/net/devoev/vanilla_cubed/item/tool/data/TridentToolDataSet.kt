package net.devoev.vanilla_cubed.item.tool.data

import net.devoev.vanilla_cubed.item.copy
import net.minecraft.item.Item
import net.minecraft.item.Item.Settings
import net.minecraft.item.ToolMaterial
import net.minecraft.item.TridentItem

/**
 * A [ToolDataSet] with data for the creation of a [TridentItem].
 */
interface TridentToolDataSet : ToolDataSet {
    val trident: ToolData<Number?, Number?>

    companion object {

        /**
         * Creates a [TridentToolDataSet].
         */
        fun of(material: ToolMaterial,
               attackDamageAmounts: List<Float> = ToolDataSet.BASE_ATTACK_DAMAGE,
               attackSpeedAmounts: List<Float> = ToolDataSet.BASE_ATTACK_SPEED,
               settings: Settings): TridentToolDataSet
            = TridentToolDataImpl(material, attackDamageAmounts, attackSpeedAmounts, settings)

        /**
         * Creates the [Item.Settings] for the trident, by adjusting the [Item.Settings.maxDamage] variable.
         * Calculated using durability*0.16.
         */
        fun tridentSettings(settings: Settings, durability: Int): Settings
            = settings.copy().maxDamageIfAbsent((durability * 0.16).toInt())
            //= settings.maxDamageIfAbsent(durability)
    }

    data class TridentToolDataImpl(
        val material: ToolMaterial,
        val attackDamageAmounts: List<Float>,
        val attackSpeedAmounts: List<Float>,
        val settings: Settings
    ) : TridentToolDataSet, ToolDataSet by ToolDataSet.of(material, attackDamageAmounts, attackSpeedAmounts, settings) {

        override val trident: ToolData<Number?, Number?>
                = ToolData(material, null, null, tridentSettings(settings, material.durability))
    }
}