package net.devoev.vanilla_cubed.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LightningEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
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

        if ((Object) this instanceof ItemEntity item) {
            Item crystal = Registry.ITEM.get(new Identifier("vanilla_cubed", "amethyst_crystal"));
            Item charged_crystal = Registry.ITEM.get(new Identifier("vanilla_cubed", "amethyst_crystal_charged"));
            if (!item.getStack().getItem().equals(crystal)) return;

            item.setStack(new ItemStack(charged_crystal, item.getStack().getCount()));
            item.setInvulnerable(true);
            item.setInvisible(false);
            item.setOnFire(false);
        }
    }
}
