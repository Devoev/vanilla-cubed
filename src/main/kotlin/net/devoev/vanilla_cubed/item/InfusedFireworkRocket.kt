package net.devoev.vanilla_cubed.item

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.projectile.FireworkRocketEntity
import net.minecraft.item.FireworkRocketItem
import net.minecraft.item.ItemStack
import net.minecraft.stat.Stats
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World

class InfusedFireworkRocket : FireworkRocketItem(ModItemGroup.VANILLA_CUBED.toSettings()) {

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

    private var ItemStack.infusionLvl: Int
        get() = if (orCreateNbt.contains(INFUSION_LVL_NBT_KEY)) nbt!!.getInt(INFUSION_LVL_NBT_KEY) else 3
        set(value) { orCreateNbt.putInt(INFUSION_LVL_NBT_KEY, value) }

    companion object {

        const val INFUSION_LVL_NBT_KEY = "infusion_lvl"
    }
}