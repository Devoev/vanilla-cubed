package net.devoev.vanilla_cubed.item.armor

import net.devoev.vanilla_cubed.item.modifier.*
import net.devoev.vanilla_cubed.item.modifierTextOf
import net.devoev.vanilla_cubed.item.whenFullArmorTextOf
import net.devoev.vanilla_cubed.item.whenUndergroundTextOf
import net.minecraft.item.ArmorItem

/**
 * All used [modded armor items][ArmorItem].
 */
object ModArmor {

    val ANCIENT_GOLD = buildArmor(
        material = ModArmorMaterials.ANCIENT_GOLD,
        modifiers = listOf(HarmfulEffectProtectionModifier),
        whenFullArmorTextOf(false, HARMFUL_EFFECT_PROTECTION_TEXT),
        modifierTextOf(true, TREASURE_ENCHANTING_TEXT)
    )
    val AMETHYST = buildArmor(
        material = ModArmorMaterials.AMETHYST,
        modifiers = listOf(MiningBonusModifier),
        whenUndergroundTextOf(false, *MINING_BONUS_TEXTS)
    )
    val ENDERITE = buildArmor(
        material = ModArmorMaterials.ENDERITE,
        modifiers = emptyList(),
        whenFullArmorTextOf(false, PROJECTILE_SHIELD_TEXT),
        modifierTextOf(true, SOULBOUND_TEXT)
    )
    val DRAGON_SCALE = buildElytraArmor(
        material = ModArmorMaterials.DRAGON_SCALE,
        modifiers = emptyList(),
        whenFullArmorTextOf(false, DRAGON_FLIGHT_TEXT)
    )
}