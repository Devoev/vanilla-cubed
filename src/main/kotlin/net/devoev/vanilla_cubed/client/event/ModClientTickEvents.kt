package net.devoev.vanilla_cubed.client.event

import net.devoev.vanilla_cubed.networking.Channels
import net.devoev.vanilla_cubed.util.MapInitializer
import net.devoev.vanilla_cubed.util.SetInitializer
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs
import net.minecraft.client.MinecraftClient
import net.minecraft.client.option.KeyBinding

@Environment(EnvType.CLIENT)
object ModClientTickEvents : SetInitializer<(MinecraftClient) -> Unit>() {

    init {
        create {
            if (it.player?.input?.jumping == true)
                ClientPlayNetworking.send(Channels.JUMP_KEY_PRESSED, PacketByteBufs.create())
        }
    }

    override fun init() {
        ModClientTickEvents.forEach { ClientTickEvents.END_CLIENT_TICK.register(it) }
    }
}