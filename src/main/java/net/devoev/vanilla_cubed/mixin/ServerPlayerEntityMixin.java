package net.devoev.vanilla_cubed.mixin;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin {

    /**
     * Copies the inventory of a player after death. Used for enderite soulbound ability.
     */
    @Inject(method = "copyFrom", at = @At(value = "HEAD"))
    private void keepItemsAfterDeath(ServerPlayerEntity oldPlayer, boolean alive, CallbackInfo info) {
        if (!oldPlayer.getWorld().getGameRules().getBoolean(GameRules.KEEP_INVENTORY))
            ((ServerPlayerEntity) (Object) this).getInventory().clone(oldPlayer.getInventory());
    }
}
