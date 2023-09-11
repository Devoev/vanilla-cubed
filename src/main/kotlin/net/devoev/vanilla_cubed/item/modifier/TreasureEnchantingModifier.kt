package net.devoev.vanilla_cubed.item.modifier

import net.devoev.vanilla_cubed.item.isAncientGold
import net.devoev.vanilla_cubed.mixin.ExperienceOrbEntityMixin
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.enchantment.EnchantmentLevelEntry
import net.minecraft.enchantment.Enchantments
import net.minecraft.entity.ExperienceOrbEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable

// TODO: Add modifier that doesn't explicitly depend on ancient gold

/**
 * Sets the [treasureAllowed] parameter to `true`, if the item of the [stack] is made of ancient gold.
 */
fun generateTreasureEnchantments(treasureAllowed: Boolean, stack: ItemStack): Boolean {
    return treasureAllowed || stack.item.isAncientGold()
}

/**
 * Filters out the curses, if the item of the given [stack] is made of ancient gold.
 */
fun generateNoCurses(stack: ItemStack, cir: CallbackInfoReturnable<List<EnchantmentLevelEntry>>) {
    if (stack.item.isAncientGold()) {
        cir.returnValue = cir.returnValue.filter { !it.enchantment.isCursed }
    }
}

/**
 * Changes the repair amount of mending gear to be higher, when the item is made of ancient gold
 * by modifying the local [i] variable in [ExperienceOrbEntity.repairPlayerGears].
 * @see ExperienceOrbEntityMixin.repairPlayersGearFaster
 */
fun repairPlayersGearFaster(i: Int, player: PlayerEntity): Int {
    val stack = EnchantmentHelper.chooseEquipmentWith(Enchantments.MENDING, player) { it.isDamaged }?.value
    return if (stack?.item?.isAncientGold() == true) 2*i else i
}