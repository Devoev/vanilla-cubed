package net.devoev.vanilla_cubed.item.behavior

import net.devoev.vanilla_cubed.entity.effect.StatusEffectHelper
import net.devoev.vanilla_cubed.item.armor.ModArmor
import net.devoev.vanilla_cubed.util.wearsFullArmor
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.item.ArmorItem
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.world.World
import kotlin.random.Random

fun interface InventoryTickBehavior<in T : Item> {

    operator fun invoke(item: T, stack: ItemStack?, world: World?, entity: Entity?, slot: Int, selected: Boolean)

    companion object {

        val DEFAULT = InventoryTickBehavior<Item> { _, _, _, _, _, _ ->  }

        /**
         * Applies a random beneficial [StatusEffectInstance] to the player wearing full armor once in a time.
         * @see ModArmor.ANCIENT_GOLD
         */
        val APPLY_BENEFICIAL_EFFECT = object : InventoryTickBehavior<ArmorItem> {

            private val waitTimeMap = mutableMapOf<LivingEntity, Int>()

            /**
             * The time in ticks that needs to be waited, until another effect can be applied.
             */
            var LivingEntity.waitTime: Int
                get() = waitTimeMap[this] ?: 0
                set(value) = waitTimeMap.set(this, value)

            override fun invoke(item: ArmorItem, stack: ItemStack?, world: World?, entity: Entity?, slot: Int, selected: Boolean) {
                if (entity !is LivingEntity) return

                if (entity.waitTime > 0) {
                    entity.waitTime--
                    return
                }

                val chance = 5e-4
                if (!entity.wearsFullArmor(item.material) || !entity.armorItems.contains(stack) || Random.nextDouble() >= chance) return

                val effect = StatusEffectHelper.randomBeneficial(1500..3000, 1..2)
                if (effect.effectType.isInstant) return

                entity.waitTime = effect.duration * 3
                entity.addStatusEffect(effect)
            }

        }
    }
}