package net.devoev.vanilla_cubed.screen

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.inventory.Inventory
import net.minecraft.screen.BeaconScreenHandler

class ModBeaconScreenHandler(syncId: Int, inventory: Inventory) : BeaconScreenHandler(syncId, inventory) {

    override fun close(player: PlayerEntity?) {
        println("Closed modded beacon!")
        super.close(player)
    }

}