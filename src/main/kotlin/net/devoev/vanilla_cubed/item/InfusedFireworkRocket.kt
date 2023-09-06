package net.devoev.vanilla_cubed.item

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.client.item.ClampedModelPredicateProvider
import net.minecraft.client.item.TooltipContext
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.projectile.FireworkRocketEntity
import net.minecraft.item.FireworkRocketItem
import net.minecraft.item.ItemStack
import net.minecraft.stat.Stats
import net.minecraft.text.Text
import net.minecraft.util.Formatting
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World

class InfusedFireworkRocket : FireworkRocketItem(FabricItemSettings()) {

    override fun use(world: World?, user: PlayerEntity?, hand: Hand?): TypedActionResult<ItemStack> {
        if (user == null || world == null || !user.isFallFlying)
            return TypedActionResult.pass(user?.getStackInHand(hand))

        val stack = user.getStackInHand(hand)
        if (!world.isClient) {
            val fireworkRocketEntity = FireworkRocketEntity(world, stack, user)
            world.spawnEntity(fireworkRocketEntity)
            if (!user.abilities.creativeMode) {
                stack.infusionLvl--
                if (stack.infusionLvl <= 0) {
                    stack.decrement(1)
                    stack.infusionLvl = 3
                }
            }
            user.incrementStat(Stats.USED.getOrCreateStat(this))
        }
        return TypedActionResult.success(stack, world.isClient())
    }

    override fun appendTooltip(stack: ItemStack, world: World?, tooltip: MutableList<Text>, context: TooltipContext) {
        tooltip += INFUSION_TOOLTIP.copy().append(": ${stack.infusionLvl}").formatted(Formatting.GRAY)

        super.appendTooltip(stack, world, tooltip, context)
    }

    companion object {

        val INFUSION_TOOLTIP: Text = Text.translatable("item.vanilla_cubed.firework_rocket_infused.infusion_lvl")
    }
}

/**
 * The predicate provider to provide the infusion level.
 */
val INFUSION_LVL_PREDICATE_PROVIDER = ClampedModelPredicateProvider { stack, _, _, _ -> (stack.infusionLvl - 1f) / 2 }