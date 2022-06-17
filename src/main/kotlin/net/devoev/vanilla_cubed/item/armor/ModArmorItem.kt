package net.devoev.vanilla_cubed.item.armor

import net.devoev.vanilla_cubed.item.behavior.Behaviors
import net.minecraft.entity.Entity
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ArmorItem
import net.minecraft.item.ItemStack
import net.minecraft.world.World

open class ModArmorItem(data: ArmorData, slot: EquipmentSlot, behaviors: Behaviors<ArmorItem>)
    : ArmorItem(data.material, slot, data.settings), Behaviors<ArmorItem> by behaviors {

    override fun inventoryTick(stack: ItemStack?, world: World?, entity: Entity?, slot: Int, selected: Boolean) {
        inventoryTick(this, stack, world, entity, slot, selected)
        super.inventoryTick(stack, world, entity, slot, selected)
    }

    override fun postHit(stack: ItemStack?, target: LivingEntity?, attacker: LivingEntity?): Boolean {
        return postHit(this, stack, target, attacker) or super.postHit(stack, target, attacker)
    }
}