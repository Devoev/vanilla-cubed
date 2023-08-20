package net.devoev.vanilla_cubed.block.entity.beacon_upgrade

import net.devoev.vanilla_cubed.mixin.ItemStackMixin
import net.minecraft.entity.player.PlayerEntity
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable

/**
 * Disables all damage to tools and armor.
 */
object DisableGearDamageUpgrade : ToggledUpgrade() {

    /**
     * Injection for [ItemStackMixin.preventItemDamage].
     */
    fun injectItemStack(player: PlayerEntity, cir: CallbackInfoReturnable<Boolean>) {
        if (inRange(player.pos)) cir.returnValue = false
    }
}