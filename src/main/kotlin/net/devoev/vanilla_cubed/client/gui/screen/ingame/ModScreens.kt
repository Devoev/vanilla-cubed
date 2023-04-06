package net.devoev.vanilla_cubed.client.gui.screen.ingame

import net.devoev.vanilla_cubed.screen.ModScreenHandlerTypes
import net.devoev.vanilla_cubed.util.MapInitializer
import net.minecraft.client.gui.screen.ingame.HandledScreen
import net.minecraft.client.gui.screen.ingame.HandledScreens
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.screen.ScreenHandler
import net.minecraft.screen.ScreenHandlerType
import net.minecraft.text.Text

/**
 * TODO: Fix generic type arguments.
 */
object ModScreens : MapInitializer<ScreenHandlerType<out ScreenHandler>, (ScreenHandler, PlayerInventory, Text) -> HandledScreen<ScreenHandler>>() {

    init {
        HandledScreens.register(ModScreenHandlerTypes.MOD_BEACON, ::ModBeaconScreen)
    }

    override fun init() = forEach { HandledScreens.register(it.key, it.value) }
}