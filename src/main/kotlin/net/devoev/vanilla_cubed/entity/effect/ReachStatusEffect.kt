package net.devoev.vanilla_cubed.entity.effect

import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectCategory
import net.minecraft.util.math.MathHelper

object ReachStatusEffect : StatusEffect(StatusEffectCategory.BENEFICIAL, 0xEF6E35) {

    init {
        addAttributeModifier(ReachEntityAttributes.REACH, MathHelper.randomUuid().toString(), 0.2, EntityAttributeModifier.Operation.ADDITION)
    }

}