package net.devoev.vanilla_cubed.item.armor

import net.devoev.vanilla_cubed.item.modifier.HarmfulEffectProtectionBehavior
import net.devoev.vanilla_cubed.item.modifier.MiningBonusBehavior
import net.minecraft.item.ArmorItem

/**
 * All used [modded armor items][ArmorItem] built using an [ArmorBuilder].
 */
object ModArmor {

    val ANCIENT_GOLD = ArmorBuilder(ModArmorMaterials.ANCIENT_GOLD,
        inventoryTickModifier = HarmfulEffectProtectionBehavior
    )
    val AMETHYST = ArmorBuilder(ModArmorMaterials.AMETHYST,
        inventoryTickModifier = MiningBonusBehavior
    )
    val ENDERITE = ArmorBuilder(ModArmorMaterials.ENDERITE)
    val DRAGON_SCALE = ElytraArmorBuilder(ModArmorMaterials.DRAGON_SCALE)
}