package net.devoev.vanilla_cubed.recipe

import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.recipe.Ingredient
import net.minecraft.recipe.SmithingRecipe
import net.minecraft.util.Identifier

/**
 * Recipe that magnetizes demagnetized netherite tools.
 * TODO: Update implementation and create custom provider/ serializer
 */
class MagnetizeNetheriteToolsRecipe(id: Identifier, result: ItemStack) : SmithingRecipe(
    id,
    Ingredient.ofItems(Items.NETHERITE_AXE, Items.NETHERITE_HOE, Items.NETHERITE_PICKAXE, Items.NETHERITE_SHOVEL, Items.NETHERITE_SWORD),
    Ingredient.ofItems(Items.NETHERITE_SCRAP),
    result
)