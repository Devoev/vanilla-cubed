package net.devoev.vanilla_cubed.item.tool

import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes
import net.devoev.vanilla_cubed.item.ModItemGroup
import net.devoev.vanilla_cubed.item.behavior.*
import net.devoev.vanilla_cubed.item.toSettings
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.item.ToolItem

/**
 * All modded [tool items][ToolItem] built using a [ToolBuilder].
 */
object ModTools {

    val ANCIENT_GOLD = ToolBuilder(material = ModToolMaterials.ANCIENT_GOLD,
        settings = ModItemGroup.TOOLS.toSettings(),
        postHitBehavior = ApplyHarmfulEffectBehavior(0.1, 100..200, 0..2)
    )

    val AMETHYST = ToolBuilder(
        material = ModToolMaterials.AMETHYST, settings = ModItemGroup.TOOLS.toSettings(),
        attackSpeedAmounts = ToolData.BASE_ATTACK_SPEED.map { it + 0.4F },
        postMineBehavior = DetectOresBehavior(3)
    )

    val ENDERITE = ToolBuilder(material = ModToolMaterials.ENDERITE, settings = ModItemGroup.TOOLS.toSettings())

    val DRAGON_SCALE = ToolBuilder(material = ModToolMaterials.DRAGON_SCALE, settings = ModItemGroup.TOOLS.toSettings(),
        inventoryTickBehavior = ApplyAttributeBehavior(
            ReachEntityAttributes.REACH,
            EntityAttributeModifier("modifier", 1.5, EntityAttributeModifier.Operation.ADDITION)
        )
    )
}