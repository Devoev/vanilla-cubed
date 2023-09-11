package net.devoev.vanilla_cubed.mixin;

import net.devoev.vanilla_cubed.entity.ItemEntityKt;
import net.devoev.vanilla_cubed.item.modifier.DragonFlightModifierKt;
import net.devoev.vanilla_cubed.item.modifier.MagneticModifierKt;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * A mixin for the amethyst crystal.
 */
@Mixin(Entity.class)
public class EntityMixin {

    /**
     * @see ItemEntityKt
     */
    @Inject(method = "onStruckByLightning", at = @At("HEAD"))
    private void chargeAmethystCrystal(ServerWorld world, LightningEntity lightning, CallbackInfo info) {
        if (((Object) this instanceof ItemEntity item))
            ItemEntityKt.chargeAmethystCrystalItems(item);
    }

    /**
     * @see DragonFlightModifierKt
     */
    @Inject(method = "isInvulnerableTo", at = @At("HEAD"), cancellable = true)
    private void setInvulnerableToFlyingDamage(DamageSource damageSource, CallbackInfoReturnable<Boolean> cir) {
        if ((Object) this instanceof LivingEntity entity) {
            DragonFlightModifierKt.setInvulnerableToFlyingDamage(entity, damageSource, cir);
        }
    }

    /**
     * @see MagneticModifierKt
     */
    @Inject(method = "baseTick", at = @At("HEAD"))
    private void demagnetize(CallbackInfo info) {
        if ((Object) this instanceof ItemEntity item) {
            MagneticModifierKt.demagnetize(item);
        }
    }
}
