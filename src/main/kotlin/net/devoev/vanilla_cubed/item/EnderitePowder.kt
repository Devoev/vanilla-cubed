package net.devoev.vanilla_cubed.item

import net.devoev.vanilla_cubed.entity.falling
import net.devoev.vanilla_cubed.util.math.toVec3d
import net.fabricmc.fabric.api.dimension.v1.FabricDimensions
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityStatuses
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
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

class EnderitePowder : Item(ModItemGroup.VANILLA_CUBED.toSettings()) {

    override fun use(world: World?, user: PlayerEntity?, hand: Hand?): TypedActionResult<ItemStack> {
        val stack = user?.getStackInHand(hand)

        if (stack == null || world == null || world.isClient || user.fallDistance != 0f)
            return TypedActionResult.pass(stack)

        world.sendEntityStatus(user, EntityStatuses.ADD_PORTAL_PARTICLES)
        user.addStatusEffect(StatusEffectInstance(
            StatusEffects.SLOWNESS, TICK_DURATION,
            10, false,
            false,
            false
        ))
        user.itemCooldownManager[this] = TICK_DURATION
        stack.teleportTicks = TICK_DURATION
        return TypedActionResult.success(stack, world.isClient)
    }

    override fun inventoryTick(stack: ItemStack?, world: World?, entity: Entity?, slot: Int, selected: Boolean) {
        if (stack == null || entity !is ServerPlayerEntity || world !is ServerWorld) return

        if (stack.teleportTicks > 0 && entity.falling)
            return cancelTeleport(stack, entity, world)
        if (stack.teleportTicks == 1)
            doTeleport(stack, entity, world)
        if (!stack.isEmpty)
            tickTeleport(stack)
    }

    /**
     * Reduces the teleporting ticks by 1, if the value is greater than 0.
     */
    private fun tickTeleport(stack: ItemStack) {
        if (stack.teleportTicks > 0)
            stack.teleportTicks -= 1
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
        if (!user.isCreative) stack.decrement(1)
        FabricDimensions.teleport(user, world.server.overworld, teleportTarget(user, world))
        world.playSound(null,
            user.blockPos,
            SoundEvents.ENTITY_ENDERMAN_TELEPORT,
            SoundCategory.PLAYERS,
            10f,
            -0.5f
        )
    }

    private fun cancelTeleport(stack: ItemStack, user: PlayerEntity, world: World) {
        stack.teleportTicks = 0
        user.itemCooldownManager[this] = 0
        user.removeStatusEffect(StatusEffects.SLOWNESS)
        world.playSound(null,
            user.blockPos,
            SoundEvents.BLOCK_CANDLE_EXTINGUISH,
            SoundCategory.PLAYERS,
            10f,
            -3f
        )
    }

    companion object {

        /**
         * The time in ticks the teleportation needs.
         */
        private const val TICK_DURATION = 50
    }
}