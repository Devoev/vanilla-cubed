package net.devoev.vanilla_cubed.networking

import net.devoev.vanilla_cubed.screen.ModBeaconScreenHandler
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking.PlayChannelHandler

/**
 * Handles updates of the beacon if it's buttons are pressed.
 */
val BEACON_BUTTON_UPDATE = PlayChannelHandler { server, player, handler, buf, responseSender ->
    val screenHandler = player.currentScreenHandler
    if (screenHandler !is ModBeaconScreenHandler) return@PlayChannelHandler

    screenHandler.propertyDelegate[0] = 1
}