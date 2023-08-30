package net.devoev.vanilla_cubed.mixin;

import net.devoev.vanilla_cubed.block.entity.beacon_upgrade.DisableFireDamageUpgrade;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FireBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FireBlock.class)
public class FireBlockMixin {

    /**
     * @see DisableFireDamageUpgrade
     */
    @Inject(method = "scheduledTick", at = @At("HEAD"), cancellable = true)
    private void disableFireTick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        DisableFireDamageUpgrade.INSTANCE.disableFireTick((Block) (Object) this, world, pos, ci);
    }
}
