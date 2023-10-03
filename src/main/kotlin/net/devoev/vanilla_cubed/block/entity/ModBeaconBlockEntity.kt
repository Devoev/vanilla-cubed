package net.devoev.vanilla_cubed.block.entity

import net.devoev.vanilla_cubed.block.ModBlocks
import net.devoev.vanilla_cubed.block.entity.beacon.*
import net.devoev.vanilla_cubed.block.entity.beacon.upgrades.BeaconUpgrade
import net.devoev.vanilla_cubed.screen.*
import net.devoev.vanilla_cubed.util.math.boxOf
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
import net.minecraft.network.listener.ClientPlayPacketListener
import net.minecraft.network.packet.Packet
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
import kotlin.properties.Delegates


/**
 * Block entity for the modded beacon.
 *
 * @param pos Block position.
 * @param state Block state.
 *
 * @property activeUpgrades Activated beacon upgrades.
 * @property totalLevels Amount of placed iron, gold, emerald or diamond blocks.
 * @property propertyDelegate Delegate of properties to sync with the screen handler.
 * @property beamSegments Segments of the beacon beam.
 * @property range Range of the beacon effect.
 */
class ModBeaconBlockEntity(pos: BlockPos, state: BlockState) : BlockEntity(ModBlockEntityTypes.MOD_BEACON, pos, state), NamedScreenHandlerFactory {

    private var lock: ContainerLock = ContainerLock.EMPTY
    var customName: Text? = null

    /**
     * The 16 beacon upgrades stored with respect to canonical indexing. A `null` value is stored, if upgrade is not active.
     */
    private var upgrades: List<BeaconUpgrade?> by Delegates.observable(UPGRADES_EMPTY) { _, old, new ->
        check(new.size == 16) { "New upgrades list is too small!" }
        old.forEach { it?.deactivate(this) }
        new.forEach { it?.activate(this) }
    }

    private val activeUpgrades: List<BeaconUpgrade>
        get() = upgrades.filterNotNull()

    val totalLevels: IntArray = intArrayOf(0,0,0,0)

    /**
     * Value between 1 and 2, depending on the number of base II blocks in the beacons base.
     */
    private var extendRange: Double = 1.0

    private val remainingLevels: IntArray
        get() {
            var res = totalLevels.toList()
            for (upgrade in activeUpgrades) {
                val required = upgrade.requiredLevels
                res = List(res.size) { i -> res[i] - required[i] }
            }
            return res.toIntArray()
        }
    private val propertyDelegate = BeaconPropertyDelegate()

    private var _beamSegments: MutableList<ModBeamSegment> = mutableListOf()
    var beamSegments: MutableList<ModBeamSegment>
        get() = if (activeBase) _beamSegments else mutableListOf()
        private set(value) { _beamSegments = value }

    val range: Box?
        get() = world?.run {Box(pos)
            .expand(BeaconUpgradeTier.levelToTier(totalLevels.sum())*10.0*extendRange + 10)
            .stretch(0.0, height.toDouble(), 0.0)
        }

    /**
     * Whether at least one of the [totalLevels] is greater than 0, meaning the base is build properly.
     */
    private val activeBase: Boolean
        get() = totalLevels.any { it > 0 }

    /**
     * Whether the [beamSegments] ar not empty, meaning a beam is active.
     */
    private val activeBeam: Boolean
        get() = beamSegments.isNotEmpty()

    /**
     * Whether the [totalLevels] are high enough to activate the current [activeUpgrades].
     */
    private val activeLevels: BooleanArray
        get() = activeUpgrades.map { it.tier.checkLevel(totalLevels) }.toBooleanArray()

    private var minY: Int = 0

    override fun getDisplayName(): Text = customName ?: Text.translatable("container.beacon")

    override fun setWorld(world: World) {
        super.setWorld(world)
        minY = world.bottomY - 1
    }

    override fun markRemoved() {
        deactivate(world!!, pos)
        super.markRemoved()
    }

    override fun createMenu(i: Int, playerInventory: PlayerInventory?, playerEntity: PlayerEntity?): ScreenHandler? {
        requireNotNull(playerInventory) { "playerInventory must not be null!" }
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
        upgrades = nbt.getIntArray("upgrades").map { BeaconUpgrades.getOrNull(it) }.ifEmpty { UPGRADES_EMPTY }
        if (nbt.contains("CustomName", 8)) {
            customName = Text.Serializer.fromJson(nbt.getString("CustomName"))
        }
        lock = ContainerLock.fromNbt(nbt)
    }

    override fun writeNbt(nbt: NbtCompound) {
        super.writeNbt(nbt)
        nbt.putIntArray("upgrades", upgrades.map { it.idx })
        if (customName != null) {
            nbt.putString("CustomName", Text.Serializer.toJson(customName))
        }
        lock.writeNbt(nbt)
    }

