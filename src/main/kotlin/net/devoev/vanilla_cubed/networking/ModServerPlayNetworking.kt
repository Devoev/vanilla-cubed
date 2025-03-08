package net.devoev.vanilla_cubed.networking

import net.devoev.vanilla_cubed.util.MapInitializer
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking.PlayChannelHandler
import net.minecraft.util.Identifier

/**
 * All modded server networking receivers.
 */
object ModServerPlayNetworking : MapInitializer<Identifier, PlayChannelHandler>() {

    init {
        this[Channels.BEACON_BUTTON_UPDATE] = BEACON_BUTTON_UPDATE
    }

    override fun init() {
        forEach { ServerPlayNetworking.registerGlobalReceiver(it.key, it.value) }
    }
}