package net.devoev.vanilla_cubed.mixin;

import net.devoev.vanilla_cubed.item.GildedBookKt;
import net.devoev.vanilla_cubed.item.modifier.TreasureEnchantingModifierKt;
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
    @ModifyVariable(method = "generateEnchantments", at = @At("HEAD"), argsOnly = true)
    private static boolean generateTreasureEnchantments(boolean treasureAllowed, Random random, ItemStack stack) {
        return TreasureEnchantingModifierKt.generateTreasureEnchantments(treasureAllowed, stack);
    }

    /**
     * Filters out the curses, if the item is made of ancient gold.
     */
    @Inject(method = "generateEnchantments", at = @At("RETURN"), cancellable = true)
    private static void generateNoCurses(Random random, ItemStack stack, int level, boolean treasureAllowed, CallbackInfoReturnable<List<EnchantmentLevelEntry>> cir) {
        TreasureEnchantingModifierKt.generateNoCurses(stack, cir);
    }

    /**
     * @see GildedBookKt
     */
    @ModifyVariable(method = "getPossibleEntries", at = @At("STORE"), ordinal = 1)
    private static boolean addGildedBookEntry(boolean bl, int power, ItemStack stack) {
        return GildedBookKt.addGildedBookEntry(bl, stack);
    }
}
