package net.devoev.vanilla_cubed.item.tool

import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes
import net.devoev.vanilla_cubed.item.ModItemGroup
import net.devoev.vanilla_cubed.item.toSettings
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.item.ToolItem

/**
 * All modded [tool items][ToolItem] built using a [ToolBuilder].
 */
object ModTools {

    val ANCIENT_GOLD = AncientGoldTools
    val AMETHYST = ToolBuilder(
        material = ModToolMaterials.AMETHYST, settings = ModItemGroup.TOOLS.toSettings(),
        attackSpeedAmounts = ToolData.BASE_ATTACK_SPEED.map { it + 0.4F })
    val ENDERITE = ToolBuilder(material = ModToolMaterials.ENDERITE, settings = ModItemGroup.TOOLS.toSettings())
    val DRAGON_SCALE = DragonScaleTools
}