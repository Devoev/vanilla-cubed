package net.devoev.vanilla_cubed.mixin;

import net.devoev.vanilla_cubed.item.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LightningEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * A mixin for the amethyst crystal.
 */
@Mixin(Entity.class)
public class AmethystCrystalMixin {

    /**
     * Charges the amethyst crystal when struck by lightning.
     */
    @Inject(method = "onStruckByLightning", at = @At("HEAD"))
    private void onStruckByLightning(ServerWorld world, LightningEntity lightning, CallbackInfo info) {
        if (!( (Object) this instanceof ItemEntity item && item.getStack().getItem().equals(ModItems.INSTANCE.getAMETHYST_CRYSTAL()))) return;

        item.setStack(new ItemStack(ModItems.INSTANCE.getCHARGED_AMETHYST_CRYSTAL(), item.getStack().getCount()));
        item.setInvulnerable(true);
        item.setInvisible(false);
        item.setOnFire(false);
    }
}