    /**
     * Called when activating the beacon.
     */
    private fun activate(world: World, pos: BlockPos) {
        BeaconBlockEntity.playSound(world, pos, SoundEvents.BLOCK_BEACON_ACTIVATE)
        activeUpgrades.forEach { it.activate(this) }
        val players = world.getNonSpectatingEntities(
            ServerPlayerEntity::class.java,
            boxOf(pos.x, pos.y, pos.z, pos.x, (pos.y - 4), pos.z).expand(10.0, 5.0, 10.0)
        )
        for (player in players) {
            Criteria.CONSTRUCT_BEACON.trigger(player, BeaconUpgradeTier.levelToTier(totalLevels.sum()))
        }
    }

    /**
     * Called when deactivating the beacon.
     */
    private fun deactivate(world: World, pos: BlockPos) {
        BeaconBlockEntity.playSound(world, pos, SoundEvents.BLOCK_BEACON_DEACTIVATE)
        activeUpgrades.forEach { it.deactivate(this) }
    }

    /**
     * Ticks the [ModBeaconBlockEntity].
     */
    private fun tick(world: World, pos: BlockPos, state: BlockState) {
        // TODO: Possibly send levels value by networking to screen, in order to prevent flicker

        val activeBaseOld = activeBase
        val activeBeamOld = activeBeam
        val activeLevelsOld = activeLevels.copyOf()

        tickLevels(world, pos)
        if (activeBase) {
            tickBeam(world, pos)
            if (world.time % 80L == 0L && activeBeam) {
                tickUpgrades(world, pos, state)
                playSound(world, pos, SoundEvents.BLOCK_BEACON_AMBIENT)
            }
        }
        tickActivation(world, pos, activeBaseOld, activeBeamOld, activeLevelsOld)
    }

    /**
     * Ticks the [totalLevels] property by updating its values.
     */
    private fun tickLevels(world: World, pos: BlockPos) {

        /**
         * Checks whether the levels contain any other block than valid base blocks.
         */
        fun Map<Block, Int>.invalid(): Boolean {
            return this.any { it.key !in BASE_BLOCKS_I && it.key !in BASE_BLOCKS_II }
        }

        // Clear old levels
        totalLevels[0] = 0
        totalLevels[1] = 0
        totalLevels[2] = 0
        totalLevels[3] = 0

        // Counters for level I and II
        var levelI = 0
        var levelII = 0

        // Set new levels
        val base = baseBlocks(world, pos)
        for (baseLevel in base) {
            if (baseLevel.invalid()) break

            val baseI = baseLevel.filterKeys { it in BASE_BLOCKS_I }
            val baseII = baseLevel.filterKeys { it in BASE_BLOCKS_II }
            levelI += baseI.values.sum()
            levelII += baseII.values.sum()

            for ((block, idx) in BASE_BLOCKS) {
                totalLevels[idx] += baseLevel[block] ?: 0
            }
        }

        extendRange = (levelI + levelII*2).toDouble() / (levelI + levelII)
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
     * Ticks the beacon beam updating beam segments [beamSegments].
     */
    private fun tickBeam(world: World, pos: BlockPos) {
        var blockPos: BlockPos
        if (minY < pos.y) {
            blockPos = pos
            beamSegments.clear()
            minY = pos.y - 1
        } else {
            blockPos = BlockPos(pos.x, minY + 1, pos.z)
        }

        var beamSegment: ModBeamSegment? = beamSegments.lastOrNull()
        val maxY: Int = world.getTopY(Heightmap.Type.WORLD_SURFACE, pos.x, pos.z)

        while (blockPos.y <= maxY) {
            val blockState = world.getBlockState(blockPos)
            val block = blockState.block

            if (block is Stainable) {
                val color = block.color.colorComponents
                if (beamSegments.size <= 1) {
                    beamSegment = ModBeamSegment(color)
                    beamSegments += beamSegment
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
                        beamSegments += beamSegment
                    }
                }
            } else {
                if (beamSegment == null || blockState.getOpacity(world, blockPos) >= 15 && !blockState.isOf(Blocks.BEDROCK)) {
                    beamSegments.clear()
                    minY = maxY
                    break
                }

                beamSegment.increaseHeight()
            }

            blockPos = blockPos.up()
            minY++
        }
    }

    /**
     * Ticks the [activeUpgrades] by invoking it and plays the [SoundEvents.BLOCK_BEACON_AMBIENT] sound.
     */
    private fun tickUpgrades(world: World, pos: BlockPos, state: BlockState) {
        if (world.isClient || activeUpgrades.isEmpty()) return

        activeUpgrades.forEachIndexed { i, upgrade ->
            if (activeLevels[i]) upgrade(world, pos, state, this)
        }

    }

