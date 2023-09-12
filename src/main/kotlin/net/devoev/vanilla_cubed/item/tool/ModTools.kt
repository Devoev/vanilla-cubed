package net.devoev.vanilla_cubed.item.tool

import net.devoev.vanilla_cubed.entity.effect.ModStatusEffects
import net.devoev.vanilla_cubed.entity.projectile.AmethystTridentEntity
import net.devoev.vanilla_cubed.entity.projectile.EnderiteTridentEntity
import net.devoev.vanilla_cubed.item.modifier.*
import net.minecraft.item.ToolItem

/**
 * All modded [tool items][ToolItem]..
 */
object ModTools {

    val ANCIENT_GOLD = buildTools(
        material = ModToolMaterials.ANCIENT_GOLD,
        modifiers = listOf(harmfulEffectOnHitModifierOf(0.1, 100..200, 0..2)),
        tooltips = listOf(modifierTextOf(HARMFUL_EFFECT_ON_HIT_TEXT))
    )

    val AMETHYST = buildTridentTools(
        material = ModToolMaterials.AMETHYST,
        entityProvider = ::AmethystTridentEntity,
        attackSpeedAmounts = BASE_ATTACK_SPEED.map { it + 0.4F },
        modifiers = listOf(VeinMiningModifier),
        tooltips = listOf(modifierTextOf(VEIN_MINING_TEXT))
    )

    val ENDERITE = buildTridentTools(
        material = ModToolMaterials.ENDERITE,
        entityProvider = ::EnderiteTridentEntity,
        modifiers = listOf(NoGravityModifier),
        tooltips = listOf(modifierTextOf(NO_GRAVITY_TEXT))
    )

    val DRAGON_SCALE = buildTools(
        material = ModToolMaterials.DRAGON_SCALE,
        modifiers = listOf(toolStatusEffectModifierOf(ModStatusEffects.REACH, amplifier = 1))
    )
}