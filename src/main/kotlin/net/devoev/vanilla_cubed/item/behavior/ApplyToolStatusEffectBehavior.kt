package net.devoev.vanilla_cubed.item.behavior

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.item.Item

/**
 * Applies the [effect] to the player holding the item.
 */
class ApplyToolStatusEffectBehavior(private val effect: StatusEffect,
                                    private val duration: Int = 0,
                                    private val amplifier: Int = 0) : InventoryTickBehavior<Item> {

    override fun accept(item: Item, params: InventoryTickParams) {
        if (params.world!!.isClient) return
        val (stack,_,entity,_,selected) = params

        if (entity !is LivingEntity) return
        if (selected && entity.offHandStack != stack) {
            entity.addStatusEffect(StatusEffectInstance(effect, duration, amplifier))
        }
    }
}