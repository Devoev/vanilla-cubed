package net.devoev.vanilla_cubed.item

import net.devoev.vanilla_cubed.util.math.toVec3d
import net.fabricmc.fabric.api.dimension.v1.FabricDimensions
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityStatuses
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.server.world.ServerWorld
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.util.math.Vec3d
import net.minecraft.world.TeleportTarget
import net.minecraft.world.World
import kotlin.jvm.optionals.getOrNull

object EnderitePowder : Item(ModItemGroup.TOOLS.toSettings()) {

    /**
     * The key for the NBT data, that indicates how many ticks have passed since the powder has been used.
     */
    private const val ENDERITE_POWDER_TICK_KEY = "enderite_powder_ticks"

    /**
     * The time in ticks the teleportation needs.
     */
    private const val INITIAL_TICKS = 60

    override fun use(world: World?, user: PlayerEntity?, hand: Hand?): TypedActionResult<ItemStack> {
        val stack = user?.getStackInHand(hand)

        if (stack == null || world == null || world.isClient)
            return TypedActionResult.pass(stack)

        world.sendEntityStatus(user, EntityStatuses.ADD_PORTAL_PARTICLES)
        user.itemCooldownManager[this] = INITIAL_TICKS
        setTeleportingTicks(stack, INITIAL_TICKS)
        return TypedActionResult.success(stack, world.isClient)
    }

    override fun inventoryTick(stack: ItemStack?, world: World?, entity: Entity?, slot: Int, selected: Boolean) {
        if (stack == null || entity !is ServerPlayerEntity || world !is ServerWorld) return

        if (teleportingTicks(stack) == 1)
            doTeleport(stack, entity, world)
        if (!stack.isEmpty)
            tickTeleport(stack)
    }

    /**
     * Returns the remaining ticks until the teleportation using [stack] can be performed.
     * Value stored in the NBT value of the key [ENDERITE_POWDER_TICK_KEY].
     */
    private fun teleportingTicks(stack: ItemStack): Int {
        if (stack.item !is EnderitePowder) error("${stack.item} must be of type $EnderitePowder")
        return stack.nbt?.getInt(ENDERITE_POWDER_TICK_KEY) ?: 0
    }

    private fun setTeleportingTicks(stack: ItemStack, value: Int) {
        if (stack.item !is EnderitePowder) error("${stack.item} must be of type $EnderitePowder")
        stack.orCreateNbt.putInt(ENDERITE_POWDER_TICK_KEY, value)
    }

    /**
     * Reduces the teleporting ticks by 1, if the value is greater than 0.
     */
    private fun tickTeleport(stack: ItemStack) {
        if (teleportingTicks(stack) > 0)
        setTeleportingTicks(stack, teleportingTicks(stack)-1)
    }

    /**
     * Returns the teleport target for the given [user].
     */
    @OptIn(ExperimentalStdlibApi::class)
    private fun teleportTarget(user: ServerPlayerEntity, world: ServerWorld): TeleportTarget {
        val pos = user.spawnPointPosition ?: world.spawnPos
        var vec = PlayerEntity.findRespawnPosition(world, pos, user.limbAngle, false, user.isAlive).getOrNull()

        if (vec == null) vec = world.spawnPos.toVec3d()
        return TeleportTarget(vec, Vec3d.ZERO, user.bodyYaw, user.pitch)
    }

    private fun doTeleport(stack: ItemStack, user: ServerPlayerEntity, world: ServerWorld) {
        stack.decrement(1)
        FabricDimensions.teleport(user, world.server.overworld, teleportTarget(user, world))
        world.playSound(null,
            user.blockPos,
            SoundEvents.ENTITY_ENDERMAN_TELEPORT,
            SoundCategory.PLAYERS,
            3f,
            -0.5f
        )
    }
}