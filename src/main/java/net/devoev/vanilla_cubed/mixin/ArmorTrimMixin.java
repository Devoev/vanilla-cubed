package net.devoev.vanilla_cubed.mixin;

import net.devoev.vanilla_cubed.item.trim.ArmorTrimKt;
import net.minecraft.item.Item;
import net.minecraft.item.equipment.trim.ArmorTrim;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(ArmorTrim.class)
public class ArmorTrimMixin {

    /**
     * @see ArmorTrimKt
     */
    @Inject(method = "appendTooltip", at = @At("HEAD"))
    private void appendTooltipSeparator(Item.TooltipContext context, Consumer<Text> tooltip, TooltipType type, CallbackInfo ci) {
        ArmorTrimKt.appendTooltipSeparator(context, tooltip, type);
    }
}
