package net.devoev.vanilla_cubed.mixin;

import net.devoev.vanilla_cubed.entity.effect.StatusEffectKt;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(StatusEffect.class)
public class StatusEffectMixin {

    /**
     * @see StatusEffectKt
     */
    @Inject(method = "onApplied", at=@At("RETURN"))
    private void applyHealthBoost(LivingEntity entity, AttributeContainer attributes, int amplifier, CallbackInfo ci) {
        StatusEffect effect = (StatusEffect) (Object) this;
        StatusEffectKt.applyHealthBoost(effect, entity);
    }
}
