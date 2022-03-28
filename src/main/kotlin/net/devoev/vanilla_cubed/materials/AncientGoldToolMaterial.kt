package net.devoev.vanilla_cubed.materials

import net.devoev.vanilla_cubed.item.ModItems
import net.minecraft.item.ToolMaterial
import net.minecraft.recipe.Ingredient

object AncientGoldToolMaterial : ToolMaterial {

    override fun getDurability(): Int = 1561

    override fun getMiningSpeedMultiplier(): Float = 6F

    override fun getAttackDamage(): Float = 3F

    override fun getMiningLevel(): Int = 4

    override fun getEnchantability(): Int = 10

    override fun getRepairIngredient(): Ingredient = Ingredient.ofItems(ModItems.ANCIENT_GOLD_INGOT)
}