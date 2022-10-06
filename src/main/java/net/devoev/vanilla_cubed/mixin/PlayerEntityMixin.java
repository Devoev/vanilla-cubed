package net.devoev.vanilla_cubed.mixin;

import net.devoev.vanilla_cubed.item.ItemStackKt;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {

    /**
     * Sets dropped by player value of the dropped stack to true.
     */
    @Inject(method = "dropItem(Lnet/minecraft/item/ItemStack;ZZ)Lnet/minecraft/entity/ItemEntity;", at = @At("HEAD"))
    private void setDroppedByPlayer(ItemStack stack, boolean throwRandomly, boolean retainOwnership, CallbackInfoReturnable<ItemEntity> info) {
        if (!((Object) this instanceof PlayerEntity player)) return;
        if (player.world.isClient) return;

        if (!stack.isEmpty())
            ItemStackKt.setDroppedByPlayer(stack, true);
    }
}
