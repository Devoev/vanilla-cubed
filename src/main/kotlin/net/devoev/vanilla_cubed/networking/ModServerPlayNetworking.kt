package net.devoev.vanilla_cubed.networking

import net.devoev.vanilla_cubed.entity.falling
import net.devoev.vanilla_cubed.entity.view
import net.devoev.vanilla_cubed.util.MapInitializer
import net.devoev.vanilla_cubed.util.math.plus
import net.devoev.vanilla_cubed.util.math.times
import net.devoev.vanilla_cubed.util.wearsEnderite
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking.PlayChannelHandler
import net.minecraft.block.BlockState
import net.minecraft.nbt.NbtCompound
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d

object ModServerPlayNetworking : MapInitializer<Identifier, PlayChannelHandler>() {

    init {
        //TODO: Extract logic into separate function.
        this[Channels.JUMP_KEY_PRESSED] = PlayChannelHandler { _, player, _, _, _ ->
            val stack = player.inventory.armor[0]

            if (!player.falling || !player.wearsEnderite() || player.isFallFlying) {
                stack.nbt?.putBoolean("enderite_jump_active", false)
                return@PlayChannelHandler
            }

            if (stack.nbt?.getBoolean("enderite_jump_active") == true) return@PlayChannelHandler

            player.run {
                stack.nbt?.putBoolean("enderite_jump_active", true)
                var dir = view * 5
                var dest: Vec3d
                var state: BlockState

                do {
                    dest = pos + dir
                    state = world.getBlockState(BlockPos(dest.x, dest.y, dest.z))
                    dir *= 0.7
                    if (dir.length() < 0.1) return@PlayChannelHandler
                } while (state.isOpaque)

                teleport(dest.x, dest.y, dest.z)
            }
        }
    }

    override fun init() {
        forEach { ServerPlayNetworking.registerGlobalReceiver(it.key, it.value) }
    }
}