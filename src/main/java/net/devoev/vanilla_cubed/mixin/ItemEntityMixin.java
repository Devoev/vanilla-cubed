package net.devoev.vanilla_cubed.mixin;

import net.devoev.vanilla_cubed.item.modifier.NoGravityBehaviorKt;
import net.minecraft.entity.ItemEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntity.class)
public class ItemEntityMixin {

    /**
     * @see NoGravityBehaviorKt
     */
    @Inject(method = "tick", at = @At("HEAD"))
    private void setNoGravityEnderiteGear(CallbackInfo ci) {
        ItemEntity entity = (ItemEntity) (Object) this;
        NoGravityBehaviorKt.setNoGravityOfEnderiteGear(entity);
    }

    /**
     * @see NoGravityBehaviorKt
     */
    @Inject(method = "tick", at = @At("HEAD"))
    private void setNoGravityMinedByEnderite(CallbackInfo info) {
        ItemEntity entity = (ItemEntity) (Object) this;
        NoGravityBehaviorKt.setNoGravityOfMinedByEnderite(entity);
    }
}
