package net.devoev.vanilla_cubed.item.armor

import net.devoev.vanilla_cubed.item.behavior.InventoryTickBehavior
import net.devoev.vanilla_cubed.item.behavior.PostHitBehavior
import net.minecraft.entity.Entity
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ArmorItem
import net.minecraft.item.ArmorMaterial
import net.minecraft.item.ItemStack
import net.minecraft.world.World

class ModArmorItem(material: ArmorMaterial, slot: EquipmentSlot, settings: Settings,
                   private val inventoryTickBehavior: InventoryTickBehavior<ArmorItem>,
                   private val postHitBehavior: PostHitBehavior<ArmorItem>)
    : ArmorItem(material, slot, settings) {

    override fun inventoryTick(stack: ItemStack?, world: World?, entity: Entity?, slot: Int, selected: Boolean) {
        inventoryTickBehavior.inventoryTick(this, stack, world, entity, slot, selected)
        super.inventoryTick(stack, world, entity, slot, selected)
    }

    override fun postHit(stack: ItemStack?, target: LivingEntity?, attacker: LivingEntity?): Boolean {
        postHitBehavior.postHit(this, stack, target, attacker)
        return super.postHit(stack, target, attacker)
    }
}