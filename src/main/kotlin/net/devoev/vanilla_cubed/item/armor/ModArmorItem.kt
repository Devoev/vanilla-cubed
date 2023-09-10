package net.devoev.vanilla_cubed.item.armor

import net.devoev.vanilla_cubed.item.modifier.inventoryTick
import net.devoev.vanilla_cubed.item.modifier.postHit
import net.devoev.vanilla_cubed.item.modifier.postMine
import net.minecraft.block.BlockState
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ArmorItem
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

open class ModArmorItem(private val data: ArmorData, type: Type)
    : ArmorItem(data.material, type, data.settings) {

    override fun inventoryTick(stack: ItemStack, world: World, entity: Entity, slot: Int, selected: Boolean) {
        data.modifiers.inventoryTick(this, stack, world, entity, slot, selected)
        super.inventoryTick(stack, world, entity, slot, selected)
    }

    override fun postHit(stack: ItemStack, target: LivingEntity, attacker: LivingEntity): Boolean {
        data.modifiers.postHit(this, stack, target, attacker)
        return super.postHit(stack, target, attacker)
    }

    override fun postMine(stack: ItemStack, world: World, state: BlockState, pos: BlockPos, miner: LivingEntity): Boolean {
        data.modifiers.postMine(this, stack, world, state, pos, miner)
        return super.postMine(stack, world, state, pos, miner)
    }
}