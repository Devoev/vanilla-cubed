package net.devoev.vanilla_cubed.item.tool

import net.devoev.vanilla_cubed.entity.effect.ModStatusEffects
import net.devoev.vanilla_cubed.entity.projectile.EnderiteTridentEntity
import net.devoev.vanilla_cubed.item.modifier.*
import net.minecraft.item.ToolItem

/**
 * All modded [tool items][ToolItem] built using a [ToolBuilder].
 */
object ModTools {

    val ANCIENT_GOLD = buildTools(
        material = ModToolMaterials.ANCIENT_GOLD,
        modifiers = listOf(ApplyHarmfulEffectItem(0.3, 100..200, 0..2))
    )

    val AMETHYST = buildTools(
        material = ModToolMaterials.AMETHYST,
        attackSpeedAmounts = BASE_ATTACK_SPEED.map { it + 0.4F },
        modifiers = listOf(DetectOresItem(3, 1))
    )

    val ENDERITE = buildTridentTools(
        material = ModToolMaterials.ENDERITE,
        entityProvider = ::EnderiteTridentEntity,
        modifiers = listOf(NoGravityBehavior)
    )

    val DRAGON_SCALE = buildTools(
        material = ModToolMaterials.DRAGON_SCALE,
        modifiers = listOf(toolStatusEffectModifierOf(ModStatusEffects.REACH, amplifier = 1))
    )
}