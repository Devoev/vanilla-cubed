package net.devoev.vanilla_cubed.enchantment

import net.devoev.vanilla_cubed.enchantment.HurlingEnchantment.damage
import net.devoev.vanilla_cubed.mixin.TridentEntityMixin
import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.enchantment.EnchantmentTarget
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.projectile.TridentEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.TridentItem

/**
 * An [Enchantment] that increases the damage of thrown [tridents][TridentItem].
 */
object HurlingEnchantment : Enchantment(Rarity.UNCOMMON, EnchantmentTarget.TRIDENT, arrayOf(EquipmentSlot.MAINHAND)) {

    override fun getMinPower(level: Int): Int = 5 + level * 7

    override fun getMaxPower(level: Int): Int = 50

    override fun getMaxLevel(): Int = 3

    /**
     * The amount of extra damage per [level].
     */
    fun damage(level: Int) = (1 + level)/2

    /**
     * The amount of extra damage for the given [stack].
     */
    fun damage(stack: ItemStack) = damage(level(stack))

    /**
     * The level of this enchantment the given [stack] has.
     */
    private fun level(stack: ItemStack): Int = EnchantmentHelper.getLevel(ModEnchantments.HURLING, stack)
}

/**
 * Applies the force enchantment when a trident hits an entity
 * by modifying the local [f] variable in [TridentEntity.onEntityHit].
 * @see TridentEntityMixin.hurlingOnEntityHit
 */
fun hurlingOnEntityHit(f: Float, tridentStack: ItemStack): Float {
    return f + damage(tridentStack)
}