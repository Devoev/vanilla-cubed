package net.devoev.vanilla_cubed.potion

import net.devoev.vanilla_cubed.entity.effect.ModStatusEffects
import net.devoev.vanilla_cubed.util.RegistryManager
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.potion.Potion
import net.minecraft.registry.Registries

object ModPotions : RegistryManager<Potion>(Registries.POTION) {

    val MAGNETIC = create("magnetism", ModStatusEffects.MAGNETIC, 3600)
    val LONG_MAGNETIC = create("long_magnetism", ModStatusEffects.MAGNETIC, 9600)
    val STRONG_MAGNETIC = create("strong_magnetism", ModStatusEffects.MAGNETIC, 1800, 1)

    /**
     * Creates a new [Potion] of the given [effect], [duration] and [amplifier] and adds it to the registry.
     */
    private fun create(name: String, effect: StatusEffect, duration: Int, amplifier: Int = 0)
        = create(name, Potion(StatusEffectInstance(effect, duration, amplifier)))
}