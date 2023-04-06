package net.devoev.vanilla_cubed.block.entity

import net.devoev.vanilla_cubed.screen.ModBeaconScreenHandler
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.entity.BeaconBlockEntity
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityTicker
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.screen.NamedScreenHandlerFactory
import net.minecraft.screen.ScreenHandler
import net.minecraft.text.Text
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class ModBeaconBlockEntity(pos: BlockPos, state: BlockState) : BeaconBlockEntity(pos, state), NamedScreenHandlerFactory {

    override fun getDisplayName(): Text = Text.literal("Modded Beacon")

    override fun createMenu(i: Int, playerInventory: PlayerInventory?, playerEntity: PlayerEntity?): ScreenHandler {
        if (playerInventory == null) error("playerInventory must not be null!")
        println("Created modded menu")
        return ModBeaconScreenHandler(i, playerInventory)
    }

    companion object {

        /**
         * Provides the [tick] function of a [ModBeaconBlockEntity].
         */
        fun <T : BlockEntity> ticker(type: BlockEntityType<T>): BlockEntityTicker<T>? {
            return if (type == BlockEntityType.BEACON)
                BlockEntityTicker { world, pos, state, blockEntity -> tick(world, pos, state, blockEntity as BeaconBlockEntity) }
            else null
        }

        /**
         * Ticks the [ModBeaconBlockEntity].
         */
        private fun tick(world: World, pos: BlockPos, state: BlockState, blockEntity: BeaconBlockEntity) {
            // BeaconBlockEntity.tick(world, pos, state, blockEntity)

            val base = baseBlocks(world, pos)
            val strength = base.filterKeys { it != Blocks.AIR }.values.sum()
            // println("Beacon operating at strength: $strength")
        }

        /**
         * Counts the amount of blocks that make up the beacon base.
         */
        private fun baseBlocks(world: World, pos: BlockPos): Map<Block, Int> {
            val res = mutableMapOf<Block, Int>()
            for (dy in 1..4) {
                for (dz in -dy..dy) {
                    for (dx in -dy..dy) {
                        val block = world.getBlockState(BlockPos(
                            pos.x + dx,
                            pos.y - dy,
                            pos.z + dz)
                        ).block
                        res[block] = res.getOrPut(block) { 0 } + 1
                    }
                }
            }
            return res
        }
    }
}