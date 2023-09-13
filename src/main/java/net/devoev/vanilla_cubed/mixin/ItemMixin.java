package net.devoev.vanilla_cubed.mixin;

import net.devoev.vanilla_cubed.item.modifier.BerserkModifierKt;
import net.devoev.vanilla_cubed.item.modifier.MagneticModifierKt;
import net.devoev.vanilla_cubed.item.modifier.NetheriteKt;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(Item.class)
public class ItemMixin {

    /**
     * @see MagneticModifierKt
     */
    @Inject(method = "inventoryTick", at = @At("HEAD"))
    private void setMagneticModifierToNetherite(ItemStack stack, World world, Entity entity, int slot, boolean selected, CallbackInfo ci) {
        MagneticModifierKt.setMagneticModifierToNetherite(stack, world, entity, slot, selected);
    }

    /**
     * @see BerserkModifierKt
     */
    @Inject(method = "inventoryTick", at = @At("HEAD"))
    private void setBerserkModifierToNetherite(ItemStack stack, World world, Entity entity, int slot, boolean selected, CallbackInfo ci) {
        BerserkModifierKt.setBerserkModifierToNetherite(stack, world, entity, slot, selected);
    }

    /**
     * @see NetheriteKt
     */
    @Inject(method = "appendTooltip", at = @At("HEAD"))
    private void appendModifierTooltipToNetherite(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context, CallbackInfo ci) {
        NetheriteKt.appendModifierTooltipToNetherite(stack, tooltip);
    }
}
