package net.devoev.vanilla_cubed.client.gui.screen.ingame

import net.devoev.vanilla_cubed.VanillaCubed
import net.devoev.vanilla_cubed.block.entity.beacon.BeaconUpgradeButtonData
import net.devoev.vanilla_cubed.block.entity.beacon.BeaconUpgradeTier
import net.devoev.vanilla_cubed.block.entity.beacon.BeaconUpgrades
import net.devoev.vanilla_cubed.block.entity.beacon.idx
import net.devoev.vanilla_cubed.block.entity.beacon.upgrades.BeaconUpgrade
import net.devoev.vanilla_cubed.networking.Channels
import net.devoev.vanilla_cubed.networking.writeBeaconUpgrades
import net.devoev.vanilla_cubed.screen.ModBeaconScreenHandler
import net.devoev.vanilla_cubed.screen.UPGRADE_RANGE
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.screen.ingame.HandledScreen
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder
import net.minecraft.client.gui.tooltip.Tooltip
import net.minecraft.client.gui.widget.ClickableWidget
import net.minecraft.client.gui.widget.PressableWidget
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.item.ItemStack
import net.minecraft.screen.ScreenHandler
import net.minecraft.screen.ScreenHandlerListener
import net.minecraft.screen.ScreenTexts
import net.minecraft.text.Text
import net.minecraft.util.Identifier

/**
 * Handled screen of a [ModBeaconScreenHandler].
 *
 * @param handler The handler linked to this screen.
 * @param inventory Player inventory accessing the beacon block.
 * @param title Title of this screen.
 *
 * @property buttons List of all buttons appearing on the screen.
 * @property upgrades The active beacon upgrade.
 */
@Environment(EnvType.CLIENT)
class ModBeaconScreen(handler: ModBeaconScreenHandler, inventory: PlayerInventory, title: Text)
    : HandledScreen<ModBeaconScreenHandler>(handler, inventory, title) {

    private val buttons: MutableList<BeaconButtonWidget> = mutableListOf()
    private var upgrades: MutableList<BeaconUpgrade?> = handler.upgrades

    /**
     * Adds the given [button] to this list and appends it as a drawable child.
     */
    private fun MutableList<BeaconButtonWidget>.addButton(button: BeaconButtonWidget): Boolean {
        addDrawableChild(button as ClickableWidget)
        return add(button)
    }

    /**
     * Ticks all buttons in this list.
     */
    private fun MutableList<BeaconButtonWidget>.tick() = forEach { it.tick(handler.totalLevels, handler.remainingLevels) }

    init {
        backgroundWidth = 230
        backgroundHeight = 219
        handler.addListener(object : ScreenHandlerListener {
            override fun onSlotUpdate(handler2: ScreenHandler, slotId: Int, stack: ItemStack) {}
            override fun onPropertyUpdate(handler2: ScreenHandler, property: Int, value: Int) {
                // Update the behavior property of this screen to stay in sync with the handler.
                if (property in UPGRADE_RANGE) upgrades = handler.upgrades
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
                buttons.addButton(UpgradeButtonWidget(xi, yj, BeaconUpgrades.dataAt(n)))
            }
        }
    }

    override fun handledScreenTick() {
        super.handledScreenTick()
        buttons.tick()
    }

    override fun render(context: DrawContext, mouseX: Int, mouseY: Int, delta: Float) {
        renderBackground(context)
        super.render(context, mouseX, mouseY, delta)
        drawMouseoverTooltip(context, mouseX, mouseY)
    }

    override fun drawForeground(context: DrawContext, mouseX: Int, mouseY: Int) {

    }

    override fun drawBackground(context: DrawContext, delta: Float, mouseX: Int, mouseY: Int) {
        val i = (width - backgroundWidth) / 2
        val j = (height - backgroundHeight) / 2
        context.drawTexture(TEXTURE, i, j, 0, 0, backgroundWidth, backgroundHeight)
        context.matrices.push()
//        context.matrices.translate(0f, 0f, 100f)
//        context.matrices.pop()
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
         * Ticks this button.
         * @param totalLevels The total block levels.
         * @param remainingLevels The remaining block levels.
         */
        fun tick(totalLevels: IntArray, remainingLevels: IntArray)
    }

    /**
     * The abstract base class for all pressable beacon buttons at coordinates ([x], [y]) with a hover [tooltip].
     */
    @Environment(EnvType.CLIENT)
    internal abstract inner class BaseButtonWidget(x: Int, y: Int, tooltipText: Text)
        : PressableWidget(x, y, 22, 22, ScreenTexts.EMPTY), BeaconButtonWidget {

        /**
         * Whether this button is pressed.
         */
        abstract val pressed: Boolean

        init {
            tooltip = Tooltip.of(tooltipText)
        }

        /**
         * Renders additional content like a sprite or texture on top.
         */
        protected abstract fun renderExtra(context: DrawContext)

        /**
         * Updates the server side beacon by sending the required packets to the [ModBeaconScreenHandler].
         */
        fun update() {
            val packet = PacketByteBufs.create().writeBeaconUpgrades(upgrades)
            ClientPlayNetworking.send(Channels.BEACON_BUTTON_UPDATE, packet)
        }

        override fun renderButton(context: DrawContext, mouseX: Int, mouseY: Int, delta: Float) {
            val u = if (!active) {
                width * 2
            } else if (pressed) {
                width * 1
            } else if (isHovered) {
                width * 3
            } else 0

            context.drawTexture(TEXTURE, x, y, u, 219, width, height)
            renderExtra(context)
        }

        override fun appendClickableNarrations(builder: NarrationMessageBuilder?) = appendDefaultNarrations(builder)
    }


    /**
     * A button enabling a [BeaconUpgrade].
     * @param x x coordinate of this button.
     * @param y y coordinate of this button.
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
        private val texture: Identifier,
        private val tier: BeaconUpgradeTier
    ) : BaseButtonWidget(x, y, tooltip) {

        constructor(x: Int, y: Int, data: BeaconUpgradeButtonData) : this(x, y, data.upgrade, data.tooltip, data.texture, data.tier)

        init {
            // TODO: Fix that initial value is always 0,0,0,0
            //  Issue is maybe caused by screen handler listener above
            active = tier.checkLevel(handler.remainingLevels)
        }

        override val pressed: Boolean
            get() = this@ModBeaconScreen.upgrades[upgrade.idx] == upgrade

        /**
         * Renders the 18x18 [texture] of this button.
         */
        override fun renderExtra(context: DrawContext) {
            context.drawTexture(texture, x + 2, y + 2, 0f, 0f, 18, 18, 18, 18)
        }

        override fun onPress() {
            if (pressed) {
                this@ModBeaconScreen.upgrades[upgrade.idx] = null
            } else {
                this@ModBeaconScreen.upgrades[upgrade.idx] = upgrade
            }

            update()
            buttons.tick()
        }

        override fun tick(totalLevels: IntArray, remainingLevels: IntArray) {
            active = if (pressed) {
                tier.checkLevel(totalLevels)
            } else {
                tier.checkLevel(totalLevels) && tier.checkLevel(remainingLevels)
            }
        }
    }
}