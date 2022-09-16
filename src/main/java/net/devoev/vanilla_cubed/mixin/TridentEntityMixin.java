package net.devoev.vanilla_cubed.mixin;

import net.devoev.vanilla_cubed.enchantment.ForceEnchantment;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(TridentEntity.class)
public class TridentEntityMixin {

    @Shadow
    private ItemStack tridentStack;

    /**
     * Applies the force enchantment when a trident hits an entity.
     */
    @ModifyVariable(method = "onEntityHit", at = @At(value = "STORE"), ordinal = 0)
    private float applyForceEnchantment(float f) {
        return f + ForceEnchantment.INSTANCE.damage(tridentStack);
    }
}
