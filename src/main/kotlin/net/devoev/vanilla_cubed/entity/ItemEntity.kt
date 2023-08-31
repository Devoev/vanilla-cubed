package net.devoev.vanilla_cubed.entity

import net.devoev.vanilla_cubed.item.ModItems.AMETHYST_COMPASS
import net.devoev.vanilla_cubed.item.ModItems.AMETHYST_CRYSTAL
import net.devoev.vanilla_cubed.item.ModItems.AMETHYST_CRYSTAL_BLOCK
import net.devoev.vanilla_cubed.item.ModItems.CHARGED_AMETHYST_CRYSTAL
import net.devoev.vanilla_cubed.item.ModItems.CHARGED_AMETHYST_CRYSTAL_BLOCK
import net.devoev.vanilla_cubed.item.charged
import net.minecraft.entity.Entity
import net.minecraft.entity.ItemEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack

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

/**
 * Charges the amethyst crystal item [entity] when struck by lightning in [Entity.onStruckByLightning].
 */
fun chargeAmethystCrystalItems(entity: ItemEntity) {

    /**
     * Replaces the item with the new [item].
     */
    fun charge(item: Item) {
        entity.stack = ItemStack(item, entity.stack.count)
        entity.isInvulnerable = true
        entity.isInvisible = false
        entity.isOnFire = false
    }

    when (entity.stack.item) {
        AMETHYST_CRYSTAL -> charge(CHARGED_AMETHYST_CRYSTAL)
        AMETHYST_CRYSTAL_BLOCK -> charge(CHARGED_AMETHYST_CRYSTAL_BLOCK)
        AMETHYST_COMPASS -> {
            if (!entity.stack.charged) charge(AMETHYST_COMPASS)
        }
    }
}
