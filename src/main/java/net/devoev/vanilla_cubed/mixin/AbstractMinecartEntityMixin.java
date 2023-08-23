package net.devoev.vanilla_cubed.mixin;

import net.devoev.vanilla_cubed.block.entity.beacon_upgrade.IncreaseVehicleSpeed;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractMinecartEntity.class)
public class AbstractMinecartEntityMixin {

    @Inject(method = "getVelocityMultiplier", at=@At("HEAD"), cancellable = true)
    private void increaseVelocityMultiplier(CallbackInfoReturnable<Float> cir) {
        AbstractMinecartEntity entity = (AbstractMinecartEntity)(Object)this;
        IncreaseVehicleSpeed.INSTANCE.injectMinecart(entity.getBlockPos(), entity.world, cir);
    }
}
