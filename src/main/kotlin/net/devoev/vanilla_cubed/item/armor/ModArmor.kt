package net.devoev.vanilla_cubed.item.armor

import net.devoev.vanilla_cubed.item.ModItemGroup
import net.devoev.vanilla_cubed.item.behavior.HarmfulEffectProtectionBehavior
import net.devoev.vanilla_cubed.item.behavior.MiningBonusBehavior
import net.devoev.vanilla_cubed.item.toSettings
import net.minecraft.item.ArmorItem

/**
 * All used [modded armor items][ArmorItem] built using an [ArmorBuilder].
 */
object ModArmor {

    val ANCIENT_GOLD = ArmorBuilder(ModArmorMaterials.ANCIENT_GOLD, ModItemGroup.VANILLA_CUBED.toSettings(),
        inventoryTickBehavior = HarmfulEffectProtectionBehavior
    )
    val AMETHYST = ArmorBuilder(ModArmorMaterials.AMETHYST, ModItemGroup.VANILLA_CUBED.toSettings(),
        inventoryTickBehavior = MiningBonusBehavior
    )
    val ENDERITE = ArmorBuilder(ModArmorMaterials.ENDERITE, ModItemGroup.VANILLA_CUBED.toSettings())
    val DRAGON_SCALE = ElytraArmorBuilder(ModArmorMaterials.DRAGON_SCALE, ModItemGroup.VANILLA_CUBED.toSettings())
}