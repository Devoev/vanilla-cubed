package net.devoev.vanilla_cubed.entity.projectile

import net.devoev.vanilla_cubed.entity.ModEntityTypes
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.world.World

/**
 * A trident made out of amethyst. A thrown amethyst trident breaks the block it hits on impact.
 */
class AmethystTridentEntity(world: World, owner: LivingEntity, stack: ItemStack)
    : ModTridentEntity(world, owner, stack, ModEntityTypes.AMETHYST_TRIDENT) {

    private var blockHit: Boolean = false

    override fun onBlockHit(blockHitResult: BlockHitResult) {
        super.onBlockHit(blockHitResult)

        val pos = blockHitResult.blockPos
        val blockState = world.getBlockState(pos)
        val block = blockState.block

        if (blockHit || block.hardness > 5 || block.hardness < 0) return

        (owner as? PlayerEntity)?.let { owner ->
            block.onBreak(world, pos, blockState, owner)
            if (world.removeBlock(pos, false)) {
                block.onBroken(world, pos, blockState)
                block.afterBreak(world, owner, pos, blockState, world.getBlockEntity(pos), itemStack.copy())
            }

            // Post mine
            if (!world.isClient && blockState.getHardness(world, pos) != 0.0f) {
                itemStack.damage(1, owner) { it.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND) }
            }

            blockHit = true
        }
    }
}