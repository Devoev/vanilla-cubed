package net.devoev.vanilla_cubed.entity.effect

import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.effect.StatusEffectCategory

/**
 * Increases the [reach distance][ReachEntityAttributes.REACH] attribute by `1` per level.
 */
val reachStatusEffect = attributeStatusEffectOf(
    StatusEffectCategory.BENEFICIAL,
    0xEF6E35,
    ReachEntityAttributes.REACH,
    1.0,
    EntityAttributeModifier.Operation.ADDITION
)