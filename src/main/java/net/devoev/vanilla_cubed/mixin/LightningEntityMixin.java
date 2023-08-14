package net.devoev.vanilla_cubed.mixin;

import net.devoev.vanilla_cubed.block.entity.beacon_upgrade.DisableEnvironmentalDamageUpgrade;
import net.minecraft.entity.LightningEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LightningEntity.class)
public class LightningEntityMixin {

    @Inject(method="spawnFire", at=@At("HEAD"), cancellable=true)
    private void disableSpawnFire(int spreadAttempts, CallbackInfo ci) {
        DisableEnvironmentalDamageUpgrade.INSTANCE.injectLightningEntity((LightningEntity)(Object)(this), ci);
    }
}
