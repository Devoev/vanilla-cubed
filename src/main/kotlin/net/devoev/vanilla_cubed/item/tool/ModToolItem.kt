package net.devoev.vanilla_cubed.item.tool

import net.devoev.vanilla_cubed.item.behavior.Behaviors
import net.devoev.vanilla_cubed.item.behavior.InventoryTickBehavior
import net.devoev.vanilla_cubed.item.behavior.PostHitBehavior
import net.devoev.vanilla_cubed.item.behavior.PostMineBehavior
import net.minecraft.block.BlockState
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.item.*
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World


open class ModSwordItem(data: ToolData, behaviors: Behaviors<ToolItem>)
    : SwordItem(data.material, data.swordData.attackDamage, data.swordData.attackSpeed, data.settings),
    Behaviors<ToolItem> by behaviors {

    override fun inventoryTick(stack: ItemStack?, world: World?, entity: Entity?, slot: Int, selected: Boolean) {
        inventoryTickBehavior(this, InventoryTickBehavior.Params(stack, world, entity, slot, selected))
        super.inventoryTick(stack, world, entity, slot, selected)
    }

    override fun postHit(stack: ItemStack?, target: LivingEntity?, attacker: LivingEntity?): Boolean {
        postHitBehavior(this, PostHitBehavior.Params(stack, target, attacker))
        return super.postHit(stack, target, attacker)
    }

    override fun postMine(stack: ItemStack?, world: World?, state: BlockState?, pos: BlockPos?, miner: LivingEntity?): Boolean {
        postMineBehavior(this, PostMineBehavior.Params(stack, world, state, pos, miner))
        return super.postMine(stack, world, state, pos, miner)
    }
}

open class ModShovelItem(data: ToolData, behaviors: Behaviors<ToolItem>)
    : ShovelItem(data.material, data.shovelData.attackDamage, data.shovelData.attackSpeed, data.settings),
    Behaviors<ToolItem> by behaviors {

    override fun inventoryTick(stack: ItemStack?, world: World?, entity: Entity?, slot: Int, selected: Boolean) {
        inventoryTickBehavior(this, InventoryTickBehavior.Params(stack, world, entity, slot, selected))
        super.inventoryTick(stack, world, entity, slot, selected)
    }

    override fun postHit(stack: ItemStack?, target: LivingEntity?, attacker: LivingEntity?): Boolean {
        postHitBehavior(this, PostHitBehavior.Params(stack, target, attacker))
        return super.postHit(stack, target, attacker)
    }

    override fun postMine(stack: ItemStack?, world: World?, state: BlockState?, pos: BlockPos?, miner: LivingEntity?): Boolean {
        postMineBehavior(this, PostMineBehavior.Params(stack, world, state, pos, miner))
        return super.postMine(stack, world, state, pos, miner)
    }
}

open class ModPickaxeItem(data: ToolData, behaviors: Behaviors<ToolItem>)
    : PickaxeItem(data.material, data.pickaxeData.attackDamage, data.pickaxeData.attackSpeed, data.settings),
    Behaviors<ToolItem> by behaviors {

    override fun inventoryTick(stack: ItemStack?, world: World?, entity: Entity?, slot: Int, selected: Boolean) {
        inventoryTickBehavior(this, InventoryTickBehavior.Params(stack, world, entity, slot, selected))
        super.inventoryTick(stack, world, entity, slot, selected)
    }

    override fun postHit(stack: ItemStack?, target: LivingEntity?, attacker: LivingEntity?): Boolean {
        postHitBehavior(this, PostHitBehavior.Params(stack, target, attacker))
        return super.postHit(stack, target, attacker)
    }

    override fun postMine(stack: ItemStack?, world: World?, state: BlockState?, pos: BlockPos?, miner: LivingEntity?): Boolean {
        postMineBehavior(this, PostMineBehavior.Params(stack, world, state, pos, miner))
        return super.postMine(stack, world, state, pos, miner)
    }
}

open class ModAxeItem(data: ToolData, behaviors: Behaviors<ToolItem>)
    : AxeItem(data.material, data.axeData.attackDamage, data.axeData.attackSpeed, data.settings),
    Behaviors<ToolItem> by behaviors {

    override fun inventoryTick(stack: ItemStack?, world: World?, entity: Entity?, slot: Int, selected: Boolean) {
        inventoryTickBehavior(this, InventoryTickBehavior.Params(stack, world, entity, slot, selected))
        super.inventoryTick(stack, world, entity, slot, selected)
    }

    override fun postHit(stack: ItemStack?, target: LivingEntity?, attacker: LivingEntity?): Boolean {
        postHitBehavior(this, PostHitBehavior.Params(stack, target, attacker))
        return super.postHit(stack, target, attacker)
    }

    override fun postMine(stack: ItemStack?, world: World?, state: BlockState?, pos: BlockPos?, miner: LivingEntity?): Boolean {
        postMineBehavior(this, PostMineBehavior.Params(stack, world, state, pos, miner))
        return super.postMine(stack, world, state, pos, miner)
    }
}

open class ModHoeItem(data: ToolData, behaviors: Behaviors<ToolItem>)
    : HoeItem(data.material, data.hoeData.attackDamage, data.hoeData.attackSpeed, data.settings),
    Behaviors<ToolItem> by behaviors {

    override fun inventoryTick(stack: ItemStack?, world: World?, entity: Entity?, slot: Int, selected: Boolean) {
        inventoryTickBehavior(this, InventoryTickBehavior.Params(stack, world, entity, slot, selected))
        super.inventoryTick(stack, world, entity, slot, selected)
    }

    override fun postHit(stack: ItemStack?, target: LivingEntity?, attacker: LivingEntity?): Boolean {
        postHitBehavior(this, PostHitBehavior.Params(stack, target, attacker))
        return super.postHit(stack, target, attacker)
    }

    override fun postMine(stack: ItemStack?, world: World?, state: BlockState?, pos: BlockPos?, miner: LivingEntity?): Boolean {
        postMineBehavior(this, PostMineBehavior.Params(stack, world, state, pos, miner))
        return super.postMine(stack, world, state, pos, miner)
    }
}