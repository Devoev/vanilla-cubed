package net.devoev.vanilla_cubed.mixin;

import net.devoev.vanilla_cubed.item.GildedBookKt;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.GrindstoneScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GrindstoneScreenHandler.class)
public class GrindstoneScreenHandlerMixin {

    /**
     * @see GildedBookKt
     */
    @Inject(method = "grind", at = @At("RETURN"), cancellable = true)
    private void grindGildedBook(ItemStack item, int damage, int amount, CallbackInfoReturnable<ItemStack> cir) {
        GildedBookKt.grindGildedBook(item, cir);
    }
}