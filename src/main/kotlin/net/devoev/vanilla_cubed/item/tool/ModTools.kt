package net.devoev.vanilla_cubed.item.tool

import net.devoev.vanilla_cubed.entity.effect.ModStatusEffects
import net.devoev.vanilla_cubed.entity.projectile.*
import net.devoev.vanilla_cubed.item.modifier.*
import net.devoev.vanilla_cubed.item.modifierTextOf
import net.devoev.vanilla_cubed.item.whenInHandTextOf
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.ToolItem
import net.minecraft.item.ToolMaterials

/**
 * All modded [tool items][ToolItem].
 */
object ModTools {

    val ANCIENT_GOLD = buildTools(
        material = ModToolMaterials.ANCIENT_GOLD,
        modifiers = listOf(harmfulEffectOnHitModifierOf(0.1, 100..200, 0..2)),
        tooltips = listOf(modifierTextOf(false, TREASURE_ENCHANTING_TEXT, HARMFUL_EFFECT_ON_HIT_TEXT))
    )

    val AMETHYST = buildTridentTools(
        material = ModToolMaterials.AMETHYST,
        entityProvider = ::AmethystTridentEntity,
        attackSpeedAmounts = BASE_ATTACK_SPEED.map { it + 0.4F },
        modifiers = listOf(VeinMiningModifier),
        tooltips = listOf(modifierTextOf(false, VEIN_MINING_TEXT)),
        tridentTooltips = listOf(modifierTextOf(false, BREAK_BLOCK_TEXT))
    )

    val ENDERITE = buildTridentTools(
        material = ModToolMaterials.ENDERITE,
        entityProvider = ::EnderiteTridentEntity,
        modifiers = listOf(NoGravityModifier),
        tooltips = listOf(modifierTextOf(false, NO_GRAVITY_TEXT, SOULBOUND_TEXT)),
        tridentTooltips = listOf(modifierTextOf(false, AIM_ASSIST_TEXT))
    )

    val DRAGON_SCALE = buildTools(
        material = ModToolMaterials.DRAGON_SCALE,
        modifiers = listOf(toolStatusEffectModifierOf(ModStatusEffects.REACH, amplifier = 1)),
        tooltips = listOf(whenInHandTextOf(false, toolStatusEffectTextOf(ModStatusEffects.REACH, "II")))
    )

    val NETHERITE_TRIDENT = ModTridentItem(
        material = ToolMaterials.NETHERITE,
        settings = FabricItemSettings().maxDamage(324).fireproof(),
        entityProvider = ::NetheriteTridentEntity,
        tooltips = listOf(modifierTextOf(false, BURN_RESISTANT_TEXT), modifierTextOf(true, ATTRACT_ITEMS_TEXT))
    )
}