package net.devoev.vanilla_cubed.item.tool

import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes
import net.devoev.vanilla_cubed.entity.projectile.EnderiteTridentEntity
import net.devoev.vanilla_cubed.item.ModItemGroup
import net.devoev.vanilla_cubed.item.behavior.ApplyAttributeBehavior
import net.devoev.vanilla_cubed.item.behavior.ApplyHarmfulEffectBehavior
import net.devoev.vanilla_cubed.item.behavior.DetectOresBehavior
import net.devoev.vanilla_cubed.item.behavior.NoGravityBehavior
import net.devoev.vanilla_cubed.item.toSettings
import net.devoev.vanilla_cubed.item.tool.data.ToolDataSet.Companion.BASE_ATTACK_SPEED
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.item.ToolItem

/**
 * All modded [tool items][ToolItem] built using a [ToolBuilder].
 */
object ModTools {

    val ANCIENT_GOLD = ToolBuilder(
        material = ModToolMaterials.ANCIENT_GOLD,
        settings = ModItemGroup.VANILLA_CUBED.toSettings(),
        postHitBehavior = ApplyHarmfulEffectBehavior(0.3, 100..200, 0..2)
    )

    val AMETHYST = ToolBuilder(
        material = ModToolMaterials.AMETHYST, settings = ModItemGroup.VANILLA_CUBED.toSettings(),
        attackSpeedAmounts = BASE_ATTACK_SPEED.map { it + 0.4F },
        postMineBehavior = DetectOresBehavior(3, 1)
    )

    val ENDERITE = TridentToolBuilder(
        material = ModToolMaterials.ENDERITE,
        settings = ModItemGroup.VANILLA_CUBED.toSettings(),
        postHitBehavior = NoGravityBehavior,
        entityProvider = ::EnderiteTridentEntity)

    val DRAGON_SCALE = ToolBuilder(
        material = ModToolMaterials.DRAGON_SCALE,
        settings = ModItemGroup.VANILLA_CUBED.toSettings(),
        inventoryTickBehavior = ApplyAttributeBehavior(
            ReachEntityAttributes.REACH,
            EntityAttributeModifier("modifier", 1.5, EntityAttributeModifier.Operation.ADDITION)
        )
    )
}