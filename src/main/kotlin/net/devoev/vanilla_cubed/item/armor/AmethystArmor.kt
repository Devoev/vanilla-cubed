package net.devoev.vanilla_cubed.item.armor

import net.devoev.vanilla_cubed.item.ModItemGroup
import net.devoev.vanilla_cubed.item.toSettings
import net.devoev.vanilla_cubed.util.wearsFullArmor
import net.minecraft.entity.Entity
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ArmorItem
import net.minecraft.item.ItemStack
import net.minecraft.world.World

/**
* Armor made out of [ModArmorMaterials.AMETHYST]. When wearing the full set of armor,
 * the user gets the [StatusEffects.HASTE] effect.
 * TODO: Different full armor effect
 */
object AmethystArmor : ArmorBuilder(ModArmorMaterials.AMETHYST, ModItemGroup.COMBAT.toSettings()) {

    override fun buildArmorItem(slot: EquipmentSlot) = object : ArmorItem(material, slot, settings) {

        override fun inventoryTick(stack: ItemStack?, world: World?, entity: Entity?, slot: Int, selected: Boolean) {
            if (entity is PlayerEntity && entity.wearsFullArmor(material))
                entity.addStatusEffect(StatusEffectInstance(StatusEffects.HASTE))
            super.inventoryTick(stack, world, entity, slot, selected)
        }
    }
}