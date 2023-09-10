package net.devoev.vanilla_cubed.item.tool

import net.devoev.vanilla_cubed.entity.effect.ModStatusEffects
import net.devoev.vanilla_cubed.entity.projectile.EnderiteTridentEntity
import net.devoev.vanilla_cubed.item.modifier.NoGravityModifier
import net.devoev.vanilla_cubed.item.modifier.detectOresModifierOf
import net.devoev.vanilla_cubed.item.modifier.harmfulEffectOnHitModifierOf
import net.devoev.vanilla_cubed.item.modifier.toolStatusEffectModifierOf
import net.minecraft.item.ToolItem

/**
 * All modded [tool items][ToolItem]..
 */
object ModTools {

    val ANCIENT_GOLD = buildTools(
        material = ModToolMaterials.ANCIENT_GOLD,
        modifiers = listOf(harmfulEffectOnHitModifierOf(0.3, 100..200, 0..2))
    )

    val AMETHYST = buildTools(
        material = ModToolMaterials.AMETHYST,
        attackSpeedAmounts = BASE_ATTACK_SPEED.map { it + 0.4F },
        modifiers = listOf(detectOresModifierOf(3, 1))
    )

    val ENDERITE = buildTridentTools(
        material = ModToolMaterials.ENDERITE,
        entityProvider = ::EnderiteTridentEntity,
        modifiers = listOf(NoGravityModifier)
    )

    val DRAGON_SCALE = buildTools(
        material = ModToolMaterials.DRAGON_SCALE,
        modifiers = listOf(toolStatusEffectModifierOf(ModStatusEffects.REACH, amplifier = 1))
    )
}