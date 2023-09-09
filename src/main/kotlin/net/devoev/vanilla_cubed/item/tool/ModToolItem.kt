package net.devoev.vanilla_cubed.item.tool

import net.devoev.vanilla_cubed.item.modifier.ItemModifiers
import net.devoev.vanilla_cubed.item.tool.data.ToolData
import net.minecraft.block.BlockState
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.item.*
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World


open class ModSwordItem(data: ToolData<Int, Float>, private val modifiers: ItemModifiers<ToolItem>)
    : SwordItem(data.material, data.attackDamage, data.attackSpeed, data.settings) {

    override fun inventoryTick(stack: ItemStack, world: World, entity: Entity, slot: Int, selected: Boolean) {
        modifiers.inventoryTick(this, stack, world, entity, slot, selected)
        super.inventoryTick(stack, world, entity, slot, selected)
    }

    override fun postHit(stack: ItemStack, target: LivingEntity, attacker: LivingEntity): Boolean {
        modifiers.postHit(this, stack, target, attacker)
        return super.postHit(stack, target, attacker)
    }

    override fun postMine(stack: ItemStack, world: World, state: BlockState, pos: BlockPos, miner: LivingEntity): Boolean {
        modifiers.postMine(this, stack, world, state, pos, miner)
        return super.postMine(stack, world, state, pos, miner)
    }
}

open class ModShovelItem(data: ToolData<Float, Float>, private val modifiers: ItemModifiers<ToolItem>)
    : ShovelItem(data.material, data.attackDamage, data.attackSpeed, data.settings) {

    override fun inventoryTick(stack: ItemStack, world: World, entity: Entity, slot: Int, selected: Boolean) {
        modifiers.inventoryTick(this, stack, world, entity, slot, selected)
        super.inventoryTick(stack, world, entity, slot, selected)
    }

    override fun postHit(stack: ItemStack, target: LivingEntity, attacker: LivingEntity): Boolean {
        modifiers.postHit(this, stack, target, attacker)
        return super.postHit(stack, target, attacker)
    }

    override fun postMine(stack: ItemStack, world: World, state: BlockState, pos: BlockPos, miner: LivingEntity): Boolean {
        modifiers.postMine(this, stack, world, state, pos, miner)
        return super.postMine(stack, world, state, pos, miner)
    }
}

open class ModPickaxeItem(data: ToolData<Int, Float>, private val modifiers: ItemModifiers<ToolItem>)
    : PickaxeItem(data.material, data.attackDamage, data.attackSpeed, data.settings) {

    override fun inventoryTick(stack: ItemStack, world: World, entity: Entity, slot: Int, selected: Boolean) {
        modifiers.inventoryTick(this, stack, world, entity, slot, selected)
        super.inventoryTick(stack, world, entity, slot, selected)
    }

    override fun postHit(stack: ItemStack, target: LivingEntity, attacker: LivingEntity): Boolean {
        modifiers.postHit(this, stack, target, attacker)
        return super.postHit(stack, target, attacker)
    }

    override fun postMine(stack: ItemStack, world: World, state: BlockState, pos: BlockPos, miner: LivingEntity): Boolean {
        modifiers.postMine(this, stack, world, state, pos, miner)
        return super.postMine(stack, world, state, pos, miner)
    }
}

open class ModAxeItem(data: ToolData<Float, Float>, private val modifiers: ItemModifiers<ToolItem>)
    : AxeItem(data.material, data.attackDamage, data.attackSpeed, data.settings) {

    override fun inventoryTick(stack: ItemStack, world: World, entity: Entity, slot: Int, selected: Boolean) {
        modifiers.inventoryTick(this, stack, world, entity, slot, selected)
        super.inventoryTick(stack, world, entity, slot, selected)
    }

    override fun postHit(stack: ItemStack, target: LivingEntity, attacker: LivingEntity): Boolean {
        modifiers.postHit(this, stack, target, attacker)
        return super.postHit(stack, target, attacker)
    }

    override fun postMine(stack: ItemStack, world: World, state: BlockState, pos: BlockPos, miner: LivingEntity): Boolean {
        modifiers.postMine(this, stack, world, state, pos, miner)
        return super.postMine(stack, world, state, pos, miner)
    }
}

open class ModHoeItem(data: ToolData<Int, Float>, private val modifiers: ItemModifiers<ToolItem>)
    : HoeItem(data.material, data.attackDamage, data.attackSpeed, data.settings) {

    override fun inventoryTick(stack: ItemStack, world: World, entity: Entity, slot: Int, selected: Boolean) {
        modifiers.inventoryTick(this, stack, world, entity, slot, selected)
        super.inventoryTick(stack, world, entity, slot, selected)
    }

    override fun postHit(stack: ItemStack, target: LivingEntity, attacker: LivingEntity): Boolean {
        modifiers.postHit(this, stack, target, attacker)
        return super.postHit(stack, target, attacker)
    }

    override fun postMine(stack: ItemStack, world: World, state: BlockState, pos: BlockPos, miner: LivingEntity): Boolean {
        modifiers.postMine(this, stack, world, state, pos, miner)
        return super.postMine(stack, world, state, pos, miner)
    }
}