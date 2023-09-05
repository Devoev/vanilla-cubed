package net.devoev.vanilla_cubed.networking

import net.devoev.vanilla_cubed.entity.falling
import net.devoev.vanilla_cubed.entity.view
import net.devoev.vanilla_cubed.entity.wearsEnderite
import net.devoev.vanilla_cubed.util.math.plus
import net.devoev.vanilla_cubed.util.math.times
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking.PlayChannelHandler
import net.minecraft.block.BlockState
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d

/**
 * Teleports the player in the direction he is looking at,
 * if he presses the jump key and wears a full set of enderite armor.
 */
@Deprecated("Enderite armor is not using the teleportation ability any more.")
val ENDERITE_TELEPORTATION = PlayChannelHandler { _, player, _, _, _ ->
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
            state = world.getBlockState(BlockPos(dest.x.toInt(), dest.y.toInt(), dest.z.toInt()))
            dir *= 0.7
            if (dir.length() < 0.1) return@PlayChannelHandler
        } while (state.isOpaque)

        teleport(dest.x, dest.y, dest.z)
    }
}