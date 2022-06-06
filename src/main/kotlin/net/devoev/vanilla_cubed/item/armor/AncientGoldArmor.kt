package net.devoev.vanilla_cubed.item.armor

import net.devoev.vanilla_cubed.entity.effect.StatusEffectHelper
import net.devoev.vanilla_cubed.item.ModItemGroup
import net.devoev.vanilla_cubed.item.toSettings
import net.devoev.vanilla_cubed.util.wearsFullArmor
import net.minecraft.entity.Entity
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ArmorItem
import net.minecraft.item.ItemStack
import net.minecraft.world.World
import kotlin.random.Random

object AncientGoldArmor : ArmorBuilder(ModArmorMaterials.ANCIENT_GOLD, ModItemGroup.COMBAT.toSettings()) {

    override val helmet = object : ArmorItem(material, EquipmentSlot.HEAD, settings) {

        /**
         * The time in ticks that needs to be waited, until another effect can be applied.
         */
        private var waitTicks = 0

        /**
         * Applies a random beneficial [StatusEffectInstance] to the player wearing full armor once in a time.
         */
        override fun inventoryTick(stack: ItemStack?, world: World?, entity: Entity?, slot: Int, selected: Boolean) {
            if (entity !is PlayerEntity) return

            if (waitTicks > 0) {
                waitTicks--
                return
            }

            val chance = 3e-3
            if (!entity.wearsFullArmor(material) || !entity.armorItems.contains(stack) || Random.nextDouble() >= chance) return

            val effect = StatusEffectHelper.randomBeneficial(1500..3000, 1..2)
            if (effect.effectType.isInstant) return

            waitTicks = effect.duration * 3
            entity.addStatusEffect(effect)
        }
    }
}