package net.devoev.vanilla_cubed.mixin;

import net.devoev.vanilla_cubed.entity.ItemEntityKt;
import net.devoev.vanilla_cubed.entity.LivingEntityKt;
import net.devoev.vanilla_cubed.item.modifier.MagneticModifierKt;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
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
     * Full set of dragon scale armor negates all flying collision damage.
     * TODO: Extract to behavior/upgrade
     */
    @Inject(method = "isInvulnerableTo", at = @At("HEAD"), cancellable = true)
    private void setInvulnerableToFlyingDamage(DamageSource damageSource, CallbackInfoReturnable<Boolean> info) {
        if (!((Object) this instanceof LivingEntity entity)) return;
        if (LivingEntityKt.wearsDragonScale(entity) && entity.isFallFlying()
                && (damageSource.isOf(DamageTypes.FALL) || damageSource.isOf(DamageTypes.FLY_INTO_WALL))) // TODO: Correct type checking?
            info.setReturnValue(true);
    }

    /**
     * Full set of netherite armor grants regeneration and strength, when player is on fire.
     * TODO: Extract to behavior/upgrade
     */
    @Inject(method = "baseTick", at = @At("HEAD"))
    private void applyEffectsWhenOnFire(CallbackInfo info) {
        if (!((Object) this instanceof LivingEntity entity)) return;
        if (!entity.isOnFire() || !LivingEntityKt.wearsNetherite(entity)) return;

        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 300, 1));
        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 300, 1));
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
