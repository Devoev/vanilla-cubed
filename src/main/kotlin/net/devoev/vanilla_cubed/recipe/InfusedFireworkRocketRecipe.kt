package net.devoev.vanilla_cubed.recipe

import net.devoev.vanilla_cubed.inventory.size
import net.devoev.vanilla_cubed.inventory.toList
import net.devoev.vanilla_cubed.item.ModItems
import net.devoev.vanilla_cubed.item.fireworksFlight
import net.minecraft.inventory.RecipeInputInventory
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.recipe.RecipeSerializer
import net.minecraft.recipe.SpecialCraftingRecipe
import net.minecraft.recipe.book.CraftingRecipeCategory
import net.minecraft.registry.DynamicRegistryManager
import net.minecraft.util.Identifier
import net.minecraft.world.World

/**
 * Recipe for [ModItems.INFUSED_FIREWORK_ROCKET]. Creates an infused rocket of durations 1-3.
 */
class InfusedFireworkRocketRecipe(id: Identifier, category: CraftingRecipeCategory) : SpecialCraftingRecipe(id, category) {
    override fun matches(inventory: RecipeInputInventory, world: World): Boolean {
        if (inventory.size != 9) return false

        val stacks = inventory.toList()
        val chunk = stacks[4]
        val rockets = stacks - chunk

        return rockets.all { it.isOf(Items.FIREWORK_ROCKET) }
                && chunk.isOf(ModItems.INFUSED_DRAGON_SCALE_CHUNK)
                && rockets.map { it.fireworksFlight }.toSet().size == 1
    }

    override fun craft(inventory: RecipeInputInventory, dynamicRegistryManager: DynamicRegistryManager): ItemStack {
        val res = ItemStack(ModItems.INFUSED_FIREWORK_ROCKET,8)
        res.fireworksFlight = inventory.getStack(0).fireworksFlight
        return res
    }

    override fun fits(width: Int, height: Int): Boolean = width * height == 9

    override fun getOutput(dynamicRegistryManager: DynamicRegistryManager): ItemStack = ItemStack(ModItems.INFUSED_FIREWORK_ROCKET)

    override fun getSerializer(): RecipeSerializer<*> = ModCraftingRecipes.INFUSED_FIREWORK_ROCKET
}