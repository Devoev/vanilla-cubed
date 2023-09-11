package net.devoev.vanilla_cubed.mixin;

import net.devoev.vanilla_cubed.block.entity.beacon.upgrades.DisableMobGriefingUpgrade;
import net.devoev.vanilla_cubed.item.DragonScaleKt;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnderDragonEntity.class)
public class EnderDragonEntityMixin extends MobEntity {

    @Shadow
    public int ticksSinceDeath;

    protected EnderDragonEntityMixin(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * @see DragonScaleKt
     */
    @Inject(method = "updatePostDeath", at = @At("HEAD"))
    private void dropDragonScale(CallbackInfo info) {
        DragonScaleKt.dropDragonScale(this, ticksSinceDeath);
    }

    /**
     * @see DisableMobGriefingUpgrade
     */
    @Inject(method = "destroyBlocks", at = @At("HEAD"), cancellable = true)
    private void disableEnderDragonBlockDestruction(Box box, CallbackInfoReturnable<Boolean> cir) {
        EnderDragonEntity entity = (EnderDragonEntity) (Object) this;
        DisableMobGriefingUpgrade.INSTANCE.disableEnderDragonBlockDestruction(entity.getPos(), cir);
    }
}
