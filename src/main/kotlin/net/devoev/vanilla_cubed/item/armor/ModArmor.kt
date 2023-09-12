package net.devoev.vanilla_cubed.item.armor

import net.devoev.vanilla_cubed.item.modifier.*
import net.minecraft.item.ArmorItem

/**
 * All used [modded armor items][ArmorItem].
 */
object ModArmor {

    val ANCIENT_GOLD = buildArmor(
        ModArmorMaterials.ANCIENT_GOLD,
        listOf(HarmfulEffectProtectionModifier),
        whenFullArmorTextOf(HARMFUL_EFFECT_PROTECTION_TEXT),
        modifierTextOf(TREASURE_ENCHANTING_TEXT)
    )
    val AMETHYST = buildArmor(ModArmorMaterials.AMETHYST, listOf(MiningBonusModifier))
    val ENDERITE = buildArmor(ModArmorMaterials.ENDERITE)
    val DRAGON_SCALE = buildElytraArmor(ModArmorMaterials.DRAGON_SCALE)
}