package net.devoev.vanilla_cubed.entity

import net.minecraft.entity.ItemEntity

/**
 * Sets [ItemEntity.droppedByPlayer] NBT property of the dropped item to `true`.
 */
fun setDroppedByPlayer(itemEntity: ItemEntity): ItemEntity {
    if (itemEntity.world.isClient) return itemEntity
    itemEntity.droppedByPlayer = true
    return itemEntity
}