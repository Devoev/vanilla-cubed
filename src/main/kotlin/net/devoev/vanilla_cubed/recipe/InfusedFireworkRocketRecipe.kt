package net.devoev.vanilla_cubed.recipe

import net.devoev.vanilla_cubed.item.ModItems
import net.minecraft.inventory.CraftingInventory
import net.minecraft.item.ItemStack
import net.minecraft.recipe.RecipeSerializer
import net.minecraft.recipe.SpecialCraftingRecipe
import net.minecraft.util.Identifier
import net.minecraft.world.World

class InfusedFireworkRocketRecipe(id: Identifier) : SpecialCraftingRecipe(id) {
    override fun matches(inventory: CraftingInventory?, world: World?): Boolean {
        println(inventory)

        return false
    }

    override fun craft(inventory: CraftingInventory?): ItemStack {
        println(inventory)

        return output
    }

    override fun fits(width: Int, height: Int): Boolean = width * height == 9

    override fun getOutput(): ItemStack = ItemStack(ModItems.INFUSED_FIREWORK_ROCKET)

    override fun getSerializer(): RecipeSerializer<*> = ModCraftingRecipes.INFUSED_FIREWORK_ROCKET
}