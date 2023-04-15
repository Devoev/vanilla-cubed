package net.devoev.vanilla_cubed.screen

import net.minecraft.block.Blocks
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.inventory.Inventory
import net.minecraft.item.ItemStack
import net.minecraft.screen.ScreenHandler
import net.minecraft.screen.ScreenHandlerContext
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

/**
 * TODO: Implement functions similar to BeaconScreenHandler
 */
class ModBeaconScreenHandler(syncId: Int, inventory: Inventory) : ScreenHandler(ModScreenHandlerTypes.MOD_BEACON, syncId) {

    private val context: ScreenHandlerContext = ScreenHandlerContext.EMPTY

    override fun transferSlot(player: PlayerEntity?, index: Int): ItemStack {
        TODO("Not yet implemented")
    }

    override fun canUse(player: PlayerEntity?): Boolean {
        return context.get({ world: World, pos: BlockPos ->
            if (!world.getBlockState(pos).isOf(Blocks.BEACON)) {
                return@get false
            }
            player!!.squaredDistanceTo(
                pos.x.toDouble() + 0.5,
                pos.y.toDouble() + 0.5,
                pos.z.toDouble() + 0.5
            ) <= 64.0
        }, true)
    }

}