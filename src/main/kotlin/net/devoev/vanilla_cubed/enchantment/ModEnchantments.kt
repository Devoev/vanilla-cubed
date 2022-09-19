package net.devoev.vanilla_cubed.enchantment

import net.devoev.vanilla_cubed.util.RegistryManager
import net.minecraft.enchantment.Enchantment
import net.minecraft.util.registry.Registry

object ModEnchantments : RegistryManager<Enchantment>(Registry.ENCHANTMENT) {

    val FORCE = create("hurling", HurlingEnchantment)
}