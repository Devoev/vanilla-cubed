package net.devoev.vanilla_cubed.mixin;

import net.devoev.vanilla_cubed.item.ModItems;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
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

    //@ModifyVariable(method = "onButtonClick", at = @At("STORE"), ordinal = 0)
    private boolean replaceGildedBooksWithEnchantedBooks(boolean bl) {
        System.out.println("onButtonClick modify variable");
        //ItemStack stack = this.inventory.getStack(0);
        return bl;
        //return stack.isOf(ModItems.INSTANCE.getGILDED_BOOK()) || bl;
    }

    /**
     * Makes it possible to enchant gilded books, by replacing the enchanted ones with Enchanted Book items.
     * Also sets the "gilded" NBT tag to true, for the custom texture to work.
     */
    @Inject(method = "onButtonClick", at = @At("RETURN"))
    public void replaceGildedBooksWithEnchantedBooks(CallbackInfoReturnable<Boolean> info) {
        ItemStack stack = inventory.getStack(0);
        if (!stack.isOf(ModItems.INSTANCE.getGILDED_BOOK())) return;

        Map<Enchantment, Integer> enchantments = EnchantmentHelper.fromNbt(stack.getEnchantments());
        NbtCompound nbtCompound = stack.getNbt();
        if (nbtCompound == null) nbtCompound = new NbtCompound();

        NbtCompound display = nbtCompound.getCompound("display");
        NbtCompound nbt = new NbtCompound();
        nbt.put("display", display);
        nbt.putBoolean("gilded", true);
        stack = new ItemStack(Items.ENCHANTED_BOOK);
        stack.setNbt(nbt.copy());

        ItemStack finalStack = stack;
        enchantments.forEach((enchantment, lvl) ->
                EnchantedBookItem.addEnchantment(finalStack, new EnchantmentLevelEntry(enchantment, lvl)));

        inventory.setStack(0, stack);
    }
}
