package net.devoev.vanilla_cubed.enchantment

import net.devoev.vanilla_cubed.util.RegistryManager
import net.minecraft.enchantment.Enchantment
import net.minecraft.registry.Registries

object ModEnchantments : RegistryManager<Enchantment>(Registries.ENCHANTMENT) {

    val HURLING = create("hurling", HurlingEnchantment)
}