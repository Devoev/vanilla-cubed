package net.devoev.vanilla_cubed.mixin;

import net.devoev.vanilla_cubed.util.ItemKt;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.Random;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {

    /**
     * Sets the treasureAllowed parameter to true, if the item is made of ancient gold.
     */
    @ModifyVariable(method = "generateEnchantments", at = @At("HEAD"))
    private static boolean allowTreasureEnchantments(boolean treasureAllowed, Random random, ItemStack stack) {
        return ItemKt.isAncientGold(stack.getItem()) || treasureAllowed;
    }
}
