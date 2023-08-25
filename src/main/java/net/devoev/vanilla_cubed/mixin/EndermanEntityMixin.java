package net.devoev.vanilla_cubed.mixin;

import net.devoev.vanilla_cubed.block.entity.beacon_upgrade.DisableMobGriefingUpgrade;
import net.devoev.vanilla_cubed.item.ItemKt;
import net.minecraft.block.BlockState;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
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
        for (ItemStack stack : player.getArmorItems())
            if (ItemKt.isEnderite(stack.getItem())) info.setReturnValue(false);
    }

    @Inject(method = "getCarriedBlock", at = @At("HEAD"), cancellable = true)
    private void removeCarriedBlock(CallbackInfoReturnable<BlockState> cir) {
        // TODO: Test if this is working
        EndermanEntity entity = (EndermanEntity)(Object) this;
        DisableMobGriefingUpgrade.INSTANCE.disableEndermanBlockPickup(entity.getPos(), cir);
    }
}
