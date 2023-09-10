package net.devoev.vanilla_cubed.mixin;

import net.devoev.vanilla_cubed.block.BeaconBlockKt;
import net.minecraft.block.BeaconBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
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
     * @see BeaconBlockKt
     */
    @Inject(method = "createBlockEntity", at = @At("HEAD"), cancellable = true)
    private void createModBeaconBlockEntity(BlockPos pos, BlockState state, CallbackInfoReturnable<BlockEntity> info) {
        BeaconBlockKt.createModBeaconBlockEntity(pos, state, info);
    }

    /**
     * @see BeaconBlockKt
     */
    @Inject(method = "getTicker", at = @At("HEAD"), cancellable = true)
    private <T extends BlockEntity> void modTicker(World world, BlockState state, BlockEntityType<T> type, CallbackInfoReturnable<BlockEntityTicker<T>> cir) {
        BeaconBlockKt.modTicker(type, cir);
    }

    /**
     * @see BeaconBlockKt
     */
    @Inject(method = "onUse", at = @At("HEAD"), cancellable = true)
    private void useModBeaconBlockEntity(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, CallbackInfoReturnable<ActionResult> cir) {
        BeaconBlockKt.useModBeaconBlockEntity(world, pos, player, cir);
    }

    /**
     * @see BeaconBlockKt
     */
    @Inject(method = "onPlaced", at = @At("HEAD"), cancellable = true)
    public void placeModBeaconBlockEntity(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack, CallbackInfo ci) {
        BeaconBlockKt.placeModBeaconBlockEntity(world, pos, itemStack, ci);
    }
}
