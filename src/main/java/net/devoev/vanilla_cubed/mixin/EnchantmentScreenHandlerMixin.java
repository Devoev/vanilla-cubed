package net.devoev.vanilla_cubed.mixin;

import net.devoev.vanilla_cubed.item.ModItems;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.EnchantmentScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchantmentScreenHandler.class)
public abstract class EnchantmentScreenHandlerMixin extends ScreenHandler {

    @Shadow
    @Final
    private Inventory inventory;

    public EnchantmentScreenHandlerMixin(ScreenHandlerType<?> type, int syncId) {
        super(type, syncId);
    }

    @ModifyVariable(method = "onButtonClick", at = @At("STORE"), ordinal = 1)
    private boolean replaceGildedBooksWithEnchantedBooks(boolean bl) {
        System.out.println("onButtonClick mixin");
        ItemStack stack = this.inventory.getStack(0);
        return stack.isOf(ModItems.INSTANCE.getGILDED_BOOK()) || bl;
    }

    @Inject(method = "onButtonClick", at = @At("HEAD"))
    public void test(CallbackInfoReturnable<Boolean> info) {
        System.out.println("test mixin generateEnchantments");
    }
}
