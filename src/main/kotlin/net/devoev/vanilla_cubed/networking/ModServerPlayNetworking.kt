package net.devoev.vanilla_cubed.networking

import net.devoev.vanilla_cubed.util.MapInitializer
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking.PlayChannelHandler
import net.minecraft.util.Identifier

object ModServerPlayNetworking : MapInitializer<Identifier, PlayChannelHandler>() {

    init {
        this[Channels.TEST] = PlayChannelHandler { a,b,c,d,e -> println("$d received!") }
    }

    override fun init() {
        forEach { ServerPlayNetworking.registerGlobalReceiver(it.key, it.value) }
    }
}