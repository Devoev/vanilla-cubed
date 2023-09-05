package net.devoev.vanilla_cubed.entity.effect

import net.devoev.vanilla_cubed.util.RegistryManager
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.registry.Registries

object ModStatusEffects : RegistryManager<StatusEffect>(Registries.STATUS_EFFECT) {

    // TODO: Add potions for both effects
    // TODO: Add magnetic status effect
    val REACH = create("reach", reachStatusEffect)
    val NOURISHMENT = create("nourishment", NourishmentStatusEffect())
}