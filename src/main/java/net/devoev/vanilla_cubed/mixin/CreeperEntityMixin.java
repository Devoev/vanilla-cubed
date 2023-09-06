package net.devoev.vanilla_cubed.mixin;

import net.minecraft.entity.mob.CreeperEntity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(CreeperEntity.class)
public class CreeperEntityMixin {

//    /**
//     * @see DisableMobGriefingUpgrade
//     */
//    @SuppressWarnings("All")
//    @ModifyVariable(method = "explode", at = @At(value = "STORE"))
//    private Explosion.DestructionType disableExplosion(Explosion.DestructionType type) {
//        CreeperEntity entity = (CreeperEntity)(Object)this;
//        return DisableMobGriefingUpgrade.INSTANCE.disableExplosion(entity.getPos(), type);
//    }
}
