package net.devoev.vanilla_cubed.mixin;

import net.devoev.vanilla_cubed.block.entity.beacon.upgrades.DisableGearDamageUpgrade;
import net.devoev.vanilla_cubed.item.ItemStackKt;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtList;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.random.Random;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(ItemStack.class)
public class ItemStackMixin {

    /**
     * @see DisableGearDamageUpgrade
     */
    @Inject(method = "damage(ILnet/minecraft/util/math/random/Random;Lnet/minecraft/server/network/ServerPlayerEntity;)Z", at = @At("HEAD"), cancellable = true)
    private void preventItemDamage(int amount, Random random, @Nullable ServerPlayerEntity player, CallbackInfoReturnable<Boolean> cir) {
        DisableGearDamageUpgrade.INSTANCE.preventItemDamage(player, cir);
    }

    /**
     * @see ItemStackKt
     */
    @Inject(method = "appendEnchantments", at = @At("HEAD"))
    private static void appendEnchantmentsTooltipSeparator(List<Text> tooltip, NbtList enchantments, CallbackInfo ci) {
        ItemStackKt.appendEnchantmentsTooltipSeparator(tooltip, enchantments);
    }
}
