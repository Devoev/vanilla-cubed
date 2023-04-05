package net.devoev.vanilla_cubed.item

import net.devoev.vanilla_cubed.world.gen.structure.StructureHelper
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.server.world.ServerWorld
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.util.registry.Registry
import net.minecraft.util.registry.RegistryEntryList
import net.minecraft.world.World
import net.minecraft.world.gen.structure.Structure


/**
 * A compass, that searches for nearby [structures][Structure].
 */
class AmethystCompass : Item(FabricItemSettings().maxDamage(25).group(ModItemGroup.VANILLA_CUBED)) {

    override fun use(world: World?, user: PlayerEntity?, hand: Hand?): TypedActionResult<ItemStack> {
        val stack = user?.getStackInHand(hand)

        if (world !is ServerWorld || user == null || stack == null || !stack.charged) return TypedActionResult.pass(stack)

        generateTargetPos(world, user, stack)
        user.itemCooldownManager[this] = 100
        if (stack.charged) stack.damage(1, user) {}
        if (stack.charged) world.playSound(null, user.blockPos, SoundEvents.BLOCK_AMETHYST_BLOCK_FALL, SoundCategory.PLAYERS, 35f, 3f)
        else world.playSound(null, user.blockPos, SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.PLAYERS, 0.5f, -1f)

        return TypedActionResult.success(stack, world.isClient)
    }

    /**
     * Generates a new target pos and sets the value of the given [stack].
     */
    private fun generateTargetPos(world: ServerWorld, user: PlayerEntity, stack: ItemStack) {
        if (stack.item !is AmethystCompass) error("${stack.item} must be of type ${AmethystCompass::class}")

        val entries = StructureHelper.keys.map { world.registryManager.get(Registry.STRUCTURE_KEY).getEntry(it).get() }
        val list = RegistryEntryList.of(entries)
        stack.targetPos = world.chunkManager.chunkGenerator.locateStructure(
            world, list, user.blockPos, 15, false
        )?.first
    }
}