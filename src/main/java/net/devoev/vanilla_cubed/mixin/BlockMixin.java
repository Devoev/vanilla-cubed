package net.devoev.vanilla_cubed.mixin;

import net.devoev.vanilla_cubed.item.ItemKt;
import net.devoev.vanilla_cubed.item.ItemStackKt;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(Block.class)
public class BlockMixin {

    /**
     * Sets the setMinedByEnderite NBT value of block drops to true, if mined with enderite tools.
     */
    @Inject(method = "getDroppedStacks(Lnet/minecraft/block/BlockState;Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/entity/BlockEntity;Lnet/minecraft/entity/Entity;Lnet/minecraft/item/ItemStack;)Ljava/util/List;", at = @At("RETURN"))
    private static void setMinedByEnderiteNBT(BlockState state, ServerWorld world, BlockPos pos, @Nullable BlockEntity blockEntity, @Nullable Entity entity, ItemStack stack, CallbackInfoReturnable<List<ItemStack>> info) {
        if (ItemKt.isEnderite(stack.getItem()))
            info.getReturnValue().forEach(itemStack -> ItemStackKt.setMinedByEnderite(itemStack, true));
    }
}
