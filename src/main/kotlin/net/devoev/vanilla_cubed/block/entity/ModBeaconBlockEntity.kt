package net.devoev.vanilla_cubed.block.entity

import net.minecraft.block.BlockState
import net.minecraft.block.entity.BeaconBlockEntity
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityTicker
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.screen.NamedScreenHandlerFactory
import net.minecraft.text.Text
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class ModBeaconBlockEntity(pos: BlockPos, state: BlockState) : BeaconBlockEntity(pos, state), NamedScreenHandlerFactory {

    override fun getDisplayName(): Text = Text.literal("Modded Beacon")

    companion object {

        /**
         * Ticks the [ModBeaconBlockEntity].
         */
        private fun tick(world: World, pos: BlockPos, state: BlockState, blockEntity: BeaconBlockEntity) {
            println("Lol!")
            BeaconBlockEntity.tick(world, pos, state, blockEntity)
        }

        /**
         * Provides the [tick] function of a [ModBeaconBlockEntity].
         */
        fun <T : BlockEntity> ticker(type: BlockEntityType<T>): BlockEntityTicker<T>? {
            return if (type == BlockEntityType.BEACON)
                BlockEntityTicker { world, pos, state, blockEntity -> tick(world, pos, state, blockEntity as BeaconBlockEntity) }
            else null
        }
    }
}