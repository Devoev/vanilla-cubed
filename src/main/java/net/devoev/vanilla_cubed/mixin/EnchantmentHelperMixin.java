package net.devoev.vanilla_cubed.mixin;

import net.devoev.vanilla_cubed.util.ItemKt;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

/**
 * A mixin to modify the enchantment behavior of ancient gold.
 */
@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {

    /**
     * Sets the treasureAllowed parameter to true, if the item is made of ancient gold.
     */
    @ModifyVariable(method = "generateEnchantments", at = @At("HEAD"))
    private static boolean allowTreasureEnchantments(boolean treasureAllowed, Random random, ItemStack stack) {
        return ItemKt.isAncientGold(stack.getItem()) || treasureAllowed;
    }

    /**
     * Filters out the curses, if the item is made of ancient gold.
     */
    @Inject(method = "generateEnchantments", at = @At("RETURN"), cancellable = true)
    private static void removeCurses(Random random, ItemStack stack, int level, boolean treasureAllowed, CallbackInfoReturnable<List<EnchantmentLevelEntry>> info) {
        if (ItemKt.isAncientGold(stack.getItem()))
            info.setReturnValue(info.getReturnValue().stream().filter(entry -> !entry.enchantment.isCursed()).toList());
    }
}
