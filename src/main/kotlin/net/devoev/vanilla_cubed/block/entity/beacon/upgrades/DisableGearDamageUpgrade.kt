package net.devoev.vanilla_cubed.block.entity.beacon.upgrades

import net.devoev.vanilla_cubed.mixin.ItemStackMixin
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable

/**
 * Disables all damage to tools and armor.
 */
object DisableGearDamageUpgrade : ToggledUpgrade() {

    /**
     * Prevents item damage by returning `false` in [ItemStack.isDamageable].
     * @see ItemStackMixin.preventItemDamage
     */
    fun preventItemDamage(player: PlayerEntity?, cir: CallbackInfoReturnable<Boolean>) {
        if (player != null && inRange(player.pos)) cir.returnValue = false
    }
}