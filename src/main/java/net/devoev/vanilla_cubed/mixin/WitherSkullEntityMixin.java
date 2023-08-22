package net.devoev.vanilla_cubed.mixin;

import net.devoev.vanilla_cubed.block.entity.beacon_upgrade.DisableMobGriefingUpgrade;
import net.minecraft.entity.projectile.WitherSkullEntity;
import net.minecraft.world.explosion.Explosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(WitherSkullEntity.class)
public class WitherSkullEntityMixin {

    @SuppressWarnings("All")
    @ModifyVariable(method = "onCollision", at = @At(value = "STORE"))
    private Explosion.DestructionType disableExplosion(Explosion.DestructionType type) {
        WitherSkullEntity entity = (WitherSkullEntity)(Object)this;
        return DisableMobGriefingUpgrade.INSTANCE.injectExplosionDestructionType(entity.getBlockPos(), type);
    }
}
