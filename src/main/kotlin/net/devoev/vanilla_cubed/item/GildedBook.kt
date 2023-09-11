package net.devoev.vanilla_cubed.item

import net.devoev.vanilla_cubed.mixin.EnchantmentHelperMixin
import net.devoev.vanilla_cubed.mixin.EnchantmentScreenHandlerMixin
import net.devoev.vanilla_cubed.mixin.GrindstoneScreenHandlerMixin
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.inventory.Inventory
import net.minecraft.item.BookItem
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.screen.GrindstoneScreenHandler
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable

class GildedBook : BookItem(FabricItemSettings()) {

    override fun getEnchantability(): Int = 25
}

/**
 * Allows gilded books to be enchanted, by setting the local [bl][isBook] variable to `true`.
 * @see EnchantmentHelperMixin.addGildedBookEntry
 */
fun addGildedBookEntry(isBook: Boolean, stack: ItemStack): Boolean {
    return stack.isOf(ModItems.GILDED_BOOK) || isBook;
}

/**
 * Makes it possible to enchant gilded books, by replacing the enchanted ones with [Items.ENCHANTED_BOOK].
 *
 * Also sets the `gilded` NBT tag to `true`, for the custom texture to work.
 *
 * @see EnchantmentScreenHandlerMixin.enchantGildedBook
 */
fun enchantGildedBook(inventory: Inventory) {
    val stack = inventory.getStack(0)
    if (!stack.isOf(ModItems.GILDED_BOOK)) return

    val res = ItemStack(Items.ENCHANTED_BOOK)
    res.gilded = true
    if (stack.hasCustomName()) res.setCustomName(stack.getName())

    val enchantments = EnchantmentHelper.fromNbt(stack.getEnchantments())
    EnchantmentHelper.set(enchantments, res)

    inventory.setStack(0, res)
}

/**
 * Returns gilded books instead of normal books, when enchanted gilded books get grinded
 * by modifying the return value of [GrindstoneScreenHandler.grind].
 * @see GrindstoneScreenHandlerMixin
 */
fun grindGildedBook(original: ItemStack, cir: CallbackInfoReturnable<ItemStack>) {
    val stack: ItemStack = cir.returnValue
    if (!original.gilded) return

    val res = ItemStack(ModItems.GILDED_BOOK, stack.count)
    res.setNbt(stack.nbt)
    cir.returnValue = res
}