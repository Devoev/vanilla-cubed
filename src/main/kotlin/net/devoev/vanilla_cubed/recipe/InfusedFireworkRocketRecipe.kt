package net.devoev.vanilla_cubed.recipe

import net.devoev.vanilla_cubed.inventory.toList
import net.devoev.vanilla_cubed.inventory.toSet
import net.devoev.vanilla_cubed.item.ModItems
import net.devoev.vanilla_cubed.item.fireworksFlight
import net.minecraft.inventory.CraftingInventory
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.recipe.RecipeSerializer
import net.minecraft.recipe.SpecialCraftingRecipe
import net.minecraft.util.Identifier
import net.minecraft.world.World

class InfusedFireworkRocketRecipe(id: Identifier) : SpecialCraftingRecipe(id) {
    override fun matches(inventory: CraftingInventory, world: World): Boolean {
        val stacks = inventory.toList()
        val rockets = stacks.filter { it.isOf(Items.FIREWORK_ROCKET) }
        val chunk = stacks.filter { it.isOf(ModItems.INFUSED_DRAGON_SCALE_CHUNK) }

        return rockets.size == 8
                && chunk.size == 1
                && rockets.map { it.fireworksFlight }.toSet().size == 1
    }

    override fun craft(inventory: CraftingInventory): ItemStack {
        val res = ItemStack(ModItems.INFUSED_FIREWORK_ROCKET)
        res.fireworksFlight = inventory.toSet().first().fireworksFlight
        return res
    }

    override fun fits(width: Int, height: Int): Boolean = false

    override fun getOutput(): ItemStack = ItemStack(ModItems.INFUSED_FIREWORK_ROCKET)

    override fun getSerializer(): RecipeSerializer<*> = ModCraftingRecipes.INFUSED_FIREWORK_ROCKET
}