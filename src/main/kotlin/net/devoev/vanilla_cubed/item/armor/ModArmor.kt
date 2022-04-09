package net.devoev.vanilla_cubed.item.armor

import net.devoev.vanilla_cubed.item.ModItemGroup
import net.devoev.vanilla_cubed.item.toSettings
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.item.ArmorItem

/**
 * All used [modded armor items][ArmorItem] build using an [ArmorBuilder].
 */
object ModArmor {

    val ANCIENT_GOLD = ArmorBuilder(ModArmorMaterials.ANCIENT_GOLD, ModItemGroup.COMBAT.toSettings(), StatusEffectInstance(StatusEffects.LUCK))
    val AMETHYST = ArmorBuilder(ModArmorMaterials.AMETHYST, ModItemGroup.COMBAT.toSettings(), StatusEffectInstance(StatusEffects.HASTE))
    val ENDERITE = ArmorBuilder(ModArmorMaterials.ENDERITE, ModItemGroup.COMBAT.toSettings())
    val DRAGON_SCALE = ArmorBuilder(ModArmorMaterials.DRAGON_SCALE, ModItemGroup.COMBAT.toSettings())
}