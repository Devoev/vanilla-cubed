package net.devoev.vanilla_cubed.item.modifier

import net.devoev.vanilla_cubed.item.isEnderite
import net.devoev.vanilla_cubed.mixin.EndermanEntityMixin
import net.minecraft.entity.player.PlayerEntity
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable

/**
 * Endermen won't attack when player is wearing enderite armor.
 *
 * TODO: Make independent of enderite armor
 * @see EndermanEntityMixin
 */
fun preventEndermanAggression(player: PlayerEntity, cir: CallbackInfoReturnable<Boolean>) {
    for (stack in player.armorItems) {
        if (stack.item.isEnderite()) {
            cir.returnValue = false
            return
        }
    }
}