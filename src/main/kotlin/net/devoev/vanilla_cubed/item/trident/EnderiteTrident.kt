package net.devoev.vanilla_cubed.item.trident

import net.devoev.vanilla_cubed.item.ModItemGroup
import net.devoev.vanilla_cubed.item.toSettings
import net.devoev.vanilla_cubed.util.math.toFloat
import net.minecraft.client.item.UnclampedModelPredicateProvider
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.projectile.TridentEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.TridentItem
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World

object EnderiteTrident : TridentItem(ModItemGroup.VANILLA_CUBED.toSettings()) {

    val THROWING_PREDICATE_PROVIDER = UnclampedModelPredicateProvider { stack, _, entity, _ ->
        (entity != null && entity.isUsingItem && entity.activeItem == stack).toFloat()
    }

    override fun use(world: World?, user: PlayerEntity?, hand: Hand?): TypedActionResult<ItemStack> {
        println("use override")
        return super.use(world, user, hand)
    }

    override fun onStoppedUsing(stack: ItemStack?, world: World?, user: LivingEntity?, remainingUseTicks: Int) {
        println("onStoppedUsing override")
        super.onStoppedUsing(stack, world, user, remainingUseTicks)
    }
}

class EnderiteTridentEntity(entityType: EntityType<out TridentEntity>, world: World) : TridentEntity(entityType, world)