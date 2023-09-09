package net.devoev.vanilla_cubed.mixin;

import net.devoev.vanilla_cubed.block.entity.beacon.upgrades.IncreaseMinecartSpeedUpgrade;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractMinecartEntity.class)
public class AbstractMinecartEntityMixin {

    @Inject(method = "getMaxSpeed", at=@At("RETURN"), cancellable = true)
    private void increaseMaxSpeed(CallbackInfoReturnable<Double> cir) {
        AbstractMinecartEntity entity = (AbstractMinecartEntity)(Object)this;
        IncreaseMinecartSpeedUpgrade.INSTANCE.increaseMaxSpeed(entity.getBlockPos(), entity.getWorld(), cir);
    }
}
