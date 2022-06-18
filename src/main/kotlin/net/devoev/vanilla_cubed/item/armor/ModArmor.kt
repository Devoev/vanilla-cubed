package net.devoev.vanilla_cubed.item.armor

import net.devoev.vanilla_cubed.item.ModItemGroup
import net.devoev.vanilla_cubed.item.behavior.ApplyBeneficialEffectBehavior
import net.devoev.vanilla_cubed.item.behavior.ApplyEffectBehavior
import net.devoev.vanilla_cubed.item.toSettings
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.item.ArmorItem

/**
 * All used [modded armor items][ArmorItem] built using an [ArmorBuilder].
 */
object ModArmor {

    val ANCIENT_GOLD = ArmorBuilder(ModArmorMaterials.ANCIENT_GOLD, ModItemGroup.COMBAT.toSettings(),
        inventoryTickBehavior = ApplyBeneficialEffectBehavior(5e-4, 1500..3000, 1..2),
        onlyOne = true
    )
    val AMETHYST = ArmorBuilder(ModArmorMaterials.AMETHYST, ModItemGroup.COMBAT.toSettings(),
        inventoryTickBehavior = ApplyEffectBehavior(StatusEffects.HASTE)
    )
    val ENDERITE = ArmorBuilder(ModArmorMaterials.ENDERITE, ModItemGroup.COMBAT.toSettings())
    val DRAGON_SCALE = ElytraArmorBuilder(ModArmorMaterials.DRAGON_SCALE, ModItemGroup.COMBAT.toSettings())
}