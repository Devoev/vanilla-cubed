package net.devoev.vanilla_cubed.item.modifier

import net.devoev.vanilla_cubed.entity.wearsFullArmor
import net.devoev.vanilla_cubed.item.armor.ModArmor
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.item.ArmorItem

/**
 * A [ItemModifier] that applies the given [effect] to the player wearing a full set of armor.
 * @see ModArmor.AMETHYST
 */
fun armorStatusEffectModifierOf(effect: StatusEffect, duration: Int = 0, amplifier: Int = 0)
    = InventoryTickModifier<ArmorItem> { _, world, entity, _, _ ->

    if (!world.isClient && entity is LivingEntity && entity.wearsFullArmor(material))
        entity.addStatusEffect(StatusEffectInstance(effect, duration, amplifier))
}