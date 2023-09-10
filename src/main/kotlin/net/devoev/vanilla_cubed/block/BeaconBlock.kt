package net.devoev.vanilla_cubed.block

import net.devoev.vanilla_cubed.block.entity.ModBeaconBlockEntity
import net.devoev.vanilla_cubed.mixin.BeaconBlockMixin
import net.minecraft.block.BeaconBlock
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityTicker
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.stat.Stats
import net.minecraft.util.ActionResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable

/**
 * Replaces the beacon block entity in [BeaconBlock.createBlockEntity] with the modded beacon block entity.
 * @see BeaconBlockMixin.createModBeaconBlockEntity
 */
fun createModBeaconBlockEntity(pos: BlockPos, state: BlockState, cir: CallbackInfoReturnable<BlockEntity>) {
    cir.returnValue = ModBeaconBlockEntity(pos, state)
}

/**
 * Replaces the ticker function in [BeaconBlock.getTicker] with the modded beacons ticker.
 * @see BeaconBlockMixin.modTicker
 */
fun <T : BlockEntity> modTicker(type: BlockEntityType<T>, cir: CallbackInfoReturnable<BlockEntityTicker<T>>) {
    cir.returnValue = ModBeaconBlockEntity.ticker(type)
}

/**
 * Replaces the beacon entity with the modded one in the [BeaconBlock.onUse] method.
 * @see BeaconBlockMixin.useModBeaconBlockEntity
 */
fun useModBeaconBlockEntity(world: World, pos: BlockPos, player: PlayerEntity, cir: CallbackInfoReturnable<ActionResult>) {
    if (world.isClient) {
        cir.returnValue = ActionResult.SUCCESS
        return
    }

    val blockEntity = world.getBlockEntity(pos)
    if (blockEntity is ModBeaconBlockEntity) {
        player.openHandledScreen(blockEntity)
        player.incrementStat(Stats.INTERACT_WITH_BEACON)
    }
    cir.returnValue = ActionResult.CONSUME
}

/**
 * Replaces the beacon entity with the modded one in the [BeaconBlock.onPlaced] method.
 * @see BeaconBlockMixin.placeModBeaconBlockEntity
 */
fun placeModBeaconBlockEntity(world: World, pos: BlockPos, stack: ItemStack, ci: CallbackInfo) {
    if (stack.hasCustomName()) {
        (world.getBlockEntity(pos) as? ModBeaconBlockEntity)?.let {
            it.customName = stack.name
            ci.cancel()
        }
    }
}