package net.devoev.vanilla_cubed.mixin;

import net.devoev.vanilla_cubed.util.ItemKt;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

/**
 * A mixin for ancient gold armor and tools.
 */
@Mixin(ExperienceOrbEntity.class)
public class ExperienceOrbEntityMixin {

    /**
     * Changes the repair amount of mending gear to be higher, when the item is made of ancient gold.
     */
    @ModifyVariable(method = "repairPlayerGears", at = @At(value = "STORE"), ordinal = 1)
    private int repairFasterWithAncientGold(int i, PlayerEntity player) {
        ItemStack stack = EnchantmentHelper.chooseEquipmentWith(Enchantments.MENDING, player, ItemStack::isDamaged).getValue();
        return ItemKt.isAncientGold(stack.getItem()) ? 2*i : i;
    }
}
