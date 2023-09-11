package net.devoev.vanilla_cubed.item.modifier

import net.devoev.vanilla_cubed.item.isAncientGold
import net.devoev.vanilla_cubed.mixin.EndermanEntityMixin
import net.minecraft.item.Item
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable

/**
 * Endermen won't attack when player is wearing enderite armor.
 *
 * TODO: Make independent of ancient gold armor
 * @see EndermanEntityMixin
 */
fun preventPiglinAggression(item: Item, cir: CallbackInfoReturnable<Boolean>) {
    if (item.isAncientGold()) {
        cir.returnValue = true
    }
}