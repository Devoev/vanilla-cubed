package net.devoev.vanilla_cubed.mixin;

import net.devoev.vanilla_cubed.entity.effect.StatusEffectHelper;
import net.devoev.vanilla_cubed.item.ModItems;
import net.devoev.vanilla_cubed.util.LivingEntityKt;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
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
     * Charges the amethyst crystal when struck by lightning.
     */
    @Inject(method = "onStruckByLightning", at = @At("HEAD"))
    private void chargeAmethystCrystal(ServerWorld world, LightningEntity lightning, CallbackInfo info) {
        if (!( (Object) this instanceof ItemEntity item && item.getStack().getItem().equals(ModItems.INSTANCE.getAMETHYST_CRYSTAL()))) return;

        item.setStack(new ItemStack(ModItems.INSTANCE.getCHARGED_AMETHYST_CRYSTAL(), item.getStack().getCount()));
        item.setInvulnerable(true);
        item.setInvisible(false);
        item.setOnFire(false);
    }

    /**
     * Full set of dragon scale armor negates all flying collision damage.
     */
    @Inject(method = "isInvulnerableTo", at = @At("HEAD"), cancellable = true)
    private void isInvulnerableToFlyingDamage(DamageSource damageSource, CallbackInfoReturnable<Boolean> info) {
        if (!((Object) this instanceof LivingEntity entity)) return;
        if (LivingEntityKt.wearsDragonScale(entity) && entity.isFallFlying()
                && (damageSource.isFromFalling() || damageSource.equals(DamageSource.FLY_INTO_WALL)))
            info.setReturnValue(true);
    }

    /**
     * Full set of netherite armor grants regeneration and strength, when player is on fire.
     */
    @Inject(method = "baseTick", at = @At("HEAD"))
    private void applyEffectsWhenOnFire(CallbackInfo info) {
        if (!((Object) this instanceof LivingEntity entity)) return;
        if (!entity.isOnFire() || !LivingEntityKt.wearsNetherite(entity)) return;

        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 300, 1));
        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 300, 1));
    }
}
