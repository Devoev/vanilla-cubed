package net.devoev.vanilla_cubed.block.entity

import net.devoev.vanilla_cubed.block.entity.behavior.BeaconUpgrade
import net.devoev.vanilla_cubed.block.entity.behavior.StatusEffectUpgrade
import net.devoev.vanilla_cubed.screen.ModBeaconScreenHandler
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.entity.*
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.ContainerLock
import net.minecraft.nbt.NbtCompound
import net.minecraft.network.Packet
import net.minecraft.network.listener.ClientPlayPacketListener
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket
import net.minecraft.screen.NamedScreenHandlerFactory
import net.minecraft.screen.PropertyDelegate
import net.minecraft.screen.ScreenHandler
import net.minecraft.screen.ScreenHandlerContext
import net.minecraft.sound.SoundEvents
import net.minecraft.text.Text
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

/**
 * Block entity for the modded beacon.
 * @param pos Block position.
 * @param state Block state.
 *
 * @property behavior Modified beacon tick behavior.
 * @property propertyDelegate Delegate of properties to update beacon behavior.
 */
class ModBeaconBlockEntity(pos: BlockPos, state: BlockState) : BlockEntity(ModBlockEntityTypes.MOD_BEACON, pos, state), NamedScreenHandlerFactory {

    private var lock: ContainerLock = ContainerLock.EMPTY

    var customName: Text? = null

    private var behavior: BeaconUpgrade = BeaconUpgrade.EMPTY

    // TODO: Currently this delegate represents a boolean, meaning it activates or deactivates the speed modifier.
    //  Change this with an encoding: int -> behavior/ button
    private val propertyDelegate = object : PropertyDelegate {
        override fun get(index: Int): Int {
            return index
        }

        override fun set(index: Int, value: Int) {
            println("Setting delegate at index $index and value $value")
            behavior = if (value == 1) {
                // TODO: play beacon sound
                StatusEffectUpgrade(StatusEffects.SPEED)
            } else {
                BeaconUpgrade.EMPTY
            }
        }

        override fun size(): Int = 2 // TODO: Update size appropriately
    }


    override fun getDisplayName(): Text = customName ?: Text.translatable("container.beacon")

    override fun createMenu(i: Int, playerInventory: PlayerInventory?, playerEntity: PlayerEntity?): ScreenHandler? {
        if (playerInventory == null)
            error("playerInventory must not be null!")
        return if (LockableContainerBlockEntity.checkUnlocked(playerEntity, lock, displayName))
            ModBeaconScreenHandler(i, playerInventory, propertyDelegate, ScreenHandlerContext.create(world, pos))
        else null;
    }

    override fun markRemoved() {
        BeaconBlockEntity.playSound(world, pos, SoundEvents.BLOCK_BEACON_DEACTIVATE)
        super.markRemoved()
    }

    override fun toUpdatePacket(): Packet<ClientPlayPacketListener>? {
        return BlockEntityUpdateS2CPacket.create(this)
    }

    override fun toInitialChunkDataNbt(): NbtCompound {
        return createNbt()
    }

    override fun readNbt(nbt: NbtCompound) {
        super.readNbt(nbt)
        if (nbt.contains("CustomName", 8)) {
            customName = Text.Serializer.fromJson(nbt.getString("CustomName"))
        }
        lock = ContainerLock.fromNbt(nbt)
    }

    override fun writeNbt(nbt: NbtCompound) {
        super.writeNbt(nbt)
        if (customName != null) {
            nbt.putString("CustomName", Text.Serializer.toJson(customName))
        }
        lock.writeNbt(nbt)
    }



    companion object {

        /**
         * Provides the [tick] function of a [ModBeaconBlockEntity].
         */
        fun <T : BlockEntity> ticker(type: BlockEntityType<T>): BlockEntityTicker<T>? {
            return if (type == ModBlockEntityTypes.MOD_BEACON)
                BlockEntityTicker { world, pos, state, blockEntity -> tick(world, pos, state, blockEntity as ModBeaconBlockEntity) }
            else null
        }

        /**
         * Ticks the [ModBeaconBlockEntity].
         */
        private fun tick(world: World, pos: BlockPos, state: BlockState, blockEntity: ModBeaconBlockEntity) {
            // TODO: Apply behaviors specific to the beacon upgrade
            blockEntity.behavior(world, pos, state, blockEntity)

//            val base = baseBlocks(world, pos)
//            val strength = base.filterKeys { it != Blocks.AIR }.values.sum()
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