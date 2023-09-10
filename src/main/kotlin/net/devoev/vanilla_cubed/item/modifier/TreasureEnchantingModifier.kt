package net.devoev.vanilla_cubed.item.modifier

import net.devoev.vanilla_cubed.item.isAncientGold
import net.minecraft.enchantment.EnchantmentLevelEntry
import net.minecraft.item.ItemStack
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable

// TODO: Add modifier that doesn't explicitly depend on ancient gold

/**
 * Sets the [treasureAllowed] parameter to `true`, if the item of the [stack] is made of ancient gold.
 */
fun generateTreasureEnchantments(treasureAllowed: Boolean, stack: ItemStack): Boolean {
    return treasureAllowed || stack.item.isAncientGold()
}

/**
 * Filters out the curses, if the item of the given [stack] is made of ancient gold.
 */
fun generateNoCurses(stack: ItemStack, cir: CallbackInfoReturnable<List<EnchantmentLevelEntry>>) {
    if (stack.item.isAncientGold()) {
        cir.returnValue = cir.returnValue.filter { !it.enchantment.isCursed }
    }
}