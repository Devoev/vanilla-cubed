package net.devoev.vanilla_cubed.item

import net.devoev.vanilla_cubed.util.math.toVec3d
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.server.world.ServerWorld
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World
import kotlin.jvm.optionals.getOrNull

object EnderitePowder : Item(ModItemGroup.TOOLS.toSettings()) {

    @OptIn(ExperimentalStdlibApi::class)
    override fun use(world: World?, user: PlayerEntity?, hand: Hand?): TypedActionResult<ItemStack> {
        val stack = user?.getStackInHand(hand)

        if (stack == null || world !is ServerWorld || user !is ServerPlayerEntity) return TypedActionResult.pass(stack)

        //TODO: Teleport to overworld
        val pos = user.spawnPointPosition ?: world.spawnPos
        var vec = PlayerEntity.findRespawnPosition(world, pos, user.limbAngle, false, user.isAlive).getOrNull()

        if (vec == null) vec = world.spawnPos.toVec3d()

        user.itemCooldownManager[this] = 20
        user.teleport(vec.x, vec.y, vec.z, true)
        world.playSound(null, user.blockPos, SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.PLAYERS, 3f, -0.5f)
        stack.decrement(1)
        return TypedActionResult.success(stack, world.isClient)
    }
}