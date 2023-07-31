package net.devoev.vanilla_cubed.block.entity

import net.devoev.vanilla_cubed.block.entity.beacon_upgrade.BeaconUpgrade
import net.devoev.vanilla_cubed.block.entity.beacon_upgrade.BeaconUpgrades
import net.devoev.vanilla_cubed.screen.ModBeaconScreenHandler
import net.devoev.vanilla_cubed.screen.levels
import net.devoev.vanilla_cubed.screen.upgrade
import net.minecraft.advancement.criterion.Criteria
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.Stainable
import net.minecraft.block.entity.*
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
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.text.Text
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Box
import net.minecraft.world.Heightmap
import net.minecraft.world.World


/**
 * Block entity for the modded beacon.
 * @param pos Block position.
 * @param state Block state.
 *
 * @property beamSegments Segments of the beacon beam.
 * @property upgrade Activated beacon upgrade.
 * @property levels Amount of placed iron, gold, emerald or diamond blocks.
 * @property propertyDelegate Delegate of properties to sync with the screen handler.
 */
class ModBeaconBlockEntity(pos: BlockPos, state: BlockState) : BlockEntity(ModBlockEntityTypes.MOD_BEACON, pos, state), NamedScreenHandlerFactory {

    private var lock: ContainerLock = ContainerLock.EMPTY
    var customName: Text? = null

    private val _beamSegments: MutableList<ModBeamSegment> = mutableListOf()
    var beamSegments: List<ModBeamSegment> = listOf()
        private set
    private var minY: Int = 0

    private var upgrade: BeaconUpgrade? = null
    private val levels: IntArray = intArrayOf(0,0,0,0)
    private val propertyDelegate = BeaconPropertyDelegate()

    override fun getDisplayName(): Text = customName ?: Text.translatable("container.beacon")

    override fun setWorld(world: World) {
        super.setWorld(world)
        minY = world.bottomY - 1
    }

    override fun markRemoved() {
        BeaconBlockEntity.playSound(world, pos, SoundEvents.BLOCK_BEACON_DEACTIVATE)
        super.markRemoved()
    }

    override fun createMenu(i: Int, playerInventory: PlayerInventory?, playerEntity: PlayerEntity?): ScreenHandler? {
        if (playerInventory == null)
            error("playerInventory must not be null!")
        return if (LockableContainerBlockEntity.checkUnlocked(playerEntity, lock, displayName))
            ModBeaconScreenHandler(i, playerInventory, propertyDelegate, ScreenHandlerContext.create(world, pos))
        else null;
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
            // TODO: Possibly send levels value by networking to screen, in order to prevent flicker

            tickBeam(world, pos, state, blockEntity)
            val oldLevels = blockEntity.levels.clone()
            if (world.time % 80L == 0L && blockEntity.beamSegments.isNotEmpty()) {
                tickLevels(world, pos, blockEntity)
                tickUpgrade(world, pos, state, blockEntity)
            }

            tickActivation(world, pos, state, blockEntity, oldLevels)
        }

        /**
         * Ticks the [levels] property by updating its values.
         */
        private fun tickLevels(world: World, pos: BlockPos, blockEntity: ModBeaconBlockEntity) {

            /**
             * Checks whether the levels contain any other block than valid base blocks.
             */
            fun Map<Block, Int>.invalid(): Boolean {
                return this.any {
                    it.key != Blocks.IRON_BLOCK
                            && it.key != Blocks.GOLD_BLOCK
                            && it.key != Blocks.EMERALD_BLOCK
                            && it.key != Blocks.DIAMOND_BLOCK
                }
            }

            // Clear old levels
            blockEntity.levels[0] = 0
            blockEntity.levels[1] = 0
            blockEntity.levels[2] = 0
            blockEntity.levels[3] = 0

            // Set new levels
            val base = baseBlocks(world, pos)
            for (baseLevel in base) {
                if (baseLevel.invalid()) break
                blockEntity.levels[0] += baseLevel[Blocks.IRON_BLOCK] ?: 0
                blockEntity.levels[1] += baseLevel[Blocks.GOLD_BLOCK] ?: 0
                blockEntity.levels[2] += baseLevel[Blocks.EMERALD_BLOCK] ?: 0
                blockEntity.levels[3] += baseLevel[Blocks.DIAMOND_BLOCK] ?: 0
            }
        }

        /**
         * Counts the amount of blocks that make up the beacon base.
         * @return A list of all blocks per y-level. Sorted from highest to lowest y value.
         */
        private fun baseBlocks(world: World, pos: BlockPos): List<Map<Block, Int>> {
            val res: List<MutableMap<Block, Int>> = listOf(
                mutableMapOf(), mutableMapOf(), mutableMapOf(), mutableMapOf()
            )
            for (dy in 1..4) {
                val level = res[dy-1]
                for (dz in -dy..dy) {
                    for (dx in -dy..dy) {
                        val block = world.getBlockState(BlockPos(
                            pos.x + dx,
                            pos.y - dy,
                            pos.z + dz)
                        ).block
                        level[block] = level.getOrPut(block) { 0 } + 1
                    }
                }
            }
            return res
        }

