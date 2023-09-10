package net.devoev.vanilla_cubed.mixin;

import net.devoev.vanilla_cubed.item.modifier.MagneticModifierKt;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Item.class)
public class ItemMixin {

    /**
     * @see MagneticModifierKt
     */
    @Inject(method = "inventoryTick", at = @At("HEAD"))
    private void setMagneticModifierToNetherite(ItemStack stack, World world, Entity entity, int slot, boolean selected, CallbackInfo ci) {
        MagneticModifierKt.setMagneticModifierToNetherite(stack, world, entity, slot, selected);
    }
}
