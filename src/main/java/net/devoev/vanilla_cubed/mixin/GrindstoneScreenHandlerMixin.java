package net.devoev.vanilla_cubed.mixin;

import net.devoev.vanilla_cubed.item.ItemStackKt;
import net.devoev.vanilla_cubed.item.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.GrindstoneScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GrindstoneScreenHandler.class)
public class GrindstoneScreenHandlerMixin {

    /**
     * Returns gilded books instead of normal books, when enchanted gilded books get grinded.
     */
    @Inject(method = "grind", at = @At("RETURN"), cancellable = true)
    private void grindGildedBooks(ItemStack item, int damage, int amount, CallbackInfoReturnable<ItemStack> info) {
        ItemStack stack = info.getReturnValue();
        if (!ItemStackKt.getGilded(item)) return;

        ItemStack res = new ItemStack(ModItems.INSTANCE.getGILDED_BOOK(), stack.getCount());
        res.setNbt(stack.getNbt());
        info.setReturnValue(res);
    }
}