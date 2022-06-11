package net.devoev.vanilla_cubed.item.behavior

import net.devoev.vanilla_cubed.entity.effect.StatusEffectHelper
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
import net.devoev.vanilla_cubed.item.armor.ModArmor
import net.devoev.vanilla_cubed.item.tool.AttributeToolItem
import net.minecraft.entity.attribute.EntityAttribute
import net.minecraft.entity.attribute.EntityAttributeInstance
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.item.ToolItem

fun interface InventoryTickBehavior<in T : Item> {

    operator fun invoke(item: T, stack: ItemStack?, world: World?, entity: Entity?, slot: Int, selected: Boolean)

    companion object {

        val DEFAULT = InventoryTickBehavior<Item> { _, _, _, _, _, _ ->  }

        /**
         * Applies a random beneficial [StatusEffectInstance] to the player wearing full armor once in a time.
         * @see ModArmor.ANCIENT_GOLD
         * TODO: Fix waitTicks bug
         */
        val APPLY_BENEFICIAL_EFFECT = object : InventoryTickBehavior<ArmorItem> {

            /**
             * The time in ticks that needs to be waited, until another effect can be applied.
             */
            private var waitTicks = 0

            override fun invoke(item: ArmorItem, stack: ItemStack?, world: World?, entity: Entity?, slot: Int, selected: Boolean) {
                if (entity !is LivingEntity) return

                if (waitTicks > 0) {
                    waitTicks--
                    return
                }

                val chance = 3e-3
                if (!entity.wearsFullArmor(item.material) || !entity.armorItems.contains(stack) || Random.nextDouble() >= chance) return

                val effect = StatusEffectHelper.randomBeneficial(1500..3000, 1..2)
                if (effect.effectType.isInstant) return

                waitTicks = effect.duration * 3
                entity.addStatusEffect(effect)
            }

        }

        /**
         * Builds an [InventoryTickBehavior] that applies the given [effect],
         * when the entity is wearing a full set of armor.
         * @see ModArmor.AMETHYST
         */
        fun buildApplyEffect(effect: StatusEffect) = InventoryTickBehavior<ArmorItem> { item, stack, world, entity, slot, selected ->
            if (entity is LivingEntity && entity.wearsFullArmor(item.material))
                entity.addStatusEffect(StatusEffectInstance(effect))
        }

        /**
         * Builds an [InventoryTickBehavior] that applies the given [modifier],
         * when the entity has the item selected.
         * @see ModArmor.DRAGON_SCALE
         * TODO: Fix bug when holding 2 tools
         */
        fun buildApplyAttribute(attribute: EntityAttribute, modifier: EntityAttributeModifier) = object : InventoryTickBehavior<ToolItem> {

            override fun invoke(item: ToolItem, stack: ItemStack?, world: World?, entity: Entity?, slot: Int, selected: Boolean) {
                if (entity !is LivingEntity) return
                val sel = selected && entity.offHandStack != stack

                val attributeInstance: EntityAttributeInstance = entity.getAttributeInstance(attribute) ?: return

                if (sel && !attributeInstance.hasModifier(modifier)) {
                    attributeInstance.addTemporaryModifier(modifier)
                } else if (!sel && attributeInstance.hasModifier(modifier) && !selectedSameModifier(entity, attribute)) {
                    attributeInstance.removeModifier(modifier)
                }
            }

            /**
             * Returns whether the given [entity] has a [AttributeToolItem] selected, that changes the [attribute] value.
             */
            private fun selectedSameModifier(entity: LivingEntity, attribute: EntityAttribute): Boolean {
                val item = entity.getStackInHand(entity.activeHand).item
                return item is AttributeToolItem && item.modifiers?.contains(attribute) ?: false
            }
        }
    }
}