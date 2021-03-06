package net.devoev.vanilla_cubed.item

import net.minecraft.entity.AreaEffectCloudEntity
import net.minecraft.entity.boss.dragon.EnderDragonEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.ItemUsage
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.stat.Stats
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World
import net.minecraft.world.event.GameEvent

object DragonScale : Item(ModItemGroup.MATERIALS.toSettings()) {

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
        areaEffectCloudEntity.radius = areaEffectCloudEntity.radius - 0.5f
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