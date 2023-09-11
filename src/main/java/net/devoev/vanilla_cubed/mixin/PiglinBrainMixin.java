package net.devoev.vanilla_cubed.mixin;

import net.devoev.vanilla_cubed.item.modifier.PreventPiglinAggressionModifierKt;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Iterator;

/**
 * A mixin to change the behavior of piglins.
 */
@Mixin(PiglinBrain.class)
public class PiglinBrainMixin {

    /**
     * Piglins won't attack players wearing ancient gold armor.
     */
    @SuppressWarnings("ALL")
    @Inject(method = "wearsGoldArmor", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/item/ItemStack;getItem()Lnet/minecraft/item/Item;"), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
    private static void wearsAncientGoldArmor(LivingEntity entity, CallbackInfoReturnable<Boolean> cir, Iterable<ItemStack> iterable, Iterator iterator, ItemStack stack, Item item) {
        PreventPiglinAggressionModifierKt.preventPiglinAggression(item, cir);
    }
}
