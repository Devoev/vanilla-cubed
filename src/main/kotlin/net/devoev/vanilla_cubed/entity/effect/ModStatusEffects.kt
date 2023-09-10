package net.devoev.vanilla_cubed.entity.effect

import net.devoev.vanilla_cubed.util.RegistryManager
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.registry.Registries

object ModStatusEffects : RegistryManager<StatusEffect>(Registries.STATUS_EFFECT) {

    // TODO: Add potions
    val REACH = create("reach", ReachStatusEffect)
    val NOURISHMENT = create("nourishment", NourishmentStatusEffect())
    val MAGNETIC = create("magnetic", DefaultMagneticStatusEffect)
}