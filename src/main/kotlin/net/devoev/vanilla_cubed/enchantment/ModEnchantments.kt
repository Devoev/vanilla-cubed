package net.devoev.vanilla_cubed.enchantment

import net.devoev.vanilla_cubed.util.RegistryManager
import net.minecraft.enchantment.Enchantment
import net.minecraft.registry.Registries

/**
 * All modded enchantments.
 */
@Suppress("unused")
object ModEnchantments : RegistryManager<Enchantment>(Registries.ENCHANTMENT) {

    val HURLING = create("hurling", HurlingEnchantment)
}