package net.devoev.vanilla_cubed.entity.effect

import net.devoev.vanilla_cubed.util.RegistryManager
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.util.registry.Registry

object ModStatusEffects : RegistryManager<StatusEffect>(Registry.STATUS_EFFECT) {

    val REACH = create("reach", reachStatusEffect)
}