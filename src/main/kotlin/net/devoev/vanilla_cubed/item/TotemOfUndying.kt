package net.devoev.vanilla_cubed.item

import net.devoev.vanilla_cubed.config.ModConfig
import net.devoev.vanilla_cubed.mixin.LivingEntityMixin
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.Items

/**
 * Makes the totem of undying work from any slot by setting the local [stack] variable to an [Items.TOTEM_OF_UNDYING].
 * @see LivingEntityMixin.useTotem
 */
fun useTotem(player: PlayerEntity, stack: ItemStack?): ItemStack? {
    if (!ModConfig.config.overrideTotemMechanics) return stack
    return player.inventory.main.find { it.isOf(Items.TOTEM_OF_UNDYING) } ?: stack
}