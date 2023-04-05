package net.devoev.vanilla_cubed.mixin;

import net.devoev.vanilla_cubed.block.entity.ModBeaconBlockEntity;
import net.minecraft.block.BeaconBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BeaconBlock.class)
public class BeaconBlockMixin {

    /**
     * Replaces the beacon block entity with the modded beacon block entity.
     */
    @Inject(method = "createBlockEntity", at = @At("HEAD"), cancellable = true)
    private void useModdedBeaconEntity(BlockPos pos, BlockState state, CallbackInfoReturnable<BlockEntity> info) {
        info.setReturnValue(new ModBeaconBlockEntity(pos, state));
    }
}
