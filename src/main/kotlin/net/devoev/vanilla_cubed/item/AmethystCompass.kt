package net.devoev.vanilla_cubed.item

import net.devoev.vanilla_cubed.world.gen.structure.StructureHelper
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.client.item.CompassAnglePredicateProvider
import net.minecraft.client.item.UnclampedModelPredicateProvider
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
import net.minecraft.util.registry.Registry
import net.minecraft.util.registry.RegistryEntryList
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
     * The predicate provider for this compasses angle.
     */
    val anglePredicateProvider = CompassAnglePredicateProvider { world, stack, _ -> targetPos(stack, world) }

    /**
     * The predicate provider for indicate, whether the compass is charged.
     */
    val chargedPredicateProvider = UnclampedModelPredicateProvider { stack, _, _, _ -> if (isCharged(stack)) 1f else 0f }

    override fun use(world: World?, user: PlayerEntity?, hand: Hand?): TypedActionResult<ItemStack> {
        val stack = user?.getStackInHand(hand)

        if (world !is ServerWorld || user == null || stack == null || !isCharged(stack)) return TypedActionResult.pass(stack)

        setTargetPos(world, user, stack)
        user.itemCooldownManager[this] = 100
        if (isCharged(stack)) stack.damage(1, user) {}
        if (isCharged(stack)) world.playSound(null, user.blockPos, SoundEvents.BLOCK_AMETHYST_BLOCK_FALL, SoundCategory.PLAYERS, 35f, 3f)
        else world.playSound(null, user.blockPos, SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.PLAYERS, 0.5f, -1f)
        return TypedActionResult.success(stack, world.isClient)
    }

    /**
     * Sets the NBT data with key [TARGET_POS_KEY] for the given item.
     */
    private fun setTargetPos(world: ServerWorld, user: PlayerEntity, stack: ItemStack) {
        if (stack.item !is AmethystCompass) error("${stack.item} must be of type $AmethystCompass")

//        val positions = StructureHelper.tagKeys
//            .mapNotNull { world.locateStructure(it, user.blockPos, 15, false) }
//
//        val distances = positions
//            .map { Vec3d(it.x.toDouble(), it.y.toDouble(), it.z.toDouble()) }
//            .map { user.pos.subtract(it).length() }
//
//        val pos = (positions zip distances).minBy { it.second }.first

        val entries = StructureHelper.keys.map { world.registryManager.get(Registry.STRUCTURE_KEY).getEntry(it).get() }
        val list = RegistryEntryList.of(entries)
        val pos = world.chunkManager.chunkGenerator.locateStructure(world, list, user.blockPos, 15, false)?.first

        stack.nbt?.putIntArray(TARGET_POS_KEY, if (pos != null) listOf(pos.x, pos.y, pos.z) else null)
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

    /**
     * Returns true, if the [stack] is charged. A compass is charged, if it has at least 2 damege points left.
     */
    private fun isCharged(stack: ItemStack): Boolean {
        if (stack.item !is AmethystCompass) error("${stack.item} must be of type $AmethystCompass")
        return stack.maxDamage - stack.damage > 1
    }
}