package net.devoev.vanilla_cubed.mixin;

import net.devoev.vanilla_cubed.block.entity.beacon_upgrade.DisableMobGriefingUpgrade;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.world.explosion.Explosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(WitherEntity.class)
public class WitherEntityMixin {

    @SuppressWarnings("All")
    @ModifyVariable(method = "mobTick", at = @At(value = "STORE"))
    private Explosion.DestructionType disableExplosion(Explosion.DestructionType type) {
        WitherEntity entity = (WitherEntity)(Object)this;
        return DisableMobGriefingUpgrade.INSTANCE.injectExplosionDestructionType(entity.getBlockPos(), type);
    }
}
