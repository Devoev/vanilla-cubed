package net.devoev.vanilla_cubed.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LightningEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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

        if ((Object) this instanceof ItemEntity) {
            ItemEntity item = (ItemEntity) (Object) this;
            System.out.println(item.getStack());
            item.setStack(new ItemStack(Items.NETHERITE_INGOT));
        }
    }
}
