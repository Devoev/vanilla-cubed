package net.devoev.vanilla_cubed.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.Optional;

@Mixin(LivingEntity.class)
abstract public class LivingEntityMixin {

    /**
     * Makes the totem of undying work from any slot.
     */
    @ModifyVariable(method = "tryUseTotem", at = @At(value = "STORE"), ordinal = 0)
    private ItemStack useTotemAnySlot(ItemStack stack) {
        if (!((Object) this instanceof PlayerEntity player)) return stack;

        Optional<ItemStack> res = player.getInventory().main.stream().filter(itemStack ->
                itemStack.isOf(Items.TOTEM_OF_UNDYING)
        ).findFirst();
        return res.orElse(stack);
    }
}
