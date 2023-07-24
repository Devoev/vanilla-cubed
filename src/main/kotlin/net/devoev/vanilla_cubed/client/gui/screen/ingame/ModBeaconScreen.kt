package net.devoev.vanilla_cubed.client.gui.screen.ingame

import com.mojang.blaze3d.systems.RenderSystem
import net.devoev.vanilla_cubed.VanillaCubed
import net.devoev.vanilla_cubed.block.entity.behavior.BeaconUpgrade
import net.devoev.vanilla_cubed.block.entity.behavior.BeaconUpgrades
import net.devoev.vanilla_cubed.networking.Channels
import net.devoev.vanilla_cubed.networking.writeBeaconUpgrade
import net.devoev.vanilla_cubed.screen.ModBeaconScreenHandler
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs
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

/**
 * Handled screen of a [ModBeaconScreenHandler].
 * @param handler The handler linked to this screen.
 * @param inventory Player inventory accessing the beacon block.
 * @param title Title of this screen.
 *
 * @property buttons List of all buttons appearing on the screen.
 * @property upgrade The active beacon upgrade.
 */
@Environment(EnvType.CLIENT)
class ModBeaconScreen(handler: ModBeaconScreenHandler, inventory: PlayerInventory, title: Text)
    : HandledScreen<ModBeaconScreenHandler>(handler, inventory, title) {

    private val buttons: MutableList<BeaconButtonWidget> = mutableListOf()
    private lateinit var upgrade: BeaconUpgrade

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
                // Update the behavior property of this screen with the one from the handler.
                println("handler listener property update called")
                upgrade = handler.upgrade
            }
        })
    }

    override fun init() {
        super.init()
        buttons.clear()

        // Effect buttons
        val dx = 42
        val dy = 25
        val x0 = 60
        val y0 = 16
        for (i in 0 .. 3) {
            for (j in 0..3) {
                val xi = x + x0 + i*dx
                val yj = y + y0 + j*dy
                val n = j + 4*i // canonical upgrade index
                // TODO: Pick u and v for the correct texture
                buttons.addButton(UpgradeButtonWidget(xi, yj, 90, 220, BeaconUpgrades[n], ScreenTexts.EMPTY))
            }
        }
    }

    override fun handledScreenTick() {
        super.handledScreenTick()
        val i = (handler as ModBeaconScreenHandler).properties // TODO: This value currently doesnt represent the level!
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

    /**
     * A beacon button widget.
     */
    @Environment(EnvType.CLIENT)
    internal interface BeaconButtonWidget {
        fun shouldRenderTooltip(): Boolean
        fun renderTooltip(matrices: MatrixStack?, mouseX: Int, mouseY: Int)
        fun tick(level: Int)
    }

    /**
     * The abstract base class for all pressable beacon buttons at coordinates ([x], [y]) with a hover [message].
     */
    @Environment(EnvType.CLIENT)
    internal abstract inner class BaseButtonWidget(x: Int, y: Int, message: Text = ScreenTexts.EMPTY) : PressableWidget(x, y, 22, 22, message), BeaconButtonWidget {

        var disabled: Boolean = false

        /**
         * Updates the beacon by sending the required packets to the [ModBeaconScreenHandler].
         */
        fun update() {
            val packet = PacketByteBufs.create().writeBeaconUpgrade(upgrade)
            ClientPlayNetworking.send(Channels.BEACON_BUTTON_UPDATE, packet)
        }

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


    /**
     * A button enabling a [BeaconUpgrade].
     * @param x x coordinate of this button.
     * @param y y coordinate of this button.
     * @param u Left-most coordinate of the texture region.
     * @param v Top-most coordinate of the texture region.
     * @param upgrade Tick behavior of this button.
     * @param message The hovering text.
     */
    @Environment(EnvType.CLIENT)
    internal inner class UpgradeButtonWidget(
        x: Int,
        y: Int,
        private val u: Int, // TODO: Update with general texture
        private val v: Int,
        private val upgrade: BeaconUpgrade,
        message: Text) : BaseButtonWidget(x, y, message) {

        override fun renderExtra(matrices: MatrixStack?) {
            drawTexture(matrices, this.x + 2, this.y + 2, this.u, this.v, 18, 18)
        }

        override fun renderTooltip(matrices: MatrixStack?, mouseX: Int, mouseY: Int) {
            this@ModBeaconScreen.renderTooltip(matrices, this@ModBeaconScreen.title, mouseX, mouseY)
        }

        override fun onPress() {
            //TODO: Send packet to screen handler with updated effects
            println("Pressing button")
            if (disabled) return

            // Update the behavior of the screen with this one
            this@ModBeaconScreen.upgrade = upgrade

            // TODO: Tick buttons?

            update()
            disabled = true
        }

        override fun tick(level: Int) {
            // TODO: Deactivate or disable button, if level is not high enough.
            active = true
        }
    }
}
