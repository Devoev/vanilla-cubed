package net.devoev.vanilla_cubed.mixin;

import net.devoev.vanilla_cubed.item.trim.ArmorTrimKt;
import net.minecraft.item.ItemStack;
import net.minecraft.item.trim.ArmorTrim;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ArmorTrim.class)
public class ArmorTrimMixin {

    /**
     * @see ArmorTrimKt
     */
    @Inject(method = "appendTooltip", at = @At("HEAD"))
    private static void appendTooltipSpace(ItemStack stack, DynamicRegistryManager registryManager, List<Text> tooltip, CallbackInfo ci) {
        ArmorTrimKt.appendTooltipSpace(stack, registryManager, tooltip);
    }
}
