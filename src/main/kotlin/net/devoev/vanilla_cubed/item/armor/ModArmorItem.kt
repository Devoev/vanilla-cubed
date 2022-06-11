package net.devoev.vanilla_cubed.item.armor

import net.devoev.vanilla_cubed.item.behavior.ItemBehaviors
import net.minecraft.entity.Entity
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ArmorItem
import net.minecraft.item.ItemStack
import net.minecraft.world.World

open class ModArmorItem(data: ArmorData, slot: EquipmentSlot, private val behaviors: ItemBehaviors<ArmorItem>)
    : ArmorItem(data.material, slot, data.settings) {

    override fun inventoryTick(stack: ItemStack?, world: World?, entity: Entity?, slot: Int, selected: Boolean) {
        behaviors.inventoryTickBehavior.inventoryTick(this, stack, world, entity, slot, selected)
        super.inventoryTick(stack, world, entity, slot, selected)
    }

    override fun postHit(stack: ItemStack?, target: LivingEntity?, attacker: LivingEntity?): Boolean {
        return behaviors.postHitBehavior.postHit(this, stack, target, attacker) or
                super.postHit(stack, target, attacker)
    }
}