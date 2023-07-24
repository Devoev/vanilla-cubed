package net.devoev.vanilla_cubed.networking

import net.devoev.vanilla_cubed.screen.ModBeaconScreenHandler
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking.PlayChannelHandler
import net.minecraft.world.World

/**
 * Handles updates of the beacon if it's buttons are pressed.
 */
val BEACON_BUTTON_UPDATE = PlayChannelHandler { _, player, _, buf, _ ->
    val screenHandler = player.currentScreenHandler
    if (screenHandler !is ModBeaconScreenHandler) return@PlayChannelHandler

    // Read the canonical index of the received upgrade via readInt.
    screenHandler.propertyDelegate[0] = buf.readBeaconUpgradeIndex()
    screenHandler.context.run(World::markDirty)
}