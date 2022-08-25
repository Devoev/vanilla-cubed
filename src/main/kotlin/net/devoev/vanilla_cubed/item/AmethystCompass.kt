package net.devoev.vanilla_cubed.item

import net.devoev.vanilla_cubed.world.gen.structure.StructureHelper
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.client.item.CompassAnglePredicateProvider.CompassTarget
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.server.world.ServerWorld
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.GlobalPos
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World
import net.minecraft.world.gen.structure.Structure


/**
 * A compass, that searches for nearby [structures][Structure].
 */
object AmethystCompass : Item(FabricItemSettings().maxDamage(25).group(ModItemGroup.TOOLS)) {

    /**
     * The key for the NBT data, that stores the coordinates of the target position.
     */
    private const val TARGET_POS_KEY = "target_pos"

    /**
     * The predicate provider for this compasses target.
     */
    val compassTarget = CompassTarget { world, stack, _ -> targetPos(stack, world) }

    override fun use(world: World?, user: PlayerEntity?, hand: Hand?): TypedActionResult<ItemStack> {
        val stack = user?.getStackInHand(hand)

        if (world !is ServerWorld || user == null || stack == null) return TypedActionResult.pass(stack)

        setTargetPos(world, user, stack)
        user.itemCooldownManager[this] = 100
        world.playSound(null, user.blockPos, SoundEvents.BLOCK_AMETHYST_BLOCK_FALL, SoundCategory.PLAYERS, 25f, 1f)
        stack.damage(1, user) { TODO("Replace with uncharged compass") }
        return TypedActionResult.success(stack, world.isClient)
    }

    /**
     * Sets the NBT data with key [TARGET_POS_KEY] for the given item.
     */
    private fun setTargetPos(world: ServerWorld, user: PlayerEntity, stack: ItemStack) {
        if (stack.item !is AmethystCompass) error("${stack.item} must be of type $AmethystCompass")

        val positions = StructureHelper.tagKeys
            .mapNotNull { world.locateStructure(it, user.blockPos, 15, false) }

        val distances = positions
            .map { Vec3d(it.x.toDouble(), it.y.toDouble(), it.z.toDouble()) }
            .map { user.pos.subtract(it).length() }

        val pos = (positions zip distances).minBy { it.second }.first
        stack.nbt?.putIntArray(TARGET_POS_KEY, listOf(pos.x, pos.y, pos.z))
    }

    /**
     * Returns the targeted position of the given [stack].
     */
    private fun targetPos(stack: ItemStack, world: World): GlobalPos? {
        if (stack.item !is AmethystCompass) error("${stack.item} must be of type $AmethystCompass")
        val list = stack.nbt?.getIntArray(TARGET_POS_KEY) ?: return null
        if (list.size != 3) return null
        return GlobalPos.create(world.registryKey, BlockPos(list[0],list[1],list[2]))
    }
}