package net.devoev.vanilla_cubed.recipe

import net.minecraft.inventory.CraftingInventory
import net.minecraft.item.ItemStack
import net.minecraft.recipe.RecipeSerializer
import net.minecraft.recipe.SpecialCraftingRecipe
import net.minecraft.util.Identifier
import net.minecraft.world.World

class InfusedFireworkRocketRecipe(id: Identifier) : SpecialCraftingRecipe(id) {
    override fun matches(inventory: CraftingInventory?, world: World?): Boolean {
        TODO("Not yet implemented")
    }

    override fun craft(inventory: CraftingInventory?): ItemStack {
        TODO("Not yet implemented")
    }

    override fun fits(width: Int, height: Int): Boolean = width * height == 3

    override fun getSerializer(): RecipeSerializer<*> = ModCraftingRecipes.INFUSED_FIREWORK_ROCKET
}