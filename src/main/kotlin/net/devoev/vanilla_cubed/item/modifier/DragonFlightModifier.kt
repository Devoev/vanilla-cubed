package net.devoev.vanilla_cubed.item.modifier

import net.devoev.vanilla_cubed.entity.wearsDragonScale
import net.devoev.vanilla_cubed.mixin.EntityMixin
import net.devoev.vanilla_cubed.text.translatableTextOf
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.damage.DamageTypes
import net.minecraft.text.Text
import net.minecraft.util.Formatting
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable

/**
 * Makes the wearer of a full dragon scale armor invulnerable to flying damage,
 * by returning `true` in [LivingEntity.isInvulnerableTo].
 * @see EntityMixin.setInvulnerableToFlyingDamage
 */
fun setInvulnerableToFlyingDamage(entity: LivingEntity, damageSource: DamageSource, cir: CallbackInfoReturnable<Boolean>) {
    if (entity.wearsDragonScale()
        && entity.isFallFlying
        && (damageSource.isOf(DamageTypes.FALL) || damageSource.isOf(DamageTypes.FLY_INTO_WALL))
    ) {
        cir.returnValue = true
    }
}

val DRAGON_FLIGHT_TEXT: Text = translatableTextOf("modifier", "dragon_flight").formatted(Formatting.BLUE)