package net.devoev.vanilla_cubed.mixin;

import net.devoev.vanilla_cubed.item.TotemOfUndyingKt;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(LivingEntity.class)
abstract public class LivingEntityMixin {

    /**
     * @see TotemOfUndyingKt
     */
    @SuppressWarnings("ALL")
    @ModifyVariable(method = "tryUseTotem", at = @At(value = "STORE"), ordinal = 0)
    private ItemStack useTotem(ItemStack stack) {
        if (!((Object) this instanceof PlayerEntity player)) return stack;

        return TotemOfUndyingKt.useTotem(player, stack);
    }
}
