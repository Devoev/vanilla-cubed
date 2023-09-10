package net.devoev.vanilla_cubed.item.armor

import net.devoev.vanilla_cubed.item.modifier.HarmfulEffectProtectionBehavior
import net.devoev.vanilla_cubed.item.modifier.MiningBonusModifier
import net.minecraft.item.ArmorItem

/**
 * All used [modded armor items][ArmorItem].
 */
object ModArmor {

    val ANCIENT_GOLD = buildArmor(ModArmorMaterials.ANCIENT_GOLD, HarmfulEffectProtectionBehavior)
    val AMETHYST = buildArmor(ModArmorMaterials.AMETHYST, MiningBonusModifier)
    val ENDERITE = buildArmor(ModArmorMaterials.ENDERITE)
    val DRAGON_SCALE = buildElytraArmor(ModArmorMaterials.DRAGON_SCALE)
}