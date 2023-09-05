package net.devoev.vanilla_cubed.recipe

import net.devoev.vanilla_cubed.inventory.get
import net.devoev.vanilla_cubed.inventory.toList
import net.devoev.vanilla_cubed.item.isNetherite
import net.devoev.vanilla_cubed.item.magnetic
import net.minecraft.inventory.Inventory
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.item.ToolItem
import net.minecraft.recipe.Ingredient
import net.minecraft.recipe.RecipeSerializer
import net.minecraft.recipe.SmithingRecipe
import net.minecraft.util.Identifier
import net.minecraft.world.World

/**
 * Recipe that magnetizes demagnetized netherite tools by combination with a [Items.NETHERITE_SCRAP].
 */
class MagnetizeNetheriteToolsRecipe(id: Identifier) : SmithingRecipe(id, Ingredient.EMPTY, Ingredient.EMPTY, ItemStack.EMPTY) {

    override fun matches(inventory: Inventory, world: World): Boolean {
        val (base, addition) = inventory.toList()
        return base.item is ToolItem
                && base.item.isNetherite()
                && !base.magnetic
                && addition.isOf(Items.NETHERITE_SCRAP)
    }

    override fun craft(inventory: Inventory): ItemStack = inventory[0].copy().apply { magnetic = true }

    override fun getSerializer(): RecipeSerializer<*> = ModCraftingRecipes.MAGNETIZE_NETHERITE_TOOLS
}