        /**
         * Ticks the beacon beam updating the temporary beam segments [_beamSegments].
         */
        private fun tickBeam(world: World, pos: BlockPos, state: BlockState, blockEntity: ModBeaconBlockEntity) {
            var blockPos: BlockPos
            if (blockEntity.minY < pos.y) {
                blockPos = pos
                blockEntity._beamSegments.clear()
                blockEntity.minY = pos.y - 1
            } else {
                blockPos = BlockPos(pos.x, blockEntity.minY + 1, pos.z)
            }

            var beamSegment: ModBeamSegment? = blockEntity._beamSegments.lastOrNull()
            val maxY: Int = world.getTopY(Heightmap.Type.WORLD_SURFACE, pos.x, pos.z)

            while (blockPos.y <= maxY) {
                val blockState = world.getBlockState(blockPos)
                val block = blockState.block

                if (block is Stainable) {
                    val color = block.color.colorComponents
                    if (blockEntity._beamSegments.size <= 1) {
                        beamSegment = ModBeamSegment(color)
                        blockEntity._beamSegments += beamSegment
                    } else if (beamSegment != null) {
                        if (color contentEquals beamSegment.color) {
                            beamSegment.increaseHeight()
                        } else {
                            beamSegment = ModBeamSegment(
                                floatArrayOf(
                                    (beamSegment.color[0] + color[0]) / 2,
                                    (beamSegment.color[1] + color[1]) / 2,
                                    (beamSegment.color[2] + color[2]) / 2
                                )
                            )
                            blockEntity._beamSegments += beamSegment
                        }
                    }
                } else {
                    if (beamSegment == null || blockState.getOpacity(world, blockPos) >= 15 && !blockState.isOf(Blocks.BEDROCK)) {
                        blockEntity._beamSegments.clear()
                        blockEntity.minY = maxY
                        break
                    }

                    beamSegment.increaseHeight()
                }

                blockPos = blockPos.up()
                blockEntity.minY++
            }
        }

        /**
         * Ticks the active [upgrade] by invoking it and plays the [SoundEvents.BLOCK_BEACON_AMBIENT] sound.
         */
        private fun tickUpgrade(world: World, pos: BlockPos, state: BlockState, blockEntity: ModBeaconBlockEntity) {
            // TODO: Check level requirement
            if (!world.isClient) {
                blockEntity.upgrade?.invoke(world, pos, state, blockEntity)
            }
            playSound(world, pos, SoundEvents.BLOCK_BEACON_AMBIENT)
        }

        /**
         * Ticks the activation and deactivation of the beacon, by comparing the [levels] to the given [oldLevels],
         * before updating them.
         */
        private fun tickActivation(world: World, pos: BlockPos, state: BlockState, blockEntity: ModBeaconBlockEntity, oldLevels: IntArray) {
            val maxY: Int = world.getTopY(Heightmap.Type.WORLD_SURFACE, pos.x, pos.z)
            // TODO: DONT use just the max levels, but each the required one.
            val maxOldLevel = oldLevels.max()
            val maxLevel = blockEntity.levels.max()

            if (blockEntity.minY >= maxY) {
                blockEntity.minY = world.bottomY - 1
                blockEntity.beamSegments = blockEntity._beamSegments
                if (!world.isClient) {
                    if (maxOldLevel <= 0 && maxLevel > 0) {
                        BeaconBlockEntity.playSound(world, pos, SoundEvents.BLOCK_BEACON_ACTIVATE)
                        for (serverPlayerEntity in world.getNonSpectatingEntities(
                            ServerPlayerEntity::class.java,
                            Box(
                                pos.x.toDouble(),
                                pos.y.toDouble(),
                                pos.z.toDouble(),
                                pos.x.toDouble(),
                                (pos.y - 4).toDouble(),
                                pos.z.toDouble()
                            ).expand(10.0, 5.0, 10.0)
                        )) {
                            Criteria.CONSTRUCT_BEACON.trigger(serverPlayerEntity, maxLevel)
                        }
                    } else if (maxOldLevel > 0 && maxLevel <= 0) {
                        BeaconBlockEntity.playSound(world, pos, SoundEvents.BLOCK_BEACON_DEACTIVATE)
                    }
                }
            }
        }

        /**
         * Plays the given [sound] at the [pos] if the current [world] is server side.
         */
        private fun playSound(world: World?, pos: BlockPos, sound: SoundEvent) {
            if (world?.isClient == false) {
                world.playSound(null, pos, sound, SoundCategory.BLOCKS, 1.0f, 1.0f)
            }
        }
    }

    /**
     * The property delegate of a [ModBeaconBlockEntity].
     * The stored properties are:
     * @property levels [ModBeaconBlockEntity.levels] at indices 0-3.
     * @property upgrade [ModBeaconBlockEntity.upgrade] at index 4.
     */
    inner class BeaconPropertyDelegate : PropertyDelegate {

        init {
            // Set initial values
            this.levels = this@ModBeaconBlockEntity.levels
            this.upgrade = this@ModBeaconBlockEntity.upgrade
        }

        override fun get(i: Int): Int {
            return when(i) {
                in 0..3 -> this@ModBeaconBlockEntity.levels[i]
                4 -> BeaconUpgrades.indexOf(this@ModBeaconBlockEntity.upgrade)
                else -> error("Index $i out of bounds.")
            }
        }

        override fun set(i: Int, value: Int) {
            // TODO: play beacon sound
            when(i) {
                in 0..3 -> { this@ModBeaconBlockEntity.levels[i] = value }
                4 -> {
                    playSound(world, pos, SoundEvents.BLOCK_BEACON_POWER_SELECT)
                    this@ModBeaconBlockEntity.upgrade = BeaconUpgrades[value]
                }
            }
        }

        override fun size(): Int = 5
    }

    /**
     * Segment of the beacons beam with the given [color] and [height].
     */
    data class ModBeamSegment(val color: FloatArray, internal var height: Int = 1) {

        internal fun increaseHeight() { ++height }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as ModBeamSegment

            if (!color.contentEquals(other.color)) return false
            return height == other.height
        }

        override fun hashCode(): Int {
            var result = color.contentHashCode()
            result = 31 * result + height
            return result
        }
    }
}