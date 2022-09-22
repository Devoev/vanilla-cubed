package net.devoev.vanilla_cubed.client.event

import net.devoev.vanilla_cubed.networking.Channels
import net.devoev.vanilla_cubed.util.MapInitializer
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.dimension.v1.FabricDimensions
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs
import net.fabricmc.fabric.impl.client.keybinding.KeyBindingRegistryImpl
import net.fabricmc.fabric.mixin.client.keybinding.KeyBindingAccessor
import net.minecraft.client.MinecraftClient
import net.minecraft.client.option.KeyBinding
import net.minecraft.network.PacketByteBuf
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import net.minecraft.world.TeleportTarget

@Environment(EnvType.CLIENT)
object ModClientTickEvents : MapInitializer<KeyBinding, (MinecraftClient) -> Unit>() {

    /**
     * Initializes keybindings that are not loaded yet.
     */
    private fun lateInit() {
        this[MinecraftClient.getInstance().options.jumpKey] = {
            val buf = PacketByteBufs.create()
            ClientPlayNetworking.send(Channels.TEST, buf)
        }
    }

    override fun init() {
        ClientTickEvents.END_CLIENT_TICK.register { client ->
            lateInit()
            forEach { key, callback ->
                while (key.wasPressed()) { callback(client) }
            }
        }
    }
}