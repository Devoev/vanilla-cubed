package net.devoev.vanilla_cubed.mixin;

import net.minecraft.entity.projectile.WitherSkullEntity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(WitherSkullEntity.class)
public class WitherSkullEntityMixin {

//    /**
//     * @see DisableMobGriefingUpgrade
//     */
//    @SuppressWarnings("All")
//    @ModifyVariable(method = "onCollision", at = @At(value = "STORE"))
//    private Explosion.DestructionType disableExplosion(Explosion.DestructionType type) {
//        WitherSkullEntity entity = (WitherSkullEntity)(Object)this;
//        return DisableMobGriefingUpgrade.INSTANCE.disableExplosion(entity.getPos(), type);
//    }
}
