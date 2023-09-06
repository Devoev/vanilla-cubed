package net.devoev.vanilla_cubed.item.armor

import net.devoev.vanilla_cubed.item.behavior.Behaviors
import net.devoev.vanilla_cubed.item.behavior.InventoryTickParams
import net.devoev.vanilla_cubed.item.behavior.PostHitParams
import net.devoev.vanilla_cubed.item.behavior.PostMineParams
import net.minecraft.block.BlockState
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ArmorItem
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

open class ModArmorItem(data: ArmorData, type: Type, behaviors: Behaviors<ArmorItem>)
    : ArmorItem(data.material, type, data.settings), Behaviors<ArmorItem> by behaviors {

    override fun inventoryTick(stack: ItemStack?, world: World?, entity: Entity?, slot: Int, selected: Boolean) {
        inventoryTickBehavior(this, InventoryTickParams(stack, world, entity, slot, selected))
        super.inventoryTick(stack, world, entity, slot, selected)
    }

    override fun postHit(stack: ItemStack?, target: LivingEntity?, attacker: LivingEntity?): Boolean {
        postHitBehavior(this, PostHitParams(stack, target, attacker))
        return super.postHit(stack, target, attacker)
    }

    override fun postMine(stack: ItemStack?, world: World?, state: BlockState?, pos: BlockPos?, miner: LivingEntity?): Boolean {
        postMineBehavior(this, PostMineParams(stack, world, state, pos, miner))
        return super.postMine(stack, world, state, pos, miner)
    }
}