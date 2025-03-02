package net.devoev.vanilla_cubed.item.trim


import net.devoev.vanilla_cubed.mixin.ArmorTrimMixin
import net.minecraft.item.Item.TooltipContext
import net.minecraft.item.ItemStack
import net.minecraft.item.equipment.trim.ArmorTrim
import net.minecraft.item.tooltip.TooltipType
import net.minecraft.registry.DynamicRegistryManager
import net.minecraft.screen.ScreenTexts
import net.minecraft.text.Text
import java.util.function.Consumer

/**
 * Appends a [ScreenTexts.EMPTY] tooltip at the top of the armor trim tooltip in [ArmorTrim.appendTooltip].
 * @see ArmorTrimMixin.appendTooltipSeparator
 */
fun appendTooltipSeparator(context: TooltipContext, tooltip: Consumer<Text>, type: TooltipType) {
    // TODO: fix code
    if (ArmorTrim.getTrim(registryManager, stack).isPresent && tooltip.size > 1) {
        tooltip += ScreenTexts.EMPTY
    }
}