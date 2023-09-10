package net.devoev.vanilla_cubed.mixin;

import net.devoev.vanilla_cubed.item.GildedBookKt;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.EnchantmentScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchantmentScreenHandler.class)
public abstract class EnchantmentScreenHandlerMixin extends ScreenHandler {

    @Shadow
    @Final
    private Inventory inventory;

    public EnchantmentScreenHandlerMixin(ScreenHandlerType<?> type, int syncId) {
        super(type, syncId);
    }

    /**
     * @see GildedBookKt
     */
    @Inject(method = "onButtonClick", at = @At("RETURN"))
    public void enchantGildedBook(CallbackInfoReturnable<Boolean> cir) {
        GildedBookKt.enchantGildedBook(inventory);
    }
}
