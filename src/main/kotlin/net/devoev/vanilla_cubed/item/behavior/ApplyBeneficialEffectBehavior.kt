package net.devoev.vanilla_cubed.item.behavior

import net.devoev.vanilla_cubed.entity.effect.StatusEffectHelper
import net.devoev.vanilla_cubed.item.armor.ModArmor
import net.devoev.vanilla_cubed.util.wearsFullArmor
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.item.ArmorItem
import kotlin.random.Random

/**
 * Applies a random beneficial [StatusEffectInstance] to the player wearing full armor
 * with the given [probability] each tick.
 * @see ModArmor.ANCIENT_GOLD
 */
class ApplyBeneficialEffectBehavior(private val probability: Double,
                                    private val durationRange: IntRange,
                                    private val amplifierRange: IntRange) : InventoryTickBehavior<ArmorItem> {

    private val waitTimeMap = mutableMapOf<LivingEntity, Int>()

    /**
     * The time in ticks that needs to be waited, until another effect can be applied.
     */
    var LivingEntity.waitTime: Int
        get() = waitTimeMap[this] ?: 0
        set(value) = waitTimeMap.set(this, value)

    override fun apply(item: ArmorItem, params: InventoryTickBehavior.Params) {
        val (stack,_,entity,_,_) = params

        if (entity !is LivingEntity) return

        if (entity.waitTime > 0) {
            entity.waitTime--
            return
        }

        if (!entity.wearsFullArmor(item.material) || !entity.armorItems.contains(stack) || Random.nextDouble() >= probability) return

        val effect = StatusEffectHelper.randomBeneficial(durationRange, amplifierRange)
        if (effect.effectType.isInstant) return

        entity.waitTime = effect.duration * 3
        entity.addStatusEffect(effect)
    }
}