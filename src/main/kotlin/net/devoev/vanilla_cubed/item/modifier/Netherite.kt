package net.devoev.vanilla_cubed.item.modifier

import net.devoev.vanilla_cubed.item.*
import net.devoev.vanilla_cubed.mixin.ItemMixin
import net.devoev.vanilla_cubed.text.translatableTextOf
import net.minecraft.item.ArmorItem
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.ToolItem
import net.minecraft.text.Text
import net.minecraft.util.Formatting

/**
 * Appends the modifier tooltips to netherite gear by injecting into [Item.appendTooltip].
 * @see ItemMixin
 */
fun appendModifierTooltipToNetherite(stack: ItemStack, tooltip: MutableList<Text>) {
    if (!stack.item.isNetherite()) return

    when (stack.item) {
        is ToolItem -> {
            tooltip += modifierTextOf(false, BURN_RESISTANT_TEXT)
            if (stack.magnetic)
                tooltip += whenInHandTextOf(true, MAGNETIC_TEXT)
        }
        is ArmorItem -> {
            tooltip += modifierTextOf(false, BURN_RESISTANT_TEXT)
            tooltip += whenOnFireTextOf(true, BERSERK_TEXT)
        }
    }
}

val BURN_RESISTANT_TEXT: Text = translatableTextOf("modifier", "burn_resistant").formatted(Formatting.BLUE)

