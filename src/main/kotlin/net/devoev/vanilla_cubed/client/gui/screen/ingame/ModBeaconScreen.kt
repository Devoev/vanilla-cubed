package net.devoev.vanilla_cubed.client.gui.screen.ingame

import net.devoev.vanilla_cubed.screen.ModBeaconScreenHandler
import net.minecraft.client.gui.screen.ingame.HandledScreen
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.text.Text

class ModBeaconScreen(handler: ModBeaconScreenHandler, inventory: PlayerInventory, title: Text)
    : HandledScreen<ModBeaconScreenHandler>(handler, inventory, title) {
    override fun drawBackground(matrices: MatrixStack?, delta: Float, mouseX: Int, mouseY: Int) {
        TODO("Not yet implemented")
    }
}