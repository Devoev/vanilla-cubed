package net.devoev.vanilla_cubed.mixin;

import net.devoev.vanilla_cubed.block.entity.ModBeaconBlockEntity;
import net.minecraft.block.BeaconBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
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

    /**
     * Replaces the ticker function with the modded beacons ticker.
     */
    @Inject(method = "getTicker", at = @At("HEAD"), cancellable = true)
    private <T extends BlockEntity> void useModdedBeaconTicker(World world, BlockState state, BlockEntityType<T> type, CallbackInfoReturnable<BlockEntityTicker<T>> info) {
        info.setReturnValue(ModBeaconBlockEntity.Companion.ticker(type));
    }

    /**
     * Replaces the beacon entity with the modded one.
     */
    @Inject(method = "onUse", at = @At("HEAD"), cancellable = true)
    private void onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, CallbackInfoReturnable<ActionResult> info) {
        if (world.isClient) {
            info.setReturnValue(ActionResult.SUCCESS);
        } else {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof ModBeaconBlockEntity) {
                player.openHandledScreen((ModBeaconBlockEntity)blockEntity);
                player.incrementStat(Stats.INTERACT_WITH_BEACON);
            }

            info.setReturnValue(ActionResult.CONSUME);
        }
    }

    /**
     * Replaces the beacon entity with the modded one.
     */
    @Inject(method = "onPlaced", at = @At("HEAD"), cancellable = true)
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack, CallbackInfo ci) {
        if (itemStack.hasCustomName()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof ModBeaconBlockEntity) {
                ((ModBeaconBlockEntity) blockEntity).setCustomName(itemStack.getName());
                ci.cancel();
            }
        }
    }
}
