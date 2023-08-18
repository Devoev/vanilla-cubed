package net.devoev.vanilla_cubed.item.behavior

import net.devoev.vanilla_cubed.item.armor.ModArmor
import net.devoev.vanilla_cubed.util.wearsFullArmor
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.item.ArmorItem

/**
 * An [InventoryTickBehavior] that applies the given [effect], when the entity is wearing a full set of armor.
 * @see ModArmor.AMETHYST
 */
class ApplyArmorStatusEffectBehavior(private val effect: StatusEffect,
                                     private val duration: Int = 0,
                                     private val amplifier: Int = 0) : InventoryTickBehavior<ArmorItem> {

    override fun accept(item: ArmorItem, params: InventoryTickParams) {
        if (params.world!!.isClient) return
        val entity = params.entity

        if (entity is LivingEntity && entity.wearsFullArmor(item.material))
            entity.addStatusEffect(StatusEffectInstance(effect, duration, amplifier))
    }
}