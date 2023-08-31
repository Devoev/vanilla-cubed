package net.devoev.vanilla_cubed.recipe

import net.devoev.vanilla_cubed.util.RegistryManager
import net.minecraft.recipe.RecipeSerializer
import net.minecraft.recipe.SpecialRecipeSerializer
import net.minecraft.util.registry.Registry

object ModCraftingRecipes : RegistryManager<RecipeSerializer<*>>(Registry.RECIPE_SERIALIZER) {

    val INFUSED_FIREWORK_ROCKET = create("infused_firework_rocket", SpecialRecipeSerializer(::InfusedFireworkRocketRecipe))

    // TODO: Add recipes to RecipeProvider
}