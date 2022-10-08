package net.devoev.vanilla_cubed.mixin;

import net.devoev.vanilla_cubed.entity.ItemEntityKt;
import net.devoev.vanilla_cubed.item.ItemStackKt;
import net.devoev.vanilla_cubed.item.behavior.ProjectileShieldKt;
import net.devoev.vanilla_cubed.util.LivingEntityKt;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
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
    @ModifyVariable(method = "dropItem(Lnet/minecraft/item/ItemStack;ZZ)Lnet/minecraft/entity/ItemEntity;", at = @At("STORE"), ordinal = 0)
    private ItemEntity setDroppedByPlayer(ItemEntity itemEntity) {
        if (itemEntity.world.isClient) return itemEntity;
        ItemEntityKt.setDroppedByPlayer(itemEntity, true);
        return itemEntity;
    }

    /**
     * Stops projectiles from hitting the player if he wears full enderite armor.
     */
    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private void stopProjectiles(DamageSource source, float amount, CallbackInfoReturnable<Boolean> info) {
        if (!((Object) this instanceof PlayerEntity player)) return;
        boolean res = ProjectileShieldKt.protectFromProjectiles(player, source);
        if (res) info.setReturnValue(false);
    }
}
