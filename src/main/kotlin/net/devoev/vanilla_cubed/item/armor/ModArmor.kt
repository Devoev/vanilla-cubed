package net.devoev.vanilla_cubed.item.armor

import net.devoev.vanilla_cubed.item.ModItemGroup
import net.devoev.vanilla_cubed.item.behavior.InventoryTickBehavior
import net.devoev.vanilla_cubed.item.behavior.ItemBehaviors
import net.devoev.vanilla_cubed.item.toSettings
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.item.ArmorItem

/**
 * All used [modded armor items][ArmorItem] built using an [ArmorBuilder].
 */
object ModArmor {

    val ANCIENT_GOLD = ArmorBuilder(ModArmorMaterials.ANCIENT_GOLD, ModItemGroup.COMBAT.toSettings(),
        ItemBehaviors(inventoryTickBehavior = InventoryTickBehavior.APPLY_BENEFICIAL_EFFECT)
    )
    val AMETHYST = ArmorBuilder(ModArmorMaterials.AMETHYST, ModItemGroup.COMBAT.toSettings(),
        ItemBehaviors(inventoryTickBehavior = InventoryTickBehavior.buildApplyEffect(StatusEffects.HASTE))
    )
    val ENDERITE = ArmorBuilder(ModArmorMaterials.ENDERITE, ModItemGroup.COMBAT.toSettings(), ItemBehaviors())
    val DRAGON_SCALE = ArmorBuilder(ModArmorMaterials.DRAGON_SCALE, ModItemGroup.COMBAT.toSettings(), ItemBehaviors())
}