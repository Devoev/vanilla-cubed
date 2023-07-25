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
import net.minecraft.util.Identifier
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
    private var upgrade: BeaconUpgrade? = handler.upgrade

    /**
     * Adds the given [button] to this list and appends it as a drawable child.
     */
    private fun MutableList<BeaconButtonWidget>.addButton(button: ClickableWidget): Boolean {
        addDrawableChild(button)
        return add(button as BeaconButtonWidget) // TODO: Update button classes to avoid cast
    }

    /**
     * Ticks all buttons in this list.
     */
    private fun MutableList<BeaconButtonWidget>.tick() {
        val i = (handler as ModBeaconScreenHandler).properties // TODO: This value currently doesnt represent the level!
        forEach { it.tick(i) }
    }

    init {
        backgroundWidth = 230
        backgroundHeight = 219
        handler.addListener(object : ScreenHandlerListener {
            override fun onSlotUpdate(handler2: ScreenHandler, slotId: Int, stack: ItemStack) {}
            override fun onPropertyUpdate(handler2: ScreenHandler, property: Int, value: Int) {
                // Update the behavior property of this screen to stay in sync with the handler.
                upgrade = handler.upgrade
            }
        })
    }

    override fun init() {
        super.init()
        buttons.clear()

        // Place upgrade buttons
        val dx = 42
        val dy = 25
        val x0 = 60
        val y0 = 16
        for (i in 0 .. 3) {
            for (j in 0..3) {
                val xi = x + x0 + i*dx
                val yj = y + y0 + j*dy
                val n = j + 4*i // canonical upgrade index
                // TODO: Pick correct texture and tooltip

                buttons.addButton(UpgradeButtonWidget(xi, yj, BeaconUpgrades.getData(n)))
            }
        }
    }

    override fun handledScreenTick() {
        super.handledScreenTick()
        buttons.tick()
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

        /**
         * Whether the tooltip of this button should be rendered.
         */
        fun shouldRenderTooltip(): Boolean

        /**
         * Renders the tooltip of this button.
         */
        fun renderTooltip(matrices: MatrixStack?, mouseX: Int, mouseY: Int)

        /**
         * Ticks this button.
         */
        fun tick(level: Int)
    }

    /**
     * The abstract base class for all pressable beacon buttons at coordinates ([x], [y]) with a hover [tooltip].
     */
    @Environment(EnvType.CLIENT)
    internal abstract inner class BaseButtonWidget(x: Int, y: Int, private val tooltip: Text)
        : PressableWidget(x, y, 22, 22, ScreenTexts.EMPTY), BeaconButtonWidget {

        /**
         * Whether this button is disabled, meaning it is already pressed.
         */
        abstract val disabled: Boolean

        /**
         * Renders additional content like a sprite or texture on top.
         */
        protected abstract fun renderExtra(matrices: MatrixStack?)

        /**
         * Updates the server side beacon by sending the required packets to the [ModBeaconScreenHandler].
         */
        fun update() {
            if (upgrade == null) return
            val packet = PacketByteBufs.create().writeBeaconUpgrade(upgrade!!)
            ClientPlayNetworking.send(Channels.BEACON_BUTTON_UPDATE, packet)
        }

        override fun renderButton(matrices: MatrixStack?, mouseX: Int, mouseY: Int, delta: Float) {
            RenderSystem.setShader { GameRenderer.getPositionTexShader() }
            RenderSystem.setShaderTexture(0, TEXTURE)
            RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f)

            val u = if (!active) {
                width * 2
            } else if (disabled) {
                width * 1
            } else if (isHovered) {
                width * 3
            } else 0

            drawTexture(matrices, x, y, u, 219, width, height)
            renderExtra(matrices)
        }

        override fun shouldRenderTooltip(): Boolean = hovered

        override fun renderTooltip(matrices: MatrixStack?, mouseX: Int, mouseY: Int) {
            renderTooltip(matrices, tooltip, mouseX, mouseY)
        }

        override fun appendNarrations(builder: NarrationMessageBuilder?) = appendDefaultNarrations(builder)
    }


    /**
     * A button enabling a [BeaconUpgrade].
     * @param x x coordinate of this button.
     * @param y y coordinate of this button.
     * @param u Left-most coordinate of the texture region.
     * @param v Top-most coordinate of the texture region.
     * @param upgrade Beacon upgrade enabled by this button.
     * @param tooltip Tooltip that shows when hovering over this button.
     * @param texture Identifier of the 18x18 texture of this button.
     */
    @Environment(EnvType.CLIENT)
    internal inner class UpgradeButtonWidget(
        x: Int,
        y: Int,
        private val upgrade: BeaconUpgrade,
        tooltip: Text,
        private val texture: Identifier) : BaseButtonWidget(x, y, tooltip) {

        constructor(x: Int, y: Int, data: BeaconUpgradeButtonData) : this(x, y, data.upgrade, data.tooltip, data.texture)

        override val disabled: Boolean
            get() = upgrade == this@ModBeaconScreen.upgrade

        /**
         * Renders the 18x18 [texture] of this button.
         */
        override fun renderExtra(matrices: MatrixStack?) {
            RenderSystem.setShaderTexture(0, texture)
            drawTexture(matrices, x + 2, y + 2, 0f, 0f, 18, 18, 18, 18)
        }

        override fun onPress() {
            if (disabled) return

            // Update the enabled upgrade of the screen with this one
            this@ModBeaconScreen.upgrade = upgrade

            update()
            buttons.tick()
        }

        override fun tick(level: Int) {
            // TODO: Deactivate button, if level is not high enough.
            active = true
        }
    }
}