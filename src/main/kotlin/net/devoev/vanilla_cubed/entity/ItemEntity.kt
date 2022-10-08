package net.devoev.vanilla_cubed.entity

import net.minecraft.entity.ItemEntity

/**
 * The backing field map for the data to indicate, whether the item was dropped by a player.
 */
private val DROPPED_BY_PLAYER_FIELD = mutableMapOf<ItemEntity, Boolean>()

/**
 * Whether this item is dropped by a player.
 */
var ItemEntity.droppedByPlayer: Boolean
    get() = DROPPED_BY_PLAYER_FIELD[this] == true
    set(value) { DROPPED_BY_PLAYER_FIELD[this] = value }