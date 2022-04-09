package net.devoev.vanilla_cubed.item.tool

import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes
import net.devoev.vanilla_cubed.item.ModItemGroup
import net.devoev.vanilla_cubed.item.toSettings
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.item.ToolItem

/**
 * All used [modded tool items][ToolItem] build using an [ToolBuilder].
 */
object ModTools {

    val ANCIENT_GOLD = ToolBuilder(ModToolMaterials.ANCIENT_GOLD, ModItemGroup.TOOLS.toSettings())
    val AMETHYST = ToolBuilder(
        ModToolMaterials.AMETHYST, ModItemGroup.TOOLS.toSettings(),
        attackSpeedAmounts = ToolBuilder.BASE_ATTACK_SPEED.map { it + 0.4F })
    val ENDERITE = ToolBuilder(
        ModToolMaterials.ENDERITE, ModItemGroup.TOOLS.toSettings(),
        modifiers = mapOf(ReachEntityAttributes.REACH to EntityAttributeModifier("modifier", 1.5, EntityAttributeModifier.Operation.ADDITION)))
    val DRAGON_SCALE = ToolBuilder(ModToolMaterials.DRAGON_SCALE, ModItemGroup.TOOLS.toSettings())
}