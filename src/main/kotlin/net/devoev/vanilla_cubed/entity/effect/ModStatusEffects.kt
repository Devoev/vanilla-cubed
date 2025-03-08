package net.devoev.vanilla_cubed.entity.effect

import net.devoev.vanilla_cubed.util.RegistryManager
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.registry.Registries

/**
 * All modded status effects.
 */
@Suppress("unused")
object ModStatusEffects : RegistryManager<StatusEffect>(Registries.STATUS_EFFECT) {

    val REACH = create("reach", ReachStatusEffect)
    val NOURISHMENT = create("nourishment", NourishmentStatusEffect())
    val MAGNETIC = create("magnetic", DefaultMagneticStatusEffect)
}