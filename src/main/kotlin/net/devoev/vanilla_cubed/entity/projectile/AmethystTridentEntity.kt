package net.devoev.vanilla_cubed.entity.projectile

import net.devoev.vanilla_cubed.entity.ModEntityTypes
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemStack
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.world.World

/**
 * A trident made out of amethyst. A thrown amethyst trident breaks the block it hits on impact.
 */
class AmethystTridentEntity(world: World, owner: LivingEntity, stack: ItemStack)
    : ModTridentEntity(world, owner, stack, ModEntityTypes.AMETHYST_TRIDENT) {

    override fun onBlockHit(blockHitResult: BlockHitResult) {
        super.onBlockHit(blockHitResult)
        // TODO: Update implementation
        world.breakBlock(blockHitResult.blockPos, true, owner)
    }
}