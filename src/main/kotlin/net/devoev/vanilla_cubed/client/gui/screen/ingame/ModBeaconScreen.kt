package net.devoev.vanilla_cubed.client.gui.screen.ingame

import com.mojang.blaze3d.systems.RenderSystem
import net.devoev.vanilla_cubed.VanillaCubed
import net.devoev.vanilla_cubed.screen.ModBeaconScreenHandler
import net.minecraft.client.gui.screen.ingame.HandledScreen
import net.minecraft.client.render.GameRenderer
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.text.Text

class ModBeaconScreen(handler: ModBeaconScreenHandler, inventory: PlayerInventory, title: Text)
    : HandledScreen<ModBeaconScreenHandler>(handler, inventory, title) {

    override fun drawBackground(matrices: MatrixStack?, delta: Float, mouseX: Int, mouseY: Int) {
        RenderSystem.setShader { GameRenderer.getPositionTexShader() }
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f)
        RenderSystem.setShaderTexture(0, TEXTURE)
        val i = (width - backgroundWidth) / 2
        val j = (height - backgroundHeight) / 2
        this.drawTexture(matrices, i, j, 0, 0, backgroundWidth, backgroundHeight)
//        itemRenderer.zOffset = 100.0f
//        itemRenderer.renderInGuiWithOverrides(ItemStack(Items.NETHERITE_INGOT), i + 20, j + 109)
//        itemRenderer.renderInGuiWithOverrides(ItemStack(Items.EMERALD), i + 41, j + 109)
//        itemRenderer.renderInGuiWithOverrides(ItemStack(Items.DIAMOND), i + 41 + 22, j + 109)
//        itemRenderer.renderInGuiWithOverrides(ItemStack(Items.GOLD_INGOT), i + 42 + 44, j + 109)
//        itemRenderer.renderInGuiWithOverrides(ItemStack(Items.IRON_INGOT), i + 42 + 66, j + 109)
//        itemRenderer.zOffset = 0.0f
    }

    companion object {

        val TEXTURE = VanillaCubed.id("textures/gui/container/mod_beacon.png")
    }
}