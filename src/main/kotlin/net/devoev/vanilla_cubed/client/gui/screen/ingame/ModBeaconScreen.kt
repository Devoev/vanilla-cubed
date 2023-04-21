package net.devoev.vanilla_cubed.client.gui.screen.ingame

import com.mojang.blaze3d.systems.RenderSystem
import net.devoev.vanilla_cubed.VanillaCubed
import net.devoev.vanilla_cubed.screen.ModBeaconScreenHandler
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.gui.screen.ingame.BeaconScreen.*
import net.minecraft.client.gui.screen.ingame.HandledScreen
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder
import net.minecraft.client.gui.widget.ClickableWidget
import net.minecraft.client.gui.widget.PressableWidget
import net.minecraft.client.render.GameRenderer
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.item.ItemStack
import net.minecraft.screen.ScreenHandler
import net.minecraft.screen.ScreenHandlerListener
import net.minecraft.screen.ScreenTexts
import net.minecraft.text.Text
import java.util.*

@Environment(EnvType.CLIENT)
class ModBeaconScreen(handler: ModBeaconScreenHandler, inventory: PlayerInventory, title: Text)
    : HandledScreen<ModBeaconScreenHandler>(handler, inventory, title) {

    private val buttons: MutableList<BeaconButtonWidget> = mutableListOf()

    private fun MutableList<BeaconButtonWidget>.addButton(button: ClickableWidget): Boolean {
        addDrawableChild(button)
        return add(button as BeaconButtonWidget) // TODO: Update button classes to avoid cast
    }

    init {
        backgroundWidth = 230
        backgroundHeight = 219
        handler.addListener(object : ScreenHandlerListener {
            override fun onSlotUpdate(handler2: ScreenHandler, slotId: Int, stack: ItemStack) {}
            override fun onPropertyUpdate(handler2: ScreenHandler, property: Int, value: Int) {
                println("Apply effects!") //TODO
            }
        })
    }

    override fun init() {
        super.init()
        buttons.clear()
        buttons.addButton(BasicButtonWidget(x + 164, y + 107, 90, 220, ScreenTexts.DONE))
        buttons.addButton(BasicButtonWidget(x + 190, y + 107, 112, 220, ScreenTexts.CANCEL))
    }

    override fun handledScreenTick() {
        super.handledScreenTick()
        val i = (handler as ModBeaconScreenHandler).properties
        buttons.forEach { it.tick(i) }
    }

    override fun render(matrices: MatrixStack?, mouseX: Int, mouseY: Int, delta: Float) {
        renderBackground(matrices)
        super.render(matrices, mouseX, mouseY, delta)
        drawMouseoverTooltip(matrices, mouseX, mouseY)
    }

    override fun drawForeground(matrices: MatrixStack?, mouseX: Int, mouseY: Int) {
        for (button in buttons) {
            if (!button.shouldRenderTooltip()) continue
            button.renderTooltip(matrices, mouseX - x, mouseY - y)
            break
        }
    }

    override fun drawBackground(matrices: MatrixStack?, delta: Float, mouseX: Int, mouseY: Int) {
        RenderSystem.setShader { GameRenderer.getPositionTexShader() }
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f)
        RenderSystem.setShaderTexture(0, TEXTURE)
        val i = (width - backgroundWidth) / 2
        val j = (height - backgroundHeight) / 2
        drawTexture(matrices, i, j, 0, 0, backgroundWidth, backgroundHeight)
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

    @Environment(EnvType.CLIENT)
    internal interface BeaconButtonWidget {
        fun shouldRenderTooltip(): Boolean
        fun renderTooltip(matrices: MatrixStack?, mouseX: Int, mouseY: Int)
        fun tick(level: Int)
    }

    @Environment(EnvType.CLIENT)
    internal abstract inner class BaseButtonWidget(x: Int, y: Int, message: Text = ScreenTexts.EMPTY) : PressableWidget(x, y, 22, 22, message), BeaconButtonWidget {

        var disabled: Boolean = false

        override fun renderButton(matrices: MatrixStack?, mouseX: Int, mouseY: Int, delta: Float) {
            RenderSystem.setShader { GameRenderer.getPositionTexShader() }
            RenderSystem.setShaderTexture(0, TEXTURE)
            RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f)
            var j = 0
            if (!active) {
                j += width * 2
            } else if (this.disabled) {
                j += width * 1
            } else if (this.isHovered) {
                j += width * 3
            }
            this.drawTexture(matrices, x, y, j, 219, width, height)
            this.renderExtra(matrices)
        }

        protected abstract fun renderExtra(matrices: MatrixStack?)

        override fun shouldRenderTooltip(): Boolean = hovered

        override fun appendNarrations(builder: NarrationMessageBuilder?) = appendDefaultNarrations(builder)
    }

    @Environment(EnvType.CLIENT)
    internal inner class BasicButtonWidget(x: Int, y: Int, val u: Int, val v: Int, message: Text) : BaseButtonWidget(x, y, message) {

        override fun renderExtra(matrices: MatrixStack?) {
            drawTexture(matrices, this.x + 2, this.y + 2, this.u, this.v, 18, 18)
        }

        override fun renderTooltip(matrices: MatrixStack?, mouseX: Int, mouseY: Int) {
            this@ModBeaconScreen.renderTooltip(matrices, this@ModBeaconScreen.title, mouseX, mouseY)
        }

        override fun onPress() {
            this@ModBeaconScreen.client!!.player!!.closeHandledScreen()
        }

        override fun tick(level: Int) {
            active = true
        }
    }
}
