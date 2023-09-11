package net.devoev.vanilla_cubed.mixin;

import net.devoev.vanilla_cubed.item.modifier.TreasureEnchantingModifierKt;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

/**
 * A mixin for ancient gold armor and tools.
 */
@Mixin(ExperienceOrbEntity.class)
public class ExperienceOrbEntityMixin {


    /**
     * @see TreasureEnchantingModifierKt
     */
    @ModifyVariable(method = "repairPlayerGears", at = @At(value = "STORE"), ordinal = 1)
    private int repairPlayersGearFaster(int i, PlayerEntity player) {
        return TreasureEnchantingModifierKt.repairPlayersGearFaster(i, player);
    }
}
