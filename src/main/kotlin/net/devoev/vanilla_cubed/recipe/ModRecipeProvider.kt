package net.devoev.vanilla_cubed.recipe

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.data.server.recipe.ComplexRecipeJsonBuilder
import net.minecraft.data.server.recipe.RecipeJsonProvider
import net.minecraft.recipe.SpecialRecipeSerializer
import java.util.function.Consumer

class ModRecipeProvider(generator: FabricDataGenerator) : FabricRecipeProvider(generator) {

    override fun generateRecipes(exporter: Consumer<RecipeJsonProvider>) {
        with(exporter) {
            createSpecialRecipe("firework_rocket_infused", ModCraftingRecipes.INFUSED_FIREWORK_ROCKET)
            createSpecialRecipe("magnetize_netherite_tools", ModCraftingRecipes.MAGNETIZE_NETHERITE_TOOLS)
        }
    }

    /**
     * Creates a hardcoded special recipe using the [serializer] and registers it under the given [recipeId].
     */
    private fun Consumer<RecipeJsonProvider>.createSpecialRecipe(recipeId: String, serializer: SpecialRecipeSerializer<*>) {
        ComplexRecipeJsonBuilder.create(serializer).offerTo(this, recipeId)
    }
}