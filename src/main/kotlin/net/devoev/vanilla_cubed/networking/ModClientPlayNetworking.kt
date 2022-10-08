package net.devoev.vanilla_cubed.networking

import net.devoev.vanilla_cubed.util.MapInitializer
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking.PlayChannelHandler
import net.minecraft.util.Identifier

@Environment(EnvType.CLIENT)
object ModClientPlayNetworking: MapInitializer<Identifier, PlayChannelHandler>() {

    init {
        this[Channels.ENDERITE_SHIELD_SPAWN_PARTICLES] = ENDERITE_SHIELD_PARTICLES
    }

    override fun init() {
        forEach { ClientPlayNetworking.registerGlobalReceiver(it.key, it.value) }
    }
}