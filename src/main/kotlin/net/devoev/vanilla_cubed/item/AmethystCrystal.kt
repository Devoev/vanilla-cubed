package net.devoev.vanilla_cubed.item

import net.minecraft.entity.Entity
import net.minecraft.entity.ItemEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack

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
        ModItems.AMETHYST_CRYSTAL -> charge(ModItems.CHARGED_AMETHYST_CRYSTAL)
        ModItems.AMETHYST_CRYSTAL_BLOCK -> charge(ModItems.CHARGED_AMETHYST_CRYSTAL_BLOCK)
        ModItems.AMETHYST_COMPASS -> {
            if (!entity.stack.charged) charge(ModItems.AMETHYST_COMPASS)
        }
    }
}