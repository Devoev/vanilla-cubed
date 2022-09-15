package net.devoev.vanilla_cubed.client.network

import net.minecraft.client.network.ClientPlayerEntity

/**
 * Returns true, if the player is moving.
 */
val ClientPlayerEntity.moving: Boolean get() = input.movementInput.length() != 0f