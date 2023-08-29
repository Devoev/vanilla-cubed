package net.devoev.vanilla_cubed.mixin;

import net.devoev.vanilla_cubed.block.entity.beacon_upgrade.DisableMobGriefingUpgrade;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "net/minecraft/entity/mob/EndermanEntity$PickUpBlockGoal")
public class EndermanEntityPickUpBlockGoalMixin {

    /**
     * @see DisableMobGriefingUpgrade
     */
    @Inject(method = "canStart", at = @At("HEAD"), cancellable = true)
    private void disableStart(CallbackInfoReturnable<Boolean> cir) {
        EndermanEntityPickUpBlockGoalAccessor accessor = (EndermanEntityPickUpBlockGoalAccessor) this;
        DisableMobGriefingUpgrade.INSTANCE.disableEndermanBlockPickup(accessor.getEnderman().getPos(), cir);
    }
}
