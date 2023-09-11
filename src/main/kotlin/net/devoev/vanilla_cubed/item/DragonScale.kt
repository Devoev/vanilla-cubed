package net.devoev.vanilla_cubed.item

import net.devoev.vanilla_cubed.mixin.EnderDragonEntityMixin
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.entity.AreaEffectCloudEntity
import net.minecraft.entity.boss.dragon.EnderDragonEntity
import net.minecraft.entity.mob.MobEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.ItemUsage
import net.minecraft.predicate.entity.EntityPredicates
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.stat.Stats
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.util.math.Box
import net.minecraft.world.World
import net.minecraft.world.event.GameEvent
import kotlin.math.max
import kotlin.random.Random

class DragonScale : Item(FabricItemSettings()) {

    /**
     * A dragon scale can be infused when right-clicked on a dragon breath.
     */
    override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        val list = world.getEntitiesByClass(AreaEffectCloudEntity::class.java, user.boundingBox.expand(2.0))
        { entity: AreaEffectCloudEntity? -> (entity != null) && entity.isAlive && (entity.owner is EnderDragonEntity) }

        val stack = user.getStackInHand(hand)

        if (list.isEmpty())
            return TypedActionResult.pass(stack)

        val areaEffectCloudEntity = list[0] as AreaEffectCloudEntity
        areaEffectCloudEntity.radius -= 0.5f
        world.playSound(null, user.x, user.y, user.z,
            SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.NEUTRAL, 1.0f, 1.0f)
        world.emitGameEvent(user, GameEvent.FLUID_PICKUP, user.blockPos)

        return TypedActionResult.success(infuse(stack, user, ItemStack(ModItems.INFUSED_DRAGON_SCALE)), world.isClient())
    }

    /**
     * Infuses this [DragonScale].
     */
    private fun infuse(stack: ItemStack, player: PlayerEntity, outputStack: ItemStack): ItemStack {
        player.incrementStat(Stats.USED.getOrCreateStat(this))
        return ItemUsage.exchangeStack(stack, player, outputStack)
    }
}

/**
 * Ender dragon will drop  [dragons scales][ModItems.DRAGON_SCALE] after its death.
 * Amount of dragon scales is calculated by the formula `players.size + Random.nextInt(0, 2)`.
 * @see EnderDragonEntityMixin.dropDragonScale
 */
fun MobEntity.dropDragonScale(ticksSinceDeath: Int) {
    if (ticksSinceDeath != 150) return

    val box = Box(blockPos)
    val players: List<PlayerEntity> = world.getEntitiesByClass(
        PlayerEntity::class.java,
        box.expand(128.0),
        EntityPredicates.EXCEPT_SPECTATOR
    )
    val amount = players.size + Random.nextInt(0, 2)

    repeat(max(amount, 1)) {
        dropItem(ModItems.DRAGON_SCALE)
    }
}