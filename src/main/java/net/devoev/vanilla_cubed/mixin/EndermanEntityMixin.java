package net.devoev.vanilla_cubed.mixin;

import net.devoev.vanilla_cubed.item.modifier.PreventEndermanAggressionModifierKt;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * A mixin to change the enderman entity.
 */
@Mixin(EndermanEntity.class)
public class EndermanEntityMixin {

    /**
     * Endermen won't attack when player is wearing enderite armor.
     */
    @Inject(method = "isPlayerStaring", at = @At("HEAD"), cancellable = true)
    private void notStaringWhenWearingEnderite(PlayerEntity player, CallbackInfoReturnable<Boolean> info) {
        PreventEndermanAggressionModifierKt.preventEndermanAggression(player, info);
    }
}
