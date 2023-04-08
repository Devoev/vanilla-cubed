package net.devoev.vanilla_cubed.screen

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.inventory.Inventory
import net.minecraft.item.ItemStack
import net.minecraft.screen.BeaconScreenHandler
import net.minecraft.screen.ScreenHandler

/**
 * TODO: Implement functions similar to BeaconScreenHandler
 */
class ModBeaconScreenHandler(syncId: Int, inventory: Inventory) : ScreenHandler(ModScreenHandlerTypes.MOD_BEACON, syncId) {
    override fun transferSlot(player: PlayerEntity?, index: Int): ItemStack {
        TODO("Not yet implemented")
    }

    override fun close(player: PlayerEntity?) {
        println("Closed modded beacon!")
        super.close(player)
    }

    override fun canUse(player: PlayerEntity?): Boolean {
        TODO("Not yet implemented")
    }

}