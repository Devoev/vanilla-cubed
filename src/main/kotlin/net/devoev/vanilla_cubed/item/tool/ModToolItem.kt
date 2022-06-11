package net.devoev.vanilla_cubed.item.tool

import net.devoev.vanilla_cubed.item.behavior.ItemBehaviors
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.item.*
import net.minecraft.world.World


open class ModSwordItem(data: ToolData, private val behaviors: ItemBehaviors<ToolItem>)
    : SwordItem(data.material, data.swordData.attackDamage, data.swordData.attackSpeed, data.settings) {

    override fun inventoryTick(stack: ItemStack?, world: World?, entity: Entity?, slot: Int, selected: Boolean) {
        behaviors.inventoryTickBehavior(this, stack, world, entity, slot, selected)
        super.inventoryTick(stack, world, entity, slot, selected)
    }

    override fun postHit(stack: ItemStack?, target: LivingEntity?, attacker: LivingEntity?): Boolean {
        behaviors.postHitBehavior(this, stack, target, attacker)
        return super.postHit(stack, target, attacker)
    }
}

open class ModShovelItem(data: ToolData, private val behaviors: ItemBehaviors<ToolItem>)
    : ShovelItem(data.material, data.shovelData.attackDamage, data.shovelData.attackSpeed, data.settings) {

    override fun inventoryTick(stack: ItemStack?, world: World?, entity: Entity?, slot: Int, selected: Boolean) {
        behaviors.inventoryTickBehavior(this, stack, world, entity, slot, selected)
        super.inventoryTick(stack, world, entity, slot, selected)
    }

    override fun postHit(stack: ItemStack?, target: LivingEntity?, attacker: LivingEntity?): Boolean {
        behaviors.postHitBehavior(this, stack, target, attacker)
        return super.postHit(stack, target, attacker)
    }
}

open class ModPickaxeItem(data: ToolData, private val behaviors: ItemBehaviors<ToolItem>)
    : PickaxeItem(data.material, data.pickaxeData.attackDamage, data.pickaxeData.attackSpeed, data.settings) {

    override fun inventoryTick(stack: ItemStack?, world: World?, entity: Entity?, slot: Int, selected: Boolean) {
        behaviors.inventoryTickBehavior(this, stack, world, entity, slot, selected)
        super.inventoryTick(stack, world, entity, slot, selected)
    }

    override fun postHit(stack: ItemStack?, target: LivingEntity?, attacker: LivingEntity?): Boolean {
        behaviors.postHitBehavior(this, stack, target, attacker)
        return super.postHit(stack, target, attacker)
    }
}

open class ModAxeItem(data: ToolData, private val behaviors: ItemBehaviors<ToolItem>)
    : AxeItem(data.material, data.axeData.attackDamage, data.axeData.attackSpeed, data.settings) {

    override fun inventoryTick(stack: ItemStack?, world: World?, entity: Entity?, slot: Int, selected: Boolean) {
        behaviors.inventoryTickBehavior(this, stack, world, entity, slot, selected)
        super.inventoryTick(stack, world, entity, slot, selected)
    }

    override fun postHit(stack: ItemStack?, target: LivingEntity?, attacker: LivingEntity?): Boolean {
        behaviors.postHitBehavior(this, stack, target, attacker)
        return super.postHit(stack, target, attacker)
    }
}

open class ModHoeItem(data: ToolData, private val behaviors: ItemBehaviors<ToolItem>)
    : HoeItem(data.material, data.hoeData.attackDamage, data.hoeData.attackSpeed, data.settings) {

    override fun inventoryTick(stack: ItemStack?, world: World?, entity: Entity?, slot: Int, selected: Boolean) {
        behaviors.inventoryTickBehavior(this, stack, world, entity, slot, selected)
        super.inventoryTick(stack, world, entity, slot, selected)
    }

    override fun postHit(stack: ItemStack?, target: LivingEntity?, attacker: LivingEntity?): Boolean {
        behaviors.postHitBehavior(this, stack, target, attacker)
        return super.postHit(stack, target, attacker)
    }
}