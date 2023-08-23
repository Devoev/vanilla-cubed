package net.devoev.vanilla_cubed.mixin;

import net.minecraft.entity.vehicle.BoatEntity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BoatEntity.class)
public class BoatEntityMixin {

//    @Inject(method = "updateVelocity", at=@At("RETURN"))
//    private void increaseVelocity(CallbackInfo ci) {
//        BoatEntity entity = (BoatEntity) (Object) this;
//        IncreaseVehicleSpeed.INSTANCE.injectBoat(entity);
//    }
}