    /**
     * Ticks the activation and deactivation of the beacon, by comparing the
     * beacons properties with the provided old values.
     * @param activeBaseOld The old property value.
     * @param activeBeamOld The old property value.
     * @param activeLevelsOld The old property value.
     */
    private fun tickActivation(world: World, pos: BlockPos, activeBaseOld: Boolean, activeBeamOld: Boolean, activeLevelsOld: BooleanArray) {
        minY = world.bottomY - 1
        if (world.isClient) return

        activeUpgrades.forEachIndexed { i, upgrade ->
            if (!activeBeamOld && activeBeam || !activeLevelsOld[i] && activeLevels[i]) {
                upgrade.activate(this)
            } else if (activeBeamOld && !activeBeam || activeLevelsOld[i] && !activeLevels[i]) {
                upgrade.deactivate(this)
            }
        }

        if (!activeBaseOld && activeBase) {
            activate(world, pos)
        } else if (activeBaseOld && !activeBase) {
            deactivate(world, pos)
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

    companion object {

        /**
         * Map of level I block-idx pairs, that are valid for the beacon base.
         */
        val BASE_BLOCKS_I = mapOf(
            Blocks.IRON_BLOCK to 0,
            Blocks.GOLD_BLOCK to 1,
            Blocks.EMERALD_BLOCK to 2,
            Blocks.DIAMOND_BLOCK to 3,
        )

        /**
         * Map of level II block-idx pairs, that are valid for the beacon base.
         */
        val BASE_BLOCKS_II = mapOf(
            ModBlocks.ENDERITE_BLOCK to 0,
            ModBlocks.ANCIENT_GOLD_BLOCK to 1,
            Blocks.NETHERITE_BLOCK to 1,
            ModBlocks.AMETHYST_CRYSTAL_BLOCK to 3,
            ModBlocks.CHARGED_AMETHYST_CRYSTAL_BLOCK to 3,
        )

        /**
         * Map of all block-idx pairs, that are valid for the beacon base.
         */
        val BASE_BLOCKS = BASE_BLOCKS_I + BASE_BLOCKS_II

        /**
         * Provides the [tick] function of a [ModBeaconBlockEntity].
         */
        fun <T : BlockEntity> ticker(type: BlockEntityType<T>): BlockEntityTicker<T>? {
            return if (type == ModBlockEntityTypes.MOD_BEACON)
                BlockEntityTicker { world, pos, state, blockEntity -> (blockEntity as ModBeaconBlockEntity).tick(world, pos, state) }
            else null
        }
    }

    /**
     * The property delegate of a [ModBeaconBlockEntity].
     * The stored properties are:
     * @property totalLevels [Total levels][ModBeaconBlockEntity.totalLevels] at indices [0-3][TOTAL_LEVELS_RANGE].
     * @property totalLevels [Remaining levels][ModBeaconBlockEntity.remainingLevels] at indices [4-7][REMAINING_LEVELS_RANGE].
     * @property upgrades [Beacons upgrades][ModBeaconBlockEntity.upgrades] at indices [8-23][UPGRADE_RANGE].
     */
    inner class BeaconPropertyDelegate : PropertyDelegate {

        init {
            // Set initial values
            this.totalLevels = this@ModBeaconBlockEntity.totalLevels
            this.remainingLevels = this@ModBeaconBlockEntity.remainingLevels
            this.upgrades = this@ModBeaconBlockEntity.upgrades
        }

        override fun get(i: Int): Int {
            return when(i) {
                in TOTAL_LEVELS_RANGE -> this@ModBeaconBlockEntity.totalLevels[i]
                in REMAINING_LEVELS_RANGE -> this@ModBeaconBlockEntity.remainingLevels[i - REMAINING_LEVELS_RANGE.first]
                in UPGRADE_RANGE -> this@ModBeaconBlockEntity.upgrades.getOrNull(i - UPGRADE_RANGE.first).idx
                else -> error("Index $i out of bounds")
            }
        }

        override fun set(i: Int, value: Int) {
            when(i) {
                in TOTAL_LEVELS_RANGE -> { this@ModBeaconBlockEntity.totalLevels[i] = value }
                in REMAINING_LEVELS_RANGE -> { this@ModBeaconBlockEntity.remainingLevels[i - REMAINING_LEVELS_RANGE.first] = value }
                in UPGRADE_RANGE -> {
                    if (activeBeam && i == UPGRADE_RANGE.first) playSound(world, pos, SoundEvents.BLOCK_BEACON_POWER_SELECT)
                    val list = this@ModBeaconBlockEntity.upgrades.toMutableList()
                    list[i - UPGRADE_RANGE.first] = BeaconUpgrades.getOrNull(value)
                    this@ModBeaconBlockEntity.upgrades = list
                }
                else -> error("Index $i out of bounds")
            }
        }

        override fun size(): Int = UPGRADE_RANGE.last + 1
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