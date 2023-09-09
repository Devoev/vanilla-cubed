package net.devoev.vanilla_cubed.mixin;

import net.devoev.vanilla_cubed.block.entity.beacon.upgrades.DisableMonsterSpawningUpgrade;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerWorld.class)
public class ServerWorldMixin {

    @Inject(method = "spawnEntity", at = @At("HEAD"))
    private void disableMonsterSpawn(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        // TODO: Overwrite ServerWorld.spawnEntity fun or ServerChunkManager.tickChunks -> SpawnHelper.spawn fun
        //  Disable ALL hostile mob spawning or just natural ones?
        DisableMonsterSpawningUpgrade.INSTANCE.disableMonsterSpawn(entity);
    }
}
