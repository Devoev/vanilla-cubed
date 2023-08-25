package net.devoev.vanilla_cubed.entity.effect

import net.devoev.vanilla_cubed.util.RegistryManager
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.util.registry.Registry

object ModStatusEffects : RegistryManager<StatusEffect>(Registry.STATUS_EFFECT) {

    // TODO: Add potions for both effects
    // TODO: Add magnetic status effect
    val REACH = create("reach", reachStatusEffect)
    val NOURISHMENT = create("nourishment", NourishmentStatusEffect())
}