package net.devoev.vanilla_cubed.mixin;

import net.devoev.vanilla_cubed.block.entity.beacon_upgrade.DisableMobGriefingUpgrade;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.world.explosion.Explosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(CreeperEntity.class)
public class CreeperEntityMixin {

    @SuppressWarnings("All")
    @ModifyVariable(method = "explode", at = @At(value = "STORE"))
    private Explosion.DestructionType disableExplosion(Explosion.DestructionType type) {
        CreeperEntity entity = (CreeperEntity)(Object)this;
        return DisableMobGriefingUpgrade.INSTANCE.injectExplosionDestructionType(entity.getPos(), type);
    }
}
