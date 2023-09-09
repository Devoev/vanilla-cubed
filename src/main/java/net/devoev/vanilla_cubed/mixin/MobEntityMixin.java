package net.devoev.vanilla_cubed.mixin;

import net.devoev.vanilla_cubed.block.entity.beacon.upgrades.IncreaseXPDropUpgrade;
import net.minecraft.entity.mob.MobEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MobEntity.class)
public class MobEntityMixin {

    @Inject(method = "getXpToDrop", at = @At("RETURN"), cancellable = true)
    private void increaseXpDrop(CallbackInfoReturnable<Integer> cir) {
        MobEntity entity = (MobEntity)(Object)this;
        IncreaseXPDropUpgrade.INSTANCE.increaseXP(entity.getPos(), cir);
    }
}
