package net.devoev.vanilla_cubed.mixin;

import net.minecraft.entity.boss.WitherEntity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(WitherEntity.class)
public class WitherEntityMixin {

//    @SuppressWarnings("All")
//    @ModifyVariable(method = "mobTick", at = @At(value = "STORE"))
//    private Explosion.DestructionType disableExplosion(Explosion.DestructionType type) {
//        WitherEntity entity = (WitherEntity)(Object)this;
//        return DisableMobGriefingUpgrade.INSTANCE.disableExplosion(entity.getPos(), type);
//    }
}
