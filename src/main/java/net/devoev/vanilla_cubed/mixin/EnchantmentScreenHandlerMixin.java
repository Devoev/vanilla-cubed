package net.devoev.vanilla_cubed.mixin;

import net.devoev.vanilla_cubed.item.ItemStackKt;
import net.devoev.vanilla_cubed.item.ModItems;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.EnchantmentScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(EnchantmentScreenHandler.class)
public abstract class EnchantmentScreenHandlerMixin extends ScreenHandler {

    @Shadow
    @Final
    private Inventory inventory;

    public EnchantmentScreenHandlerMixin(ScreenHandlerType<?> type, int syncId) {
        super(type, syncId);
    }

    /**
     * Makes it possible to enchant gilded books, by replacing the enchanted ones with Enchanted Book items.
     * Also sets the "gilded" NBT tag to true, for the custom texture to work.
     */
    @Inject(method = "onButtonClick", at = @At("RETURN"))
    public void replaceGildedBooksWithEnchantedBooks(CallbackInfoReturnable<Boolean> info) {
        ItemStack stack = inventory.getStack(0);
        if (!stack.isOf(ModItems.INSTANCE.getGILDED_BOOK())) return;

        ItemStack res = new ItemStack(Items.ENCHANTED_BOOK);
        ItemStackKt.setGilded(res, true);
        if (stack.hasCustomName())
            res.setCustomName(stack.getName());

        Map<Enchantment, Integer> enchantments = EnchantmentHelper.fromNbt(stack.getEnchantments());
        EnchantmentHelper.set(enchantments, res);

        inventory.setStack(0, res);
    }
}
