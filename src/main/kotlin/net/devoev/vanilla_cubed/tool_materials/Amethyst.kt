package net.devoev.vanilla_cubed.tool_materials

import net.minecraft.item.Items
import net.minecraft.item.ToolMaterial
import net.minecraft.recipe.Ingredient

object Amethyst : ToolMaterial {

    override fun getDurability(): Int = 1561

    override fun getMiningSpeedMultiplier(): Float = 6F

    override fun getAttackDamage(): Float = 3F

    override fun getMiningLevel(): Int = 4

    override fun getEnchantability(): Int = 10

    override fun getRepairIngredient(): Ingredient = Ingredient.ofItems(Items.AMETHYST_SHARD)
}