package net.devoev.vanilla_cubed.mixin;

import net.devoev.vanilla_cubed.item.ModItems;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
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

//    @ModifyVariable(method = "onButtonClick", at = @At("STORE"), ordinal = 0)
//    private boolean replaceGildedBooksWithEnchantedBooks(boolean bl) {
//        System.out.println("onButtonClick modify variable");
//        ItemStack stack = this.inventory.getStack(0);
//        return stack.isOf(ModItems.INSTANCE.getGILDED_BOOK()) || bl;
//    }

    @Inject(method = "onButtonClick", at = @At("RETURN"))
    public void test(CallbackInfoReturnable<Boolean> info) {
        System.out.println("test mixin onButtonClick");
        ItemStack stack = this.inventory.getStack(0);
        if (!stack.isOf(ModItems.INSTANCE.getGILDED_BOOK())) return;

        NbtCompound nbtCompound = stack.getNbt();
        stack = new ItemStack(Items.ENCHANTED_BOOK);
        if (nbtCompound != null) {
            stack.setNbt(nbtCompound.copy());
        }

        this.inventory.setStack(0, stack);
    }
}
