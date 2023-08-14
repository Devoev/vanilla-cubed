package net.devoev.vanilla_cubed.mixin;

import net.devoev.vanilla_cubed.block.entity.beacon_upgrade.DisableEnvironmentalDamageUpgrade;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.LavaFluid;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LavaFluid.class)
public class LavaFluidMixin {

    @Inject(method = "onRandomTick", at = @At("HEAD"), cancellable=true)
    private void disableLavaFireTick(World world, BlockPos pos, FluidState state, Random random, CallbackInfo ci) {
        DisableEnvironmentalDamageUpgrade.INSTANCE.injectLavaFluid(pos, ci);
    }
}
