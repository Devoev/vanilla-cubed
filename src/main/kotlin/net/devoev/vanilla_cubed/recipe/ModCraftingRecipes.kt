package net.devoev.vanilla_cubed.recipe

import net.devoev.vanilla_cubed.util.RegistryManager
import net.minecraft.recipe.RecipeSerializer
import net.minecraft.recipe.SpecialRecipeSerializer
import net.minecraft.util.registry.Registry

object ModCraftingRecipes : RegistryManager<RecipeSerializer<*>>(Registry.RECIPE_SERIALIZER) {

    val INFUSED_FIREWORK_ROCKET = create("firework_rocket_infused", SpecialRecipeSerializer(::InfusedFireworkRocketRecipe))
    val MAGNETIZE_NETHERITE_TOOLS = create("magnetize_netherite_tools", MagnetizeNetheriteToolsRecipe.Serializer(::MagnetizeNetheriteToolsRecipe))

    // TODO: Add recipes using dynamic data generation
}