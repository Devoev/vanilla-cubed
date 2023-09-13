package net.devoev.vanilla_cubed.item.trim


import net.devoev.vanilla_cubed.mixin.ArmorTrimMixin
import net.minecraft.item.ItemStack
import net.minecraft.item.trim.ArmorTrim
import net.minecraft.registry.DynamicRegistryManager
import net.minecraft.screen.ScreenTexts
import net.minecraft.text.Text

/**
 * Appends a [ScreenTexts.EMPTY] tooltip at the top of the armor trim tooltip in [ArmorTrim.appendTooltip].
 * @see ArmorTrimMixin.appendTooltipSpace
 */
fun appendTooltipSpace(stack: ItemStack, registryManager: DynamicRegistryManager, tooltip: MutableList<Text>) {
    if (ArmorTrim.getTrim(registryManager, stack).isPresent) {
        tooltip += ScreenTexts.EMPTY
    }
}