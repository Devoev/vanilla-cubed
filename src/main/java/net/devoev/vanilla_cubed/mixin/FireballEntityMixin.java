package net.devoev.vanilla_cubed.mixin;

import net.devoev.vanilla_cubed.block.entity.beacon.upgrades.DisableMobGriefingUpgrade;
import net.minecraft.entity.projectile.FireballEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(FireballEntity.class)
public class FireballEntityMixin {

    /**
     * @see DisableMobGriefingUpgrade
     */
    @SuppressWarnings("All")
    @ModifyVariable(method = "onCollision", at = @At(value = "STORE"), ordinal = 0)
    private boolean disableFire(boolean bl) {
        FireballEntity entity = (FireballEntity)(Object)this;
        return DisableMobGriefingUpgrade.INSTANCE.disableFire(entity.getPos(), bl);
    }
}
