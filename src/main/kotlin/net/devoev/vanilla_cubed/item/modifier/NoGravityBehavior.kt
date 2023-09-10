package net.devoev.vanilla_cubed.item.modifier

import net.devoev.vanilla_cubed.item.isEnderite
import net.devoev.vanilla_cubed.item.minedByEnderite
import net.devoev.vanilla_cubed.mixin.BlockMixin
import net.devoev.vanilla_cubed.mixin.ItemEntityMixin
import net.devoev.vanilla_cubed.tag.ModTagKeys.ENDERITE_ITEM
import net.devoev.vanilla_cubed.util.math.times
import net.minecraft.block.Block
import net.minecraft.entity.EntityType
import net.minecraft.entity.ItemEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.predicate.entity.EntityPredicates
import net.minecraft.util.math.Box
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable

/**
 * Removes gravity from the loot of killed targets.
 */
val NoGravityBehavior = PostHitModifier<Item> { _, target, _ ->
    if (target.isDead && !target.world.isClient)
        removeGravity(target.pos, target.world)
}

/**
 * Removes the gravity from all items that just spawned inside a small region around the given [pos].
 */
private fun removeGravity(pos: Vec3d, world: World) {
    pos.run {
        val delta = 0.1
        world.getEntitiesByType(
                EntityType.ITEM,
                Box(x - delta, y - delta, z - delta, x + delta, y + delta, z + delta),
                EntityPredicates.VALID_ENTITY)
            .filter { it.itemAge < 5 }
            .forEach {
                it.setNoGravity(true)
            }
    }
}

/**
 * Sets the [ItemStack.minedByEnderite] NBT value of block drops in [Block.getDroppedStacks] to `true`, if mined with enderite tools.
 * @see BlockMixin
 */
fun setMinedByEnderiteOfDroppedStack(stack: ItemStack, cir: CallbackInfoReturnable<List<ItemStack>>) {
    if (stack.item.isEnderite())
        cir.returnValue.forEach { it.minedByEnderite = true }
}

/**
 * Removes the gravity of the enderite item [entity] by calling [ItemEntity.setNoGravity].
 * @see ItemEntityMixin.setNoGravityEnderiteGear
 */
fun setNoGravityOfEnderiteGear(entity: ItemEntity) {
    if (entity.stack.isIn(ENDERITE_ITEM))
        entity.setNoGravity(true)
}

/**
 * Removes the gravity of an item [entity] mined with enderite tools by calling [ItemEntity.setNoGravity].
 * The NBT property [ItemStack.minedByEnderite] is then set to `false` again.
 * @see ItemEntityMixin.setNoGravityMinedByEnderite
 */
fun setNoGravityOfMinedByEnderite(entity: ItemEntity) {
    if (entity.stack.minedByEnderite) {
        entity.setNoGravity(true)
        entity.velocity *= 0.3
        entity.stack.minedByEnderite = false
    }
}