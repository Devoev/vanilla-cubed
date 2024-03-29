package net.devoev.vanilla_cubed.mixin;

import net.devoev.vanilla_cubed.entity.PlayerEntityKt;
import net.devoev.vanilla_cubed.item.modifier.ProjectileShieldModifierKt;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {

    /**
     * Sets dropped by player value of the dropped item to true.
     */
    @SuppressWarnings("ALL")
    @ModifyVariable(method = "dropItem(Lnet/minecraft/item/ItemStack;ZZ)Lnet/minecraft/entity/ItemEntity;", at = @At("STORE"), ordinal = 0)
    private ItemEntity setDroppedByPlayer(ItemEntity itemEntity) {
        return PlayerEntityKt.setDroppedByPlayer(itemEntity);
    }

    /**
     * @see ProjectileShieldModifierKt
     */
    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private void protectFromProjectiles(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if ((Object) this instanceof PlayerEntity player) {
            ProjectileShieldModifierKt.protectFromProjectiles(player, source, cir);
        }
    }
}
