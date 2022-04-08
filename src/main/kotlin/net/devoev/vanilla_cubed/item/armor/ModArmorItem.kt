package net.devoev.vanilla_cubed.item.armor

import net.minecraft.entity.Entity
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.item.ArmorItem
import net.minecraft.item.ArmorMaterial
import net.minecraft.item.ItemStack
import net.minecraft.world.World

class ModArmorItem(material: ArmorMaterial, slot: EquipmentSlot, settings: Settings,
                   override val statusEffect: StatusEffectInstance? = null)
    : ArmorItem(material, slot, settings), StatusEffectArmorItem {

    override fun inventoryTick(stack: ItemStack, world: World, entity: Entity, slot: Int, selected: Boolean) {
        super<StatusEffectArmorItem>.inventoryTick(world, entity, material)
    }
